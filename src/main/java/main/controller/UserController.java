package main.controller;

import main.dto.UserDto;
import main.entity.User;
import main.service.UserService;
import main.util.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@ModelAttribute User user) {
        // TODO Encrypter le mot de passe
        return new ResponseEntity<>(this.userService.save(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public void login() {
        // TODO Créer le login dans toutes les couches
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> getUser(@RequestParam Long id) {
        Optional<User> user = this.userService.findById(id);
        UserDto userDto = new UserDto(user.orElse(null));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(DtoUtil.createUserListDto(this.userService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/user/update")
    public ResponseEntity<UserDto> updateUser(@ModelAttribute User user) {
        return new ResponseEntity<>(new UserDto(this.userService.updateEntity(user)), HttpStatus.OK);
    }

    @PostMapping("/user/delete")
    public ResponseEntity deleteUser(@RequestParam Long id) {
        this.userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
