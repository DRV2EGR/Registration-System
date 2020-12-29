package kursach.registrationClient.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//@Repository
public class User {
    UserLogin userLogin;
    UserInfo userInfo;

    @JsonCreator
    public User(@JsonProperty("id") int id,
                @JsonProperty("login") String login,
                @JsonProperty("password") String password,
                @JsonProperty("name")String name,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("age")int age,
                @JsonProperty("location") String location){
        this.userInfo=new UserInfo(name, lastName, age, location);
        this.userLogin = new UserLogin(id, login, password);
    }

    public User(UserLogin userLogin, UserInfo userInfo) {
        this.userInfo =userInfo;
        this.userLogin = userLogin;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

}
