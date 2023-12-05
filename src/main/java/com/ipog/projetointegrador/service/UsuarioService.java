package com.ipog.projetointegrador.service;
import com.ipog.projetointegrador.model.Usuario;
import com.ipog.projetointegrador.repository.UsuarioRepository;
import com.ipog.projetointegrador.util.PasswordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cria um usuario
    public ResponseEntity<String> createUsuario(Usuario usuario) {
        Optional<Usuario> searchUsuario = usuarioRepository.findByEmailOrLogin(usuario.getEmail(), usuario.getLogin());

        if(searchUsuario.isPresent()) {
            Usuario existingUsuario = searchUsuario.get();
            if(Objects.equals(existingUsuario.getLogin(), usuario.getLogin())) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Ja existe um usuario com este login, por favor, utilize um login unico.");
            }
            if(Objects.equals(existingUsuario.getEmail(), usuario.getEmail())) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Ja existe um usuario com este email, por favor, utilize um email unico.");
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body("Usuario criado com sucesso");
    }

    // Busca todos usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Busca um usuario por ID
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Busca um usuario pelo token
    public Optional<Usuario> getUsuarioByToken(String token) {
        return usuarioRepository.findByToken(token);
    }

    // Atualiza usuario
    public ResponseEntity<String> updateUsuario(Long id, Usuario usuarioDetails) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        Optional<Usuario> searchUsuario = usuarioRepository.findByEmailOrLoginAndIdIsNot(usuarioDetails.getEmail(), usuarioDetails.getLogin(), id);

        if(searchUsuario.isPresent()) {
            Usuario conflictingUsuario = searchUsuario.get();
           if(Objects.equals(conflictingUsuario.getLogin(), usuarioDetails.getLogin())) {
               return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Ja existe um usuario com este login, por favor, utilize um login unico.");
           }
            if(Objects.equals(conflictingUsuario.getEmail(), usuarioDetails.getEmail())) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Ja existe um usuario com este email, por favor, utilize um email unico.");
            }
        }

        if (usuario.isPresent()) {
            Usuario existingUsuario = usuario.get();

            existingUsuario.setLogin(usuarioDetails.getLogin());
            existingUsuario.setSenha(usuarioDetails.getSenha());
            existingUsuario.setStatus(usuarioDetails.getStatus());
            existingUsuario.setEmail(usuarioDetails.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body("Usuario atualizado com sucesso");
        }
        return null;
    }

    // Deleta todos usuarios
    public void deleteAllUsuarios() {
        usuarioRepository.deleteAll();
    }

    // Deleta usuario
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void logoutUsuario(String token) {
        Optional<Usuario> usuario = usuarioRepository.findByToken(token);
        if (usuario.isPresent()) {
            Usuario existingUsuario = usuario.get();
            existingUsuario.cleanToken();
        }
    }

    public Usuario loginUsuario(String login, String senha) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
        if (usuario.isPresent()) {
            Usuario existingUsuario = usuario.get();
            boolean senhaCorreta = PasswordProvider.verifyPassphrase(senha, existingUsuario.getSenha());
            if(senhaCorreta) {
                existingUsuario.setToken();
                return existingUsuario;
            }
            throw new Exception("Credenciais incorretas");
        }
        throw new Exception("Credenciais incorretas");
    }
}
