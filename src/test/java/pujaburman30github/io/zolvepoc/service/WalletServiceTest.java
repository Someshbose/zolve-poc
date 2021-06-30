package pujaburman30github.io.zolvepoc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pujaburman30github.io.zolvepoc.model.User;
import pujaburman30github.io.zolvepoc.repo.UserRespository;
import pujaburman30github.io.zolvepoc.util.UserCreationException;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @Mock
    private UserRespository userRespository;

    @InjectMocks
    private WalletService service;

    @Test
    public void addTest(){
        User user =User.builder().id(1L).firstName("Somesh").balance(120).build();
        service.createUser(user);
        Mockito.verify(userRespository,Mockito.times(1)).save(user);
    }

    @Test
    public void addNegativeTest(){
        User user =User.builder().id(1L).firstName("Somesh").build();
        Assertions.assertThrows(UserCreationException.class,()->service.createUser(user));
        Mockito.verify(userRespository,Mockito.times(0)).save(user);
    }
}
