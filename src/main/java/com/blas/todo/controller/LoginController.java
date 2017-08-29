package com.blas.todo.controller;

import com.blas.todo.entity.Role;
import com.blas.todo.entity.User;
import com.blas.todo.form.AccountForm;
import com.blas.todo.repository.RoleRepository;
import com.blas.todo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public String userSave(@Valid AccountForm accountForm, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if(!accountForm.getPassword().equals(accountForm.getRePassword())) {
            model.addAttribute("error_noequal", 1);
            return "/login/new_acount";
        }

        if (!bindingResult.hasErrors()) {
            User user = new User();
            user.setEnabled(true);
            user.setUsername(accountForm.getEmail());
            user.setPassword(passwordEncoder.encode(accountForm.getPassword()));

            Role role = roleRepository.findByRole("ADMIN");
            user.setRole(new HashSet<Role>(Arrays.asList(role)));
            userRepository.save(user);
        }
        else {
            return "/login/new_acount";
        }

        return "redirect:/login";
    }
}
