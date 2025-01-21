package com.example.userapi.service;

import com.example.userapi.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.validator.routines.EmailValidator;

@Service
public class UserServiceImpl implements UserService{
    List<User> userList = List.of(new User(
                    1,
                    "Elvis",
                    "Presley",
                    "elvis@presley.com",
                    1),
            new User(
                    2,
                    "John",
                    "Elton",
                    "john@elton.com",1),
            new User(
                    3,
                    "Lana ",
                    "Del Rey",
                    "lana@delrey.com",1)
    );
    ;
    int defaultStatus = 1;
    int deleteStatus = 0;


    public List<User> getUserList() {
        List<User> u = userList != null ? new ArrayList<>(userList) :  new ArrayList<>();
        /*if(u != null){
            u = u.stream().filter(i -> i.getStatus() != 0).collect(Collectors.toList());
        }*/

        return u;
    }

    public User addUser(User user) {
        Integer maxSize = userList == null ? 0 : userList.size();
        List<User> u = userList != null ? new ArrayList<>(userList) :  new ArrayList<>();

        if(user == null){
            return  null;
        }else{
            user = new User(
                    maxSize + 1 ,
                    user.firstName,
                    user.lastName,
                    user.email,
                    defaultStatus);
            u.add(user);
            System.out.println("adding user {}"+ maxSize);
            userList = u;

        }
        return user;
    }

    public User editUser(Integer userId, User user) {
        Integer maxSize = userList == null ? 0 : userList.size();
        List<User> u = userList != null ? new ArrayList<>(userList) :  new ArrayList<>();
        System.out.println("editing maxSize {}"+ maxSize);

        if(user == null || Objects.isNull(getUserById(userId))){
            return  null;
        }else{
            int index = IntStream.range(0, userList.size())
                    .filter(i -> userId.equals(userList.get(i).getId()))
                    .findFirst().orElse(-1);

            userList.get(index).setStatus(defaultStatus);
            userList.get(index).setEmail(user.getEmail());
            userList.get(index).setFirstName(user.getFirstName());
            userList.get(index).setLastName(user.getLastName());

            return userList.get(index);
        }
    }


    public User getUserById(Integer userId) {
        if(userId == 0){
            return  null;
        }else{
            return userList != null ? userList.stream().filter(u -> u.getId() == (userId)).collect(Collectors.toList()).get(0) : null;
        }
    }

    public boolean deleteUserById(Integer userId) {
        if(userId == null){
            return  false;
        }else{
             if(Objects.nonNull(getUserById(userId))){
                 int index = IntStream.range(0, userList.size())
                         .filter(i -> userId.equals(userList.get(i).getId()))
                         .findFirst().orElse(-1);
                 System.out.println("index "+ index);
                 if(index >=0){
                     userList.get(index).setStatus(deleteStatus);
                     return true;
                 }else{
                     return false;
                 }
             }else{
                 return false;
             }
        }
    }

    /*
    public User editUserById(String userId) {
        if(userId == null){
            userId = "1";
        }

        if(userId != null) {
            if (userId.equals("1")) {
                return new User(
                        "1",
                        "14 Flood St",
                        "New Orleads, LA70122",
                        "https://images.pexels.com/photos/388830/pexels-photo-388830.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                        5000.0,40000.0);
            }
            else if (userId.equals("2")) {
                System.out.println("pro"+userId);

                return new User(
                        "2",
                        "24 Washington Ave",
                        "New Orleads, LA70122",
                        "https://images.pexels.com/photos/259588/pexels-photo-259588.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", 4000.0,30000.0);
            }
            else if (userId.equals("3")) {
                return new User(
                        "3",
                        "289 Heampsted St",
                        "New Orleads, LA70122",
                        "https://images.pexels.com/photos/4526153/pexels-photo-4526153.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", 6000.0,35000.0);
            }
            else if (userId.equals("4")) {
                return new User(
                        "4",
                        "57 West Ave St",
                        "New Orleads, LA70122",
                        "https://images.pexels.com/photos/210617/pexels-photo-210617.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", 7000.0,45000.0);
            }
        }

        return  new User();
    }

     */
}