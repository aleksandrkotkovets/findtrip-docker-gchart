package by.sum_solutions.findtrip.controller.dto;

import by.sum_solutions.findtrip.repository.entity.Role;
import org.assertj.core.internal.bytebuddy.asm.Advice;

import javax.validation.constraints.*;
import java.io.Serializable;

public class UserDTO {

    private Long id;

    @NotNull
    @Pattern(regexp = "^([a-zA-Z])([a-zA-Z0-9]{4,13})$", message = "Login must start with a letter\nLogin must be longer than 5 characters and shorter than 14")
    private String login;

    @NotNull
    @Size(min = 5, max = 14, message = "Password must be longer than 5 characters and shorter than 14")
    private String password;

    @NotNull
    @Email(message = "Invalid email")
    private String email;

    @NotNull
    //@Size(min = 3, max = 10, message = "First name must be longer than 3 characters and shorter than 10")
    @Pattern(regexp = "^[A-Z]([a-z]{2,9})$", message = "F Err")
    private String firstName;

    @Size(min = 3, max = 10, message = "Last name must be longer than 3 characters and shorter than 10")
    private String lastName;

    @Size(min = 3, max = 10, message = "Patronymic name must be longer than 3 characters and shorter than 10")
    private String patronymic;

    @Pattern(message = "Phone number is required",regexp = "^375(17|25|29|33|44)([0-9]{7})$")
    private String phoneNumber;

    private Role role;

    public UserDTO() {
    }

    public UserDTO(@NotNull @Pattern(regexp = "^([a-zA-Z])([a-zA-Z0-9]{1,13})$", message = "Login must be longer than 5 characters and shorter than 14") String login, @NotNull @NotBlank(message = "Email is required") @Email(message = "Invalid email") String email, @NotNull @NotBlank(message = "Password is required") @Size(min = 5, max = 14, message = "Password must be longer than 5 characters and shorter than 14") String password, @NotNull @NotBlank(message = "First name is required") @Size(min = 3, max = 10, message = "First name must be longer than 3 characters and shorter than 10") String firstName, @Size(min = 3, max = 10, message = "Last name must be longer than 3 characters and shorter than 10") @NotBlank(message = "Last name is required") String lastName, @Size(min = 3, max = 10, message = "Patronymic name must be longer than 3 characters and shorter than 10") @NotBlank(message = "Patronymic is required") String patronymic, @Pattern(message = "Phone number is required", regexp = "^375(17|25|29|33|44)([0-9]{7})$") String phoneNumber, Role role) {
        this.login = login;
        this.email = email;
        this.password = password;
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
