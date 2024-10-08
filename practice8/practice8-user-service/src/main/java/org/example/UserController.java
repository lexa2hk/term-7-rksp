package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserFullnameClient userFullnameClient;
    private final UserRoleClient userRoleClient;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        userRepository.deleteAll();
        userRepository.saveAll(
                List.of(
                        User.builder()
                                .username("username1")
                                .password("password1")
                                .build(),
                        User.builder()
                                .username("username2")
                                .password("password2")
                                .build(),
                        User.builder()
                                .username("username3")
                                .password("password3")
                                .build()
                )
        );
        return userRepository.findAll();
    }

    @GetMapping("/{userId}/info")
    public UserInfoDto getUserInfo(@PathVariable Long userId) {
        return new UserInfoDto(
                userFullnameClient.getUserFullname(userId),
                userRoleClient.getUserRole(userId)
        );
    }
}