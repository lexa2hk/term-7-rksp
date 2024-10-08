package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-fullnames")
@RequiredArgsConstructor
public class UserFullnameController {

    private final UserFullnameRepository userFullnameRepository;
    private final UserClient userClient;

    @GetMapping("/{userId}")
    public UserFullname getUserFullname(@PathVariable("userId") Long userId) {
        userFullnameRepository.deleteAll();
        List<User> allUsers = userClient.getAllUsers();
        userFullnameRepository.saveAll(
                List.of(
                        UserFullname.builder()
                                .userId(allUsers.get(0).getId())
                                .fullname("username1_fullname")
                                .build(),
                        UserFullname.builder()
                                .userId(allUsers.get(1).getId())
                                .fullname("username2_fullname")
                                .build(),
                        UserFullname.builder()
                                .userId(allUsers.get(2).getId())
                                .fullname("username3_fullname")
                                .build()
                )
        );
        return userFullnameRepository.findByUserId(userId + 3);
    }
}
