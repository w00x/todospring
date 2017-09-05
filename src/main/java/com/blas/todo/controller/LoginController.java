package com.blas.todo.controller;

import com.blas.todo.entity.Role;
import com.blas.todo.entity.User;
import com.blas.todo.entity.VerificationToken;
import com.blas.todo.event.OnRegistrationCompleteEvent;
import com.blas.todo.form.AccountForm;
import com.blas.todo.form.RescueAccountForm;
import com.blas.todo.repository.RoleRepository;
import com.blas.todo.repository.UserRepository;
import com.blas.todo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private UserService service;

    @Autowired
    private MessageSource messages;

    final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String loginForm(Model model,
                            @RequestParam(name = "error", required = false) String error) {
        model.addAttribute("error", error);
        return "/login/login";
    }

    @GetMapping("/new_account")
    public String newAccount(Model model,
                             @RequestParam(name = "error", required = false) String error) {
        model.addAttribute("accountForm", new AccountForm());
        model.addAttribute("error", error);
        return "/login/new_acount";
    }

    @PostMapping("/create_acount")
    public String userSave(@Valid AccountForm accountForm, BindingResult bindingResult, Model model, RedirectAttributes attr, WebRequest request) {
        if(!accountForm.getPassword().equals(accountForm.getRePassword())) {
            model.addAttribute("error_noequal", 1);
            return "/login/new_acount";
        }

        if (!bindingResult.hasErrors()) {
            String appUrl = request.getContextPath();
            User user = userService.saveNewUser(accountForm.getEmail(), accountForm.getPassword());
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
        }
        else {
            return "/login/new_acount";
        }

        return "redirect:/login";
    }

    @GetMapping(value = "/regitrationConfirm")
    public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        user.setEnabled(true);
        service.saveUser(user);
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
    }

    @GetMapping(value = "/rescue_password")
    public String rescuePassword(Model model) {
        model.addAttribute("rescueForm", new RescueAccountForm());
        return "/login/rescue_password";
    }

    @PostMapping(value = "/do_rescue_password")
    public String doRescuePassword(@Valid RescueAccountForm rescueAccountForm, BindingResult bindingResult, WebRequest request, Model model) {
        if (!bindingResult.hasErrors()) {
            User user = userService.getUserByUserName(rescueAccountForm.getEmail());
            String newPassword = userService.resetPassword(user);
            userService.saveUser(user);
            userService.sendMailNewPassword(user, newPassword);
        }
        else {
            model.addAttribute("rescueForm", new RescueAccountForm());
            return "/login/rescue_password";
        }

        return "redirect:/login";
    }
}
