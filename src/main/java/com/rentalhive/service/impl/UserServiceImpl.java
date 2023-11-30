package com.rentalhive.service.impl;

import com.rentalhive.domain.Organization;
import com.rentalhive.domain.User;
import com.rentalhive.repository.OrganizationRepository;
import com.rentalhive.repository.UserRepository;
import com.rentalhive.service.UserService;
import com.rentalhive.utils.CustomError;
import com.rentalhive.utils.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, OrganizationRepository organizationRepository) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }
    @Override
    public User save(User user) throws ValidationException {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new ValidationException(new CustomError("Email","Email already exists"));
        Optional<Organization> optionalOrganization = organizationRepository.findByName(user.getOrganization().getName());
        if(optionalOrganization.isEmpty()){
            organizationRepository.save(user.getOrganization());
        }else user.setOrganization(optionalOrganization.get());
        return userRepository.save(user);
    }
    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
