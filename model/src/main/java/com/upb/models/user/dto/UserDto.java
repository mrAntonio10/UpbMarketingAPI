package com.upb.models.user.dto;

import com.upb.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class UserDto {
    private String id;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String rol;
    private String state;
    private String branchOfficeName;


    public UserDto(User user) {
        this.id = user.getId();
        this.fullname = user.getName() +" "+ user.getLastname();
        this.email = user.getEmail();
        this.rol = user.getRol().getName();
        this.state = user.getState();
    }

}
