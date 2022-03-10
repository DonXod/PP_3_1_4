package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model) {
        model.addAttribute("usersInfo", userService.getAllUsers());
        return "users";
    }


    @GetMapping("/admin/users/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping("/admin/users")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "inputRoles", required = false) Long[] inputRoles) {
        Set<Role> temp = new HashSet<>();
        if (inputRoles == null) {
            temp.add(roleService.getRoleByRoleName("ROLE_USER"));
            user.setRoleSet(temp);
        } else {
            for(Long i: inputRoles) {
                temp.add(roleService.getRoleById(i));
            }
            user.setRoleSet(temp);
        }
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}")
    public String userPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("usersInfo",userService.getUserById(id));
        return "upage";
    }

    @GetMapping("/admin/users/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("usersInfo", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("/admin/users/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") long id,
                             @RequestParam(value = "inputRoles", required = false) Long[] inputRoles) {
        Set<Role> temp = new HashSet<>();
        if (inputRoles == null) {
            temp.add(roleService.getRoleByRoleName("ROLE_USER"));
            user.setRoleSet(temp);
        } else {
            for(Long i: inputRoles) {
                temp.add(roleService.getRoleById(i));
            }
            user.setRoleSet(temp);
        }
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/clean")
    public String clean() {
        userService.cleanUsersTable();
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/users/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/user")
    public String userGetPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("usersInfo", userService.getUserByUsername(userDetails.getUsername()));
        return "user";
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

}
