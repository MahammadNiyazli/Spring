package com.company.controller;

import com.company.dao.impl.UserRepository;
import com.company.dto.ResponseDTO;
import com.company.entity.User;
import com.company.dto.UserDTO;
import com.company.service.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    UserServiceInter userService;

    @GetMapping("/users/{id}")
    @CrossOrigin
    public ResponseEntity<ResponseDTO> getUser(@PathVariable("id") int id) throws Exception {

        List<UserDTO> userDTOS = new ArrayList<>();

        User user = userService.getById(id);
        userDTOS.add(new UserDTO(user));

        return  ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(userDTOS));
//        return  ResponseEntity.ok(userDTOS);
    }

    @DeleteMapping("/users/{id}")
    @CrossOrigin
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id) throws Exception {

        User user = userService.getById(id);

        userService.removeUser(id);
        return  ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(new UserDTO(user),"Successfuly deleted"));
//        return  ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/users")
    @CrossOrigin
    public ResponseEntity<ResponseDTO> getUsers(
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "suranme",required = false) String surname,
            @RequestParam(name = "age",required = false) Integer age
    ) throws Exception {

        List<User> users = userService.getAll(name,surname,age);
        List<UserDTO> userDTOS = new ArrayList<>();

        for(int i=0 ; i<users.size() ;i++){
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return  ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(userDTOS));
//        return  ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/foo")
    @CrossOrigin
    public ResponseEntity<ResponseDTO> foo(
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "suranme",required = false) String surname,
            @RequestParam(name = "age",required = false) Integer age
    ) throws Exception {

        List<User> users = userService.getAll(name,surname,age);
        List<UserDTO> userDTOS = new ArrayList<>();

        for(int i=0 ; i<users.size() ;i++){
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return  ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(userDTOS));
//        return  ResponseEntity.ok(userDTOS);
    }

    @PostMapping("/users")
    @CrossOrigin
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO) throws Exception {

       User u = new User();
       u.setName(userDTO.getName());
       u.setSurname(userDTO.getSurname());
       u.setPassword(userDTO.getPassword());
       u.setEmail("turan@mail.ru");
       u.setAddress("baku");
       u.setPhone("06212154");

       userService.addUser(u);

       UserDTO userDTOnew = new UserDTO();
       userDTOnew.setId(u.getId());
       userDTOnew.setName(u.getName());
       userDTOnew.setSurname(u.getSurname());
       userDTOnew.setPassword(u.getPassword());

        return  ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(userDTOnew));
//        return  ResponseEntity.ok(userDTOS);
    }

}
