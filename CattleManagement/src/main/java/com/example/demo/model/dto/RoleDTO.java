package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private String roleId;

    private String roleName;

    private Set<AccountRoleDTO> roleSet;
}
