package org.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", url = "http://user-service:8080") // Совпадает с конфигами
public interface UserFeignClient {

    @GetMapping("/api/users/all")
    List<User> getAllUsers();

    @GetMapping("/api/users/{userId}/info")
    UserInfoDto getUserInfo(@PathVariable Long userId);
}
