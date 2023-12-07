package com.rentalhive.web.rest;

import com.rentalhive.domain.User;
import com.rentalhive.dto.RoleDto;
import com.rentalhive.dto.request.RequestUserDto;
import com.rentalhive.dto.response.ResponseUserDto;
import com.rentalhive.mapper.UserDtoMapper;
import com.rentalhive.service.impl.UserServiceImpl;
import com.rentalhive.utils.Response;
import com.rentalhive.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRest {

    private final UserServiceImpl userService;


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

    @GetMapping("/authorities")
    public ResponseEntity<List<String>> getAuthorities() {
        return ResponseEntity.ok().body(userService.getAuthorities());
    }

    @PostMapping("/revokeRole/{id}")
    public ResponseEntity<Response<?>> revokeRole(@PathVariable("id") Long id, @RequestBody List<RoleDto> roles){
        Response<?> response = new Response<>();
        try {
            userService.revokeRole(id, roles);
            response.setMessage("Role has been revoked successfully");
        } catch (ValidationException e) {
            response.setErrors(List.of(e.getCustomError()));
            response.setMessage("Role has not been revoked");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/assigneRole/{id}")
    public ResponseEntity<Response<?>> assigneRole(@PathVariable("id") Long id, @RequestBody List<RoleDto> roles){
        Response<?> response = new Response<>();
        try {
            userService.assigneRole(id, roles);
            response.setMessage("Role has been assigned successfully");
        } catch (ValidationException e) {
            response.setErrors(List.of(e.getCustomError()));
            response.setMessage("Role has not been assigned");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
