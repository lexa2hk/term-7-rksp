package org.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "userClient", url = "${user.service.url}")
public interface UserClient {

    @GetMapping("/api/users/all")
    List<User> getAllUsers();
}