package com.rentalhive.service;

import com.rentalhive.domain.User;
import com.rentalhive.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    public User save(User user) throws ValidationException;
    public User update(User user);
    public User delete(User user);
    public List<User> findAll();

}
