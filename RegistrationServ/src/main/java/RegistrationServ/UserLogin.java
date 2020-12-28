package RegistrationServ;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;


public class UserLogin {
    private int id;
    private String login;
    private String password;

    @JsonCreator
    public UserLogin(@JsonProperty("id") int id,
                     @JsonProperty("login") String login,
                     @JsonProperty("password") String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
