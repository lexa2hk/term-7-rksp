package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userFeignClient.getAllUsers();
    }

    @GetMapping("/{userId}/info")
    public UserInfoDto getUserInfo(@PathVariable Long userId) {
        return userFeignClient.getUserInfo(userId);
    }
}