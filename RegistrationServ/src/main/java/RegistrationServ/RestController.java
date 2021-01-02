package RegistrationServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/Auth")
public class RestController {
    //TODO сделать обратно приватным
    public ArrayList<User> usersArr;
    public String testPost;
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

    public boolean addUser(String login,String password,String name,String lastName,int age,String location) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=registration", "postgres", "plami");
            PreparedStatement stLog = c.prepareStatement("insert into users (login, password) values (?, ?);");
            stLog.setString(1, login);
            stLog.setString(2, password);
            stLog.execute();
            PreparedStatement stInf = c.prepareStatement("insert into users (name,secondName,age,location) values (?,?,?,?);");
            stInf.setString(1, name);
            stInf.setString(2, lastName);
            stInf.setInt(3, age);
            stInf.setString(4, location);
            stInf.execute();
        } catch (ClassNotFoundException e) {
            return false;
        } catch (SQLException e) {
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

    //TODO Нужно добавить медод добавления пользователей в таблицу и использовать метод Refresh

    @RequestMapping(
        value = "/sendUsers",
        method = RequestMethod.POST ,
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody() User newUser) {
        User newUserMethod = new User(0,
                newUser.getUserLogin().getLogin(),
                newUser.getUserLogin().getPassword(),
                newUser.getUserInfo().getName(),
                newUser.getUserInfo().getLastName(),
                newUser.getUserInfo().getAge(),
                newUser.getUserInfo().getLocation());
        addUser(newUser.getUserLogin().getLogin(),
                newUser.getUserLogin().getPassword(),
                newUser.userInfo.getName(),
                newUser.getUserInfo().getLastName(),
                newUser.getUserInfo().getAge(),
                newUser.userInfo.location);
        refresh();
//       return "Ok";
    }
    @RequestMapping(
            value = "/Test",
            method = RequestMethod.POST ,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public String test(@RequestBody() String test) {
        testPost = test;
        return testPost;
    }

//  @RequestMapping(
//            value = "/sendInfo",
//            method = RequestMethod.GET)
//    public ArrayList sendInfo() {
//        return user.userInfo;
//    }
}
