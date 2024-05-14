package me.lucas.gerenciadorDeSenhas.model.domain.user;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    MASTER("master");


    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

}