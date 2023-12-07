package com.rentalhive.service;
import com.rentalhive.domain.Role;
import com.rentalhive.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    Role save(Role role) throws ValidationException;
    Optional<Role> findByName(String name) ;
    List<Role> getALlRoles();
    void delete(Long id);

    Role findById(Long id);

}
