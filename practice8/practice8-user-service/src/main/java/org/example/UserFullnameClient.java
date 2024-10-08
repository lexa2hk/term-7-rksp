package org.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userFullnameClient", url = "${user.fullname.service.url}")
public interface UserFullnameClient {

    @GetMapping("/api/user-fullnames/{userId}")
    UserFullname getUserFullname(@PathVariable("userId") Long userId);
}

