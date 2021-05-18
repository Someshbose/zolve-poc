package pujaburman30github.io.zolvepoc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pujaburman30github.io.zolvepoc.model.User;
import pujaburman30github.io.zolvepoc.repo.UserRespository;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @Mock
    private UserRespository userRespository;

    @InjectMocks
    private WalletService service;

    @Test
    public void addTest(){
        User user =User.builder().id(1L).firstName("Somesh").build();
        service.createUser(user);
        Mockito.verify(userRespository,Mockito.times(1)).save(user);
    }
}
