package by.sum_solutions.findtrip.controller.dto;

import by.sum_solutions.findtrip.repository.entity.Role;
import org.assertj.core.internal.bytebuddy.asm.Advice;

import javax.validation.constraints.*;
import java.io.Serializable;

public class UserDTO {

    private Long id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String patronymic;

    @NotNull
    private String phoneNumber;

    private Role role;

    public UserDTO() {
    }

    public UserDTO(@NotNull String login, @NotNull String password, @NotNull String email, @NotNull String firstName, @NotNull String lastName, @NotNull String patronymic, @NotNull String phoneNumber, Role role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public UserDTO(Long id, @NotNull String login, @NotNull String password, @NotNull String email, @NotNull String firstName, @NotNull String lastName, @NotNull String patronymic, @NotNull String phoneNumber, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDTO{");
        sb.append(", login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
