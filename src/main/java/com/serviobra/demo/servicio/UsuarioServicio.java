package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.Usuario;
import com.serviobra.demo.repositorio.UsuarioRepositorio;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
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

        String codigo = generarCodigoVerificacion();
        u.setVerificationToken(codigo);
        u.setVerified(false);

        usuarioRepositorio.save(u);

        enviarCodigo(email, nombre + " " + apellido, codigo);

        return u;
    }

    private String generarCodigoVerificacion() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private void enviarCodigo(String email, String nombre, String codigo) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String html = """
                <div style="font-family: Arial; padding:20px; background:#f7f7f7;">
                  <div style="max-width:600px;margin:auto;background:#ffffff;border-radius:10px;padding:30px;border:1px solid #ddd;">
                    <h2 style="text-align:center;color:#2e7d32;margin-bottom:10px;">SERVIOBRA</h2>
                    <p>Hola <strong>%s</strong>,</p>
                    <p>Gracias por registrarte en <strong>ServiObra</strong>.</p>
                    <p>Tu código de verificación es:</p>
                    <h1 style="text-align:center;letter-spacing:10px;">%s</h1>
                    <p>Ingresa este código en la plataforma para activar tu cuenta.</p>
                    <hr>
                    <p style="text-align:center;font-size:12px;color:#777;">
                      © 2025 ServiObras. Todos los derechos reservados.
                    </p>
                  </div>
                </div>
                """.formatted(nombre, codigo);

            helper.setTo(email);
            helper.setSubject("Verificación de cuenta - ServiObras");
            helper.setText(html, true);

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error enviando correo: " + e.getMessage());
        }
    }

    // ============================
    //  LOGIN
    // ============================
    public Usuario login(String username, String password) {
        Usuario u = usuarioRepositorio.findByUsername(username);

        if (u == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!passwordEncoder.matches(password, u.getContraseñaHash())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        if (!Boolean.TRUE.equals(u.getVerified())) {
            throw new RuntimeException("NO_VERIFICADO");
        }

        return u;
    }

    // ============================
    //  VERIFICAR CÓDIGO
    // ============================
    public boolean verificar(String emailOrUsername, String codigo) {
        // primero intenta por email
        Usuario u = usuarioRepositorio.findByEmail(emailOrUsername);

        // si no lo encuentra, intenta por username
        if (u == null) {
            u = usuarioRepositorio.findByUsername(emailOrUsername);
        }

        if (u == null) {
            return false;
        }
        if (!codigo.equals(u.getVerificationToken())) {
            return false;
        }

        u.setVerified(true);
        u.setVerificationToken(null);
        usuarioRepositorio.save(u);

        return true;
    }
    
    // ============================
//  CREAR USUARIO (ADMIN PANEL)
// ============================
public Usuario crearDesdeAdmin(
        String nombre,
        String apellido,
        String email,
        String username,
        String password,
        String rol
) {

    // Validaciones
    if (nombre == null || apellido == null || email == null ||
        username == null || password == null || rol == null) {
        throw new RuntimeException("Todos los campos son obligatorios");
    }

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
    u.setRol(rol);
    u.setEstado("activo");
    u.setFecha_registro(new Timestamp(System.currentTimeMillis()));

    // Hash de contraseña
    String hash = passwordEncoder.encode(password);
    u.setContraseñaHash(hash);

    // Admin crea usuario → ya verificado
    u.setVerificationToken(null);
    u.setVerified(true);

    usuarioRepositorio.save(u);

    return u;
  }
}