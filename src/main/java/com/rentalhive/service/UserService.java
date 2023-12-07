package com.rentalhive.service;

import com.rentalhive.domain.User;
import com.rentalhive.dto.RoleDto;
import com.rentalhive.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    User save(User user) throws ValidationException;
    User update(User user);
    User delete(User user);
    List<User> findAll();

    void revokeRole(Long id, List<RoleDto> roles) throws ValidationException;

    void assigneRole(Long id, List<RoleDto> roles) throws ValidationException;

    List<String> getAuthorities();
}
