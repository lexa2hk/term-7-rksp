import org.example.User;
import org.example.UserController;
import org.example.UserFlights;
import org.example.FlightsServiceClient;
import org.example.UserInfoDto;
import org.example.UserRepository;
import org.example.UserRole;
import org.example.UserRoleClient;
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
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FlightsServiceClient flightsServiceClient;

    @Mock
    private UserRoleClient userRoleClient;

    @InjectMocks
    private UserController userController;


    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(User.builder().id(1L).build()));

        List<User> actualUsers = userController.getAllUsers();

        assertThat(actualUsers).hasSize(1);
        assertThat(actualUsers.getFirst().getId()).isEqualTo(1L);
    }

    @Test
    void getUserInfo() {
        when(flightsServiceClient.getUserScheduledFlights(anyLong()))
                .thenReturn(UserFlights.builder().flightCode("testFullName").build());
        when(userRoleClient.getUserRole(anyLong()))
                .thenReturn(UserRole.builder().roleName("ROLENAME").build());

        UserInfoDto actualInfo = userController.getUserInfo(1L);

        assertThat(actualInfo).isNotNull();
        assertThat(actualInfo.fullname().getFlightCode()).isEqualTo("testFullName");
        assertThat(actualInfo.userRole().getRoleName()).isEqualTo("ROLENAME");
    }
}
