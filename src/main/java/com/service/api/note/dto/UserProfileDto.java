package com.service.api.note.dto;

import com.service.api.note.entity.Gender;
import com.service.api.note.entity.Role;
import com.service.api.note.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {

    private String username;
    private String firstName;
    private String lastName;
    private Role role;
    private Gender gender;
    private Date dateOfBirth;
    private Status status;
}
