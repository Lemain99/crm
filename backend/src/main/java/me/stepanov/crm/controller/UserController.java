package me.stepanov.crm.controller;

import me.stepanov.crm.domain.User;
import me.stepanov.crm.dto.UserDto;
import me.stepanov.crm.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/create")
    public UserDto create(@RequestBody UserDto userDto) {
        return userDetailsService.create(userDto);
    }

    @GetMapping("/delete")
    public ResponseEntity delete(Long id) {
        userDetailsService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody UserDto userDto){
        userDetailsService.update(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byId")
    public User getById(Long id){
        return userDetailsService.getById(id);
    }

    @GetMapping("/byAll")
    public List<UserDto> getAll(){
        return userDetailsService.getAll();
    }
}
