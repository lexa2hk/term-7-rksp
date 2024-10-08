package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleRepository userRoleRepository;
    private final UserClient userClient;

    @GetMapping("/{userId}")
    public UserRole getUserRole(@PathVariable("userId") Long userId) {
        userRoleRepository.deleteAll();
        List<User> allUsers = userClient.getAllUsers();
        userRoleRepository.saveAll(
                List.of(
                        UserRole.builder()
                                .userId(allUsers.getFirst().getId())
                                .roleName(Role.USER)
                                .build(),
                        UserRole.builder()
                                .userId(allUsers.get(1).getId())
                                .roleName(Role.USER)
                                .build(),
                        UserRole.builder()
                                .userId(allUsers.getLast().getId())
                                .roleName(Role.USER)
                                .build()
                )
        );
        return userRoleRepository.findByUserId(userId + 6);
    }
}
