package edu.kit.informatik;

public class Admin extends Person {
    /**
     *
     */
    private String password;

    public Admin(String preName, String surName, String password) {
        this.preName = preName;
        this.surName = surName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
