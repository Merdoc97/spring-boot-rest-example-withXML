package com.example.controller;

import com.example.model.Users;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Igor
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Collection<Users> getUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Users getByName(@RequestParam("name") String userName) {
        return userService.getByName(userName);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Users getUserId(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void addUser(@RequestParam("name") String userName) {
        userService.addUser(userName);
    }
}
