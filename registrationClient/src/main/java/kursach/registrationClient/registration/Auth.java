package kursach.registrationClient.registration;

import java.util.ArrayList;


public class Auth {

    public static User Authentication(ArrayList<User> userArrayList, String login, String password){
        for(User user : userArrayList){
            if(user.userLogin.getLogin().equals(login)){
                if(user.userLogin.getPassword().equals(password)){
                    return user;
                }
                else
                    return null;
            }
            else
                return null;
        }
        return null;
    }
}
