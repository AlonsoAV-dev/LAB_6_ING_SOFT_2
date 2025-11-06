package sebas.lab.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Test
    public void testGetUserName_UsuarioExiste() {
        
        UserRepository repo = Mockito.mock(UserRepository.class);
        
        when(repo.findById("123")).thenReturn(new User("123", "Juan"));

        UserService service = new UserService(repo);

        String name = service.getUserName("123");

        assertEquals("Juan", name);
        verify(repo, times(1)).findById("123");
    }

    @Test
    public void testGetUserName_UsuarioNoExiste() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        when(repo.findById("999")).thenReturn(null);

        UserService service = new UserService(repo);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            service.getUserName("999");
        });

        assertEquals("User not found", ex.getMessage());
        verify(repo, times(1)).findById("999");
    }
}
