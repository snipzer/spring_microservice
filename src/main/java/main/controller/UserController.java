package main.controller;

import main.dto.UserDto;
import main.entity.Tracking;
import main.service.UserService;
import main.util.DtoUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Tracking> createUser(@ModelAttribute Tracking tracking) {
        // TODO Encrypter le mot de passe
        return new ResponseEntity<>(this.userService.save(tracking), HttpStatus.OK);
    }

    @PostMapping("/login")
    public void login() {
        // TODO Créer le login dans toutes les couches
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> getUser(@RequestParam Long id) {
        Optional<Tracking> user = this.userService.findById(id);
        UserDto userDto = new UserDto(user.orElse(null));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(DtoUtil.createUserListDto(this.userService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/user/update")
    public ResponseEntity<UserDto> updateUser(@ModelAttribute Tracking tracking) {
        return new ResponseEntity<>(new UserDto(this.userService.updateEntity(tracking)), HttpStatus.OK);
    }

    @PostMapping("/user/delete")
    public ResponseEntity deleteUser(@RequestParam Long id) {
        this.userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
