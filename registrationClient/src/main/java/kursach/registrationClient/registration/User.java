package kursach.registrationClient.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    UserLogin userLogin;
    UserInfo userInfo;

    @JsonCreator
    public User(@JsonProperty("login") String login, @JsonProperty("password") String password,
                @JsonProperty("name")String name,@JsonProperty("lastName") String lastName, @JsonProperty("age")int age,
                @JsonProperty("location") String location){
        this.userInfo=new UserInfo(name, lastName, age, location);
        this.userLogin = new UserLogin(login, password);
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }


}
