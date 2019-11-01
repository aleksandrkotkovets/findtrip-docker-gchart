package by.sum_solutions.findtrip.controller.dto;

import by.sum_solutions.findtrip.repository.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserDTO {

    private Long id;

    @Size(min = 5,max = 14, message = "Login mast be at least 5 characters long")
    @NotBlank(message="Login is required")
    private String login;

    @Email(message = "invalid email")
    @NotBlank(message = "email is required")
    private String email;

    @Size(min = 5,max = 14, message = "Password mast be at least 5 characters long")
    @NotBlank(message="password is required")
    private String password;

    @Size(min = 3,max = 14, message = "First name mast be at least 5 characters long")
    @NotBlank(message="First name is required")
    private String firstName;

    @Size(min = 3,max = 14, message = "Last name mast be at least 5 characters long")
    @NotBlank(message="Last name is required")
    private String lastName;

    @Size(min = 3,max = 14, message = "Patronymic mast be at least 5 characters long")
    @NotBlank(message="Patronymic is required")
    private String patronymic;

    @Size(min = 12,max = 12, message = "Phone number mast has 12 characters ")
    @NotBlank(message="Phone number is required")
    @Pattern(regexp = "^375(17|25|29|33|44)([0-9]{7})$")
    private String phoneNumber;

    private Role role;

    public UserDTO() {
    }

    public UserDTO(Long id, @Size(min = 5, max = 14, message = "Login mast be at least 5 characters long") @NotBlank(message = "Login is required") String login, @Email(message = "{user.email.invalid}") @NotBlank(message = "email is required") String email, @Size(min = 5, max = 14, message = "Password mast be at least 5 characters long") @NotBlank(message = "password is required") String password, @Size(min = 3, max = 14, message = "First name mast be at least 5 characters long") @NotBlank(message = "First name is required") String firstName, @Size(min = 3, max = 14, message = "Last name mast be at least 5 characters long") @NotBlank(message = "Last name is required") String lastName, @Size(min = 3, max = 14, message = "Patronymic mast be at least 5 characters long") @NotBlank(message = "Patronymic is required") String patronymic, @Size(min = 12, max = 12, message = "Phone number mast has 12 characters ") @NotBlank(message = "Phone number is required") @Pattern(regexp = "^375(17|25|29|33|44)([0-9]{7})$") String phoneNumber, Role role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public UserDTO(@Size(min = 5, max = 14, message = "Login mast be at least 5 characters long") @NotBlank(message = "Login is required") String login, @Email(message = "{user.email.invalid}") @NotBlank(message = "email is required") String email, @Size(min = 5, max = 14, message = "Password mast be at least 5 characters long") @NotBlank(message = "password is required") String password, @Size(min = 3, max = 14, message = "First name mast be at least 5 characters long") @NotBlank(message = "First name is required") String firstName, @Size(min = 3, max = 14, message = "Last name mast be at least 5 characters long") @NotBlank(message = "Last name is required") String lastName, @Size(min = 3, max = 14, message = "Patronymic mast be at least 5 characters long") @NotBlank(message = "Patronymic is required") String patronymic, @Size(min = 12, max = 12, message = "Phone number mast has 12 characters ") @NotBlank(message = "Phone number is required") @Pattern(regexp = "^375(17|25|29|33|44)([0-9]{7})$") String phoneNumber, Role role) {
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
