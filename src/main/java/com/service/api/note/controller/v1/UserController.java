package com.service.api.note.controller.v1;

import com.service.api.note.dto.ResponseMessageDto;
import com.service.api.note.dto.UserProfileDto;
import com.service.api.note.dto.UserSaveDto;
import com.service.api.note.facade.impl.UserFacadeImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserFacadeImpl userFacade;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('full')")
    public ResponseEntity<List<UserProfileDto>> getUsers() {
        return new ResponseEntity<>(userFacade.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('full')")
    public ResponseEntity<ResponseMessageDto> saveUser(@RequestBody UserSaveDto userDto) {
        userFacade.saveUser(userDto);
        return new ResponseEntity<>(
                new ResponseMessageDto("User was saved"),
                HttpStatus.OK);
    }

    @PostMapping("/profile")
    @PreAuthorize("hasAnyAuthority('default')")
    public ResponseEntity<ResponseMessageDto> saveUser(@RequestBody UserSaveDto userDto, Principal principal) {
        userFacade.updateYourself(userDto, principal.getName());
        return new ResponseEntity<>(
                new ResponseMessageDto("User was updated"),
                HttpStatus.OK);
    }

    @GetMapping("/{profile}")
    @PreAuthorize("hasAnyAuthority('default')")
    public ResponseEntity<UserProfileDto> getUserProfile(Principal principal) {
        return new ResponseEntity<>(
                userFacade.getUserProfile(principal.getName()),
                HttpStatus.OK);
    }
}
