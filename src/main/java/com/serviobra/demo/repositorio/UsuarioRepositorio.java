package com.serviobra.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serviobra.demo.modelo.Usuario;
import java.util.List;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
    Usuario findByVerificationToken(String token);
    List<Usuario> findByNombreContainingIgnoreCaseOrUsernameContainingIgnoreCase(String nombre, String username);

}


