package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserFullnameControllerUnitTest {

    @Mock
    private UserFullnameRepository userFullnameRepository;
    @Mock
    private UserClient userClient;
    @InjectMocks
    private org.example.UserFullnameController userFullnameController;

    @Test
    void testGetUserFullname() {
        when(userClient.getAllUsers())
                .thenReturn(List.of(
                        User.builder()
                                .id(4L)
                                .build(),
                        User.builder()
                                .id(5L)
                                .build(),
                        User.builder()
                                .id(6L)
                                .build()
                ));
        when(userFullnameRepository.findByUserId(anyLong()))
                .thenReturn(UserFullname.builder()
                        .fullname("testFullName")
                        .build());

        UserFullname actualFullname = userFullnameController.getUserFullname(4L);

        assertThat(actualFullname.getFullname()).isEqualTo("testFullName");
    }
}