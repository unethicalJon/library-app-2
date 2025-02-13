package com.example.library.controller;

import com.example.library.dto.general.EntityIdDto;
import com.example.library.dto.user.UserDto;
import com.example.library.dto.user.SimpleUserDto;
import com.example.library.entity.User;
import com.example.library.service.UserService;
import com.example.library.util.constants.RestConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(RestConstants.UserController.BASE)
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(RestConstants.UserController.SIGN_UP)
    public ResponseEntity<EntityIdDto> postUser(@RequestBody UserDto addUserRequest) {
        User user = userService.postUser(addUserRequest);
        return new ResponseEntity<>(EntityIdDto.of(user.getId()), HttpStatus.CREATED);
    }

    @GetMapping(RestConstants.ID_PATH)
    public ResponseEntity<SimpleUserDto> getProfile(@PathVariable(value = RestConstants.ID) Long id) {
        SimpleUserDto userDto = userService.getProfile(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping(RestConstants.UserController.UPDATE)
    public ResponseEntity<User> updateUserProfile(@PathVariable(value = RestConstants.ID) Long id, @RequestBody SimpleUserDto updatedUser) {
        User user = userService.updateUserProfile(id, updatedUser);
        return new ResponseEntity(EntityIdDto.of(user.getId()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping(RestConstants.UserController.PASSWORD_CHANGE)
    public ResponseEntity<User> updateUserPassword(@PathVariable(value = RestConstants.ID) Long id,
                                                        @RequestBody User adminUpdatedUserPassword) {
        try {
            User updatedUser = userService.updateUserPassword(id, adminUpdatedUserPassword);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping(RestConstants.UserController.ACTIVATE_USER)
    public ResponseEntity<String> activateUser(@PathVariable(value = RestConstants.ID) Long id) {
        userService.activateUser(id);
        return ResponseEntity.ok("User activated successfully.");
    }
}





