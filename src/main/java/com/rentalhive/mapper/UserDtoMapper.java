package com.rentalhive.mapper;

import com.rentalhive.domain.Role;
import com.rentalhive.domain.User;
import com.rentalhive.dto.request.RequestUserDto;
import com.rentalhive.dto.response.ResponseUserDto;

import java.util.ArrayList;
import java.util.List;

public class UserDtoMapper {

    private UserDtoMapper() {
    }

    public static User toEntity(RequestUserDto userDto) {
        List<Role> roles = new ArrayList<>();
        if(userDto.getAuthorities() != null){
            for (String role : userDto.getAuthorities()) {
                roles.add(Role.builder().name(role).build());
            }
        }
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .roles(roles)
                .organization(
                        OrganizationDtoMapper.toEntity(userDto.getOrganization())
                )
                .build();
    }

    public static ResponseUserDto toDto(User user) {
        return ResponseUserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .verifiedAt(user.getVerifiedAt())
                .authorities(user.getRoles().stream().map(Role::getName).toList())
                .organizationName(user.getOrganization().getName())
                .build();
    }
}
