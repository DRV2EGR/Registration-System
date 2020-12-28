package RegistrationServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/Auth")
public class RestController {
    //TODO сделать обратно приватным
    public ArrayList<User> usersArr;

    //TODO replace refresh method

    /**
     * @return false if database not found else return false
     */
    public boolean refresh (){
        usersArr.clear();
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/postgres?currentSchema=registration",
                            "postgres",
                            "plami");
            Statement stUsrLgn = c.createStatement();
            Statement stUsrInf = c.createStatement();
            ResultSet resultUserLogin = stUsrLgn.executeQuery("select id, login, password from users");
            ResultSet resultUserInfo = stUsrInf.executeQuery("select users_info.name, secondname, age, location from users_info");
            while(resultUserLogin.next()){
                resultUserInfo.next();
                usersArr.add(new User(
                        new UserLogin(
                            resultUserLogin.getInt("id"),
                            resultUserLogin.getString("login"),
                            resultUserLogin.getString("password")),
                        new UserInfo(
                                resultUserInfo.getString("name"),
                                resultUserInfo.getString("secondName"),
                                resultUserInfo.getInt("age"),
                                resultUserInfo.getString("location"))));
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
            return false;
        }
        catch (SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
    }



    @Autowired
    public RestController(){
        usersArr = new ArrayList<>();
    }

    @RequestMapping(
            value = "/sendUsers",
            method = RequestMethod.GET)
    public ArrayList<User> sendUsers() {
            refresh();
            return usersArr;
    }

//    @RequestMapping(
//        value = "/sendUsers",
//        method = RequestMethod.POST)
//    public String createUser(@RequestBody User newUser) {
//        usersArr.add(newUser);
//        return "Ok";
//    }


//  @RequestMapping(
//            value = "/sendInfo",
//            method = RequestMethod.GET)
//    public ArrayList sendInfo() {
//        return user.userInfo;
//    }
}
