package com.blas.todo.form;

import javax.validation.constraints.NotNull;

public class RescueAccountForm {
    @NotNull
    private String email;

    public RescueAccountForm() {

    }

    public RescueAccountForm(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
