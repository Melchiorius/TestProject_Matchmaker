package com.boost.test_project.lasta_games.matchmaker.controller;

import com.boost.test_project.lasta_games.matchmaker.model.Group;
import com.boost.test_project.lasta_games.matchmaker.model.User;
import com.boost.test_project.lasta_games.matchmaker.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private GroupService groupService;

/*
    @GetMapping("/users")
    public List<User> showAllUsers(){
        List<User> allUsers = userService.getAllUser();
        return allUsers;
    }
*/

/*
    @GetMapping("/users/{id}")
    public User showVehicle(@PathVariable int id){
        User vehicle = userService.getVehicle(id);
        if(vehicle == null){
            throw new NoSuchUserException("There is no user with ID = "+ id +" in DataBase");
        }
        return vehicle;
    }
*/

    @PostMapping("/users")
    public List<Group> addNewUser(@RequestBody User user){
        groupService.addUser(user);
        return groupService.getAllGroups();
    }

    @RequestMapping("/")
    public String showFirstView(){
        return "Matchmaker is ready!";
    }

/*
    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        userService.saveUser(vehicle);
        return vehicle;
    }
*/

/*
    @DeleteMapping ("/users/{id}")
    public String deleteUser(@PathVariable int id){
        User user = userService.getUser(id);
        if(user == null){
            throw new NoSuchUserException("There is no user with ID = "+ id +" in DataBase");
        }
        userService.deleteUser(id);
        return "User with ID = "+id+" was removed from database";
    }
*/

}