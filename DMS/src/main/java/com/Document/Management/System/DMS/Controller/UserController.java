package com.Document.Management.System.DMS.Controller;


import com.Document.Management.System.DMS.DTO.UserRegisterationDto;
import com.Document.Management.System.DMS.Model.User;
import com.Document.Management.System.DMS.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/register")
//    public User register(@RequestBody User user){
//        return userService.register(user);
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterationDto userDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        User registeredUser = userService.register(userDTO);
        return ResponseEntity.ok(registeredUser);
    }


    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }

}
