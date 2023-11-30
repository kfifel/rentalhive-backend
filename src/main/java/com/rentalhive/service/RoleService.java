package com.rentalhive.service;
import com.rentalhive.domain.Role;
import com.rentalhive.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    public Role save(Role role) throws ValidationException;
    public Role findByName(String name) ;
    public List<Role> getALlRoles();
    public void delete(Long id);

    public Role findById(Long id);

}
