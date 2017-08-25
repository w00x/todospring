package com.blas.todo.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AccountForm {
    @NotNull
    private String email;
    @NotNull
    @Length(min = 5, message = "Tu passoword como minimo debe tener 5 caracteres")
    private String password;
    @NotNull
    private String rePassword;

    public AccountForm(String email, String password, String rePassword) {
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
    }

    public AccountForm() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
