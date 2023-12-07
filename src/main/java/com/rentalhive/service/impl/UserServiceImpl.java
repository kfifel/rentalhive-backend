package com.rentalhive.service.impl;

import com.rentalhive.domain.Organization;
import com.rentalhive.domain.Role;
import com.rentalhive.domain.User;
import com.rentalhive.dto.RoleDto;
import com.rentalhive.repository.OrganizationRepository;
import com.rentalhive.repository.UserRepository;
import com.rentalhive.service.RoleService;
import com.rentalhive.service.UserService;
import com.rentalhive.utils.CustomError;
import com.rentalhive.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final RoleService roleService;

    @Override
    public User save(User user) throws ValidationException {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new ValidationException(new CustomError("Email","Email already exists"));
        Optional<Organization> optionalOrganization = organizationRepository.findByName(user.getOrganization().getName());
        if(optionalOrganization.isEmpty())
            organizationRepository.save(user.getOrganization());
        else
            user.setOrganization(optionalOrganization.get());
        final List <Role> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roleService.findByName(role.getName()).ifPresent(roles::add));
        user.setRoles(roles);
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

    @Override
    public void revokeRole(Long id, List<RoleDto> roles) throws ValidationException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<Role> roleList = new ArrayList<>();
            roles.forEach(roleDto -> roleService.
                findByName(roleDto.getName()).ifPresent(roleList::add));

            if (new HashSet<>(user.getRoles()).containsAll(roleList)) {
                user.getRoles().removeAll(roleList);
                userRepository.save(user);
            } else {
                throw new ValidationException(CustomError.builder()
                        .field("roles")
                        .message("User does not have all specified roles.")
                        .build());
            }
        }
        else {
            throw new ValidationException(CustomError.builder()
                    .field("user id")
                    .message("User does not exist")
                    .build());
        }
    }

    @Override
    public void assigneRole(Long id, List<RoleDto> roles) throws ValidationException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<Role> roleList = new ArrayList<>();
            roles.forEach(roleDto -> roleService.findByName(roleDto.getName()).ifPresent(roleList::add));
            if(user.getRoles().stream().anyMatch(roleList::contains)) {
                throw new ValidationException(CustomError.builder()
                        .field("roles")
                        .message("User already has some of specified roles.")
                        .build());
            }
            user.getRoles().addAll(roleList);
            userRepository.save(user);
        }
    }

    @Override
    public List<String> getAuthorities() {
        return roleService.getALlRoles().stream().map(Role::getName).toList();
    }
}
