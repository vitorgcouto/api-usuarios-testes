package vitorgcouto.api.usuarios.testes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }
    @Test
    void deveRetornarUsuarioQuandoExiste() {
        User user = new User("Ana", "ana@email.com");
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));


        User resultado = userService.getUserById(1L);


        assertEquals("Ana", resultado.getName());
        verify(userRepository).findById(1L);
    }

}
