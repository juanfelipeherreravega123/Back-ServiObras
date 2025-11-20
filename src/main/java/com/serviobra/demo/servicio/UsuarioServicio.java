package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.Usuario;
import com.serviobra.demo.repositorio.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    

    // Serivicio de Email- cambiar luego de implementacion
    @Autowired(required = false)
    private JavaMailSender mailSender;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    // ============================
    //  REGISTRO
    // ============================
    public Usuario registrar(String nombre, String apellido, String email, String password, String username) {

        if (usuarioRepositorio.findByEmail(email) != null) {
            throw new RuntimeException("El correo ya está registrado");
        }

        if (usuarioRepositorio.findByUsername(username) != null) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setEmail(email);
        u.setUsername(username);
        u.setFecha_registro(new Timestamp(System.currentTimeMillis()));
        u.setRol("cliente");
        u.setEstado("activo");

        String hash = passwordEncoder.encode(password);
        u.setContraseñaHash(hash);

        
        // Código de verificación
        String codigo = generarCodigoVerificacion();
        u.setVerificationToken(codigo);
        u.setVerified(false);

        usuarioRepositorio.save(u);

        enviarCodigo(email, codigo);

        return u;
    }

    private String generarCodigoVerificacion() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private void enviarCodigo(String email, String codigo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(email);
        mensaje.setSubject("Verificación de cuenta - ServiObras");
        mensaje.setText("Tu código de verificación es: " + codigo);

        mailSender.send(mensaje);
    }

    // ============================
    //  LOGIN
    // ============================
    public Usuario login(String username, String password) {
        Usuario u = usuarioRepositorio.findByUsername(username);

        if (u == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

       // Comparación con BCrypt
    if (!passwordEncoder.matches(password, u.getContraseñaHash())) {
        throw new RuntimeException("Contraseña incorrecta");
    }

        if (!Boolean.TRUE.equals(u.getVerified())) {
            throw new RuntimeException("Debes verificar tu correo antes de iniciar sesión");
        }

        return u;
    }

    // ============================
    //  VERIFICAR CÓDIGO
    // ============================
    public boolean verificar(String email, String codigo) {
        Usuario u = usuarioRepositorio.findByEmail(email);

        if (u == null) return false;

        if (!codigo.equals(u.getVerificationToken())) return false;

        u.setVerified(true);
        u.setVerificationToken(null);
        usuarioRepositorio.save(u);

        return true;
    }
}
