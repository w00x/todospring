package com.blas.todo.service;

import com.blas.todo.entity.Role;
import com.blas.todo.entity.VerificationToken;
import com.blas.todo.repository.RoleRepository;
import com.blas.todo.repository.UserRepository;
import com.blas.todo.repository.VerificationTokenRepository;
import com.blas.todo.service.interfaces.IUserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

@Service("userService")
public class UserService implements UserDetailsService, IUserService {
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;
    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Autowired
    private UserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    Configuration fmConfiguration;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.blas.todo.entity.User user = userRepository.findByUsername(username);
        List<GrantedAuthority> autorithies = buildAuthorities(user.getRole());

        return buildUser(user, autorithies);
    }

    private User buildUser(com.blas.todo.entity.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

        for (Role role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new ArrayList<GrantedAuthority>(auths);
    }

    @Override
    public com.blas.todo.entity.User saveNewUser(String email, String password) {
        com.blas.todo.entity.User user = new com.blas.todo.entity.User();
        user.setEnabled(true);
        user.setUsername(email);
        user.setPassword(passwordEncoder.encode(password));

        Role role = roleRepository.findByRole("ADMIN");
        user.setRole(new HashSet<Role>(Arrays.asList(role)));
        userRepository.save(user);
        return user;
    }

    @Override
    public com.blas.todo.entity.User saveUser(com.blas.todo.entity.User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void createVerificationToken(com.blas.todo.entity.User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public com.blas.todo.entity.User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String resetPassword(com.blas.todo.entity.User user) {
        SecureRandom random = new SecureRandom();
        String newPassword = random.generateSeed(5).toString();
        user.setPassword(passwordEncoder.encode(newPassword));
        return newPassword;
    }

    @Override
    public void sendMailNewPassword(com.blas.todo.entity.User user, String newPassord) {
        String recipientAddress = user.getUsername();
        String subject = "Recuperar password";

        Map<String, String> context = new HashMap<>();
        context.put("newPassord", newPassord);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);

        try {
            Template template = fmConfiguration.getTemplate("mail/restorePassword.ftl");
            String messageHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, context);

            email.setText(messageHtml);
        }
        catch(IOException ex) {

        }
        catch(TemplateException ex) {

        }

        mailSender.send(email);
    }

}
