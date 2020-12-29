package kursach.registrationClient.registration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//TODO дальше пусть выдаст текущие данные и создай методы для обновления каждого параметра (ну и меню к нему)

public class Client {

    public static ArrayList<User> getUser(){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode userA = ClientBuilder.newBuilder()
                .register(JacksonJsonProvider.class)
                .build()
                .target("http://localhost")
                .path("Auth/sendUsers")
                .request("application/json")
                .header("Content-Type","application/json")
                .get(JsonNode.class);
        return mapper.convertValue(
                userA,
                new TypeReference<ArrayList<User>>(){}
        );
    }

    public static void main(String[] args) throws IOException {

        System.out.print("If you new user press N, or if you want LogIn press L: ");
        Scanner sc =  new Scanner(System.in);
        String answer = sc.nextLine();

        ArrayList<User> usersDatabase;
        User currentUser = null;

        if(answer.toUpperCase().equals("L")){
            //get Users Data base:
            usersDatabase = getUser();
            int i = 5;
            while(i > 0) {
                System.out.println("Input your login: ");
                String login = sc.nextLine();
                System.out.println("Input your password: ");
                String password = sc.nextLine();
                currentUser = Auth.Authentication(usersDatabase, login, password);
                i--;
                if(currentUser != null) {

                }
                else if(currentUser == null && i != 0){
                    System.out.println("Erorr! Invalid user name or password. Please repeat your enter. You have " + i +
                            " attempts");
                }
                else if(currentUser == null && i == 0){
                    System.out.println("You don`t gave any more attepts left. Try again later.");
                    return;
                }
            }
            System.out.println("Authentication successful!");
            //TODO выдать данные пользователя

        }
        else if(answer.toUpperCase().equals("N")){
            System.out.println("Input user`s login: ");
            String newLogin = sc.nextLine();
            System.out.println("Input user`s password: ");
            String newPassword = sc.nextLine();
            System.out.println("Input user`s name: ");
            String newName = sc.nextLine();
            System.out.println("Input user`s LastName: ");
            String newLastName = sc.nextLine();
            System.out.println("Input user`s Age: ");
            int newAge = sc.nextInt();
            sc.reset();
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Input user`s Location: ");
            String newLocation = sc1.nextLine();

            User newUser = new User(newLogin, newPassword, newName, newLastName, newAge, newLocation);
            ClientBuilder.newBuilder()
                    .register(JacksonJsonProvider.class)
                    .build()
                    .target("http://localhost")
                    .path("Auth/sendUsers")
                    .request("application/json")
                    .header("Content-Type","application/json")
                    .post(Entity.entity(newUser, MediaType.APPLICATION_JSON), User.class);
            System.out.println("Debug");
        }
        else{
            System.out.println("Sorry! Connection is lost. Try again later...");
        }
    }
}
