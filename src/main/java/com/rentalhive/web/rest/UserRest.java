package com.rentalhive.web.rest;

import com.rentalhive.domain.User;
import com.rentalhive.dto.request.RequestUserDto;
import com.rentalhive.dto.response.ResponseUserDto;
import com.rentalhive.mapper.UserDtoMapper;
import com.rentalhive.service.impl.UserServiceImpl;
import com.rentalhive.utils.Response;
import com.rentalhive.utils.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserRest {

    private final UserServiceImpl userService;
    @Autowired
    public UserRest(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Response<List<ResponseUserDto>>> findAll(){
        Response<List<ResponseUserDto>> response = new Response<>();
        List<User> users = userService.findAll();
        List<ResponseUserDto> usersDto = users.stream().map(UserDtoMapper::toDto).toList();
        response.setResult(usersDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<ResponseUserDto>> save(@RequestBody @Valid RequestUserDto userDto){
        Response<ResponseUserDto> response = new Response<>();
        try {
            User user = UserDtoMapper.toEntity(userDto);
            response.setResult(UserDtoMapper.toDto(userService.save(user)));
            response.setMessage("User has been saved successfully");
        } catch (ValidationException e) {
            response.setErrors(List.of(e.getCustomError()));
            response.setMessage("User has not been saved");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
