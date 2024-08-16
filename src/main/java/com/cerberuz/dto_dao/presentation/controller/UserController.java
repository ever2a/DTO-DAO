package com.cerberuz.dto_dao.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerberuz.dto_dao.presentation.dto.UserDto;
import com.cerberuz.dto_dao.service.interfaces.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    // Find All User
    @GetMapping("/find")
    public ResponseEntity<List<UserDto>> findAll() {
        return new ResponseEntity<>(this.iUserService.findAll(), HttpStatus.OK);
    }

    // Find By Id User
    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.iUserService.findById(id), HttpStatus.OK);
    }

    // Create User
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(this.iUserService.createUser(userDto), HttpStatus.CREATED);
    }

    // Update User
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        return new ResponseEntity<>(this.iUserService.updateUser(userDto, id), HttpStatus.OK);
    }

    // Delete User 42:50 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(this.iUserService.deleteUser(id), HttpStatus.NO_CONTENT);
    }
}
