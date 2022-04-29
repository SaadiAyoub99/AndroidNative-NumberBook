package com.example.contact;

public class Contact {
    private String phone;
    private String nom;

    public Contact(String nom, String phone) {
        this.phone = phone;
        this.nom = nom;
    }

    public String getPhone() {
        return phone;
    }

    public String getNom() {
        return nom;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
