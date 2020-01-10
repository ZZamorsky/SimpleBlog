package model.user;

import java.time.LocalDateTime;

public class RegisteredUser extends User {

    private String name;
    private String surname;
    private String password;
    private LocalDateTime registeredSince;

    public RegisteredUser(String nickname, String name, String surname, String password) {
        super(nickname);
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.registeredSince =  LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDateTime getRegisteredSince() {
        return registeredSince;
    }

    public String getPassword() {
        return password;
    }
}
