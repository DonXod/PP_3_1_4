package ru.kata.spring.boot_security.demo.configs;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class StartDataBase {
    private UserService userService;
    private RoleService roleService;

    public StartDataBase(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void initDB() {
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        User user = new User("alex@mail.ru","root","Alexey", "Naumov", (byte) 22);
        User admin = new User("admin@mail.ru","root","admin", "adminovich", (byte) 99);
        roleService.addRole(roleUser);
        roleService.addRole(roleAdmin);
        Set<Role> temp = new HashSet<>();
        temp.add(roleUser);
        user.setRoleSet(Set.of(roleUser));
        admin.setRoleSet(Set.of(roleUser, roleAdmin));
        userService.addUser(user);
        userService.addUser(admin);
    }
}
