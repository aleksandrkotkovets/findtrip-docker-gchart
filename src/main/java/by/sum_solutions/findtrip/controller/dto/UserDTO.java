package by.sum_solutions.findtrip.controller.dto;

import by.sum_solutions.findtrip.repository.entity.Role;
import org.intellij.lang.annotations.RegExp;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

    private Long id;

    @NotNull
    @Size(min = 3,max = 14, message = "Login must have between {min} and {max} characters.")
    private String login;

    @NotNull
    @Size(min = 8,max = 14, message = "Password must have between {min} and {max} characters.")
    private String password;

    @NotNull
    @Email(message = "This field must contain an E-Mail in the format example@site.com")
    private String email;

    @NotNull
    @Size(min = 3,max = 14, message = "First name must have between {min} and {max} characters.")
    private String firstName;

    @NotNull
    @Size(min = 3,max = 14, message = "Last name must have between {min} and {max} characters.")
    private String lastName;

    @NotNull
    @Size(min = 3,max = 14, message = "Patronymic must have between {min} and {max} characters.")
    private String patronymic;

    @NotNull
    @Pattern(regexp = "^375[(](17|25|29|33|44)[)]([0-9]{7})$", message = "The phone number should be similar to example 375(17|25|29|33|44)3885668")
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
        sb.append(", id='").append(id).append('\'');
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
