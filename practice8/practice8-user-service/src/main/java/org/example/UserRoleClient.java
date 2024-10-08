package org.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userRoleClient", url = "${user.role.service.url}")
public interface UserRoleClient {

    @GetMapping("/api/user-roles/{userId}")
    UserRole getUserRole(@PathVariable("userId") Long userId);
}
