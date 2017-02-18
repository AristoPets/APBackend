package com.AristoPets.dto;


import javax.servlet.http.HttpServletRequest;

public class RegistrationDto {

    private Long id;
    private Long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String authType;
    private String authId;
    private String Country;
    private String city;
    private String password;
    private String passwordConfirmation;
    private String invalidUserInput;

    public RegistrationDto() {
    }

    ;

    public RegistrationDto(String password) {
        this.password = password;
    }

    ;

    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getInvalidUserInput() {
        return invalidUserInput;
    }

    public void setInvalidUserInput(String invalidUserInput) {
        this.invalidUserInput = invalidUserInput;
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }

    public static RegistrationDto requestToRegisteredUser(HttpServletRequest request) {
        RegistrationDto registrationDto = new RegistrationDto();
        String email, fName, lName, pass, passConfirm, country, city;
        email = request.getParameter("email");
        if (email.equals("")) {
            registrationDto.setInvalidUserInput("Email can't be empty");
            return registrationDto;
        }
        fName = request.getParameter("firstName");
        if (fName.equals("")) {
            registrationDto.setInvalidUserInput("Fist name can't be empty");
            return registrationDto;
        }
        lName = request.getParameter("lastName");
        if (lName.equals("")) {
            registrationDto.setInvalidUserInput("Last name can't be empty");
            return registrationDto;
        }
        pass = request.getParameter("password");
        if (pass.equals("")) {
            registrationDto.setInvalidUserInput("Password field can't be empty");
            return registrationDto;
        }
        passConfirm = request.getParameter("passwordConfirm");
        if (passConfirm.equals("")) {
            registrationDto.setInvalidUserInput("Password field can't be empty");
            return registrationDto;
        }
        if (!pass.equals(passConfirm)) {
            registrationDto.setInvalidUserInput("Passwords aren't same");
            return registrationDto;
        }
        country = request.getParameter("country");
        city = request.getParameter("city");
        registrationDto.setFirstName(fName);
        registrationDto.setLastName(lName);
        registrationDto.setPassword(pass);
        registrationDto.setPasswordConfirmation(passConfirm);
        registrationDto.setEmail(email);
        registrationDto.setCountry(country);
        registrationDto.setCity(city);
        return registrationDto;
    }
}