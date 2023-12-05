package com.ipog.projetointegrador.component;

import com.ipog.projetointegrador.model.Usuario;
import com.ipog.projetointegrador.repository.UsuarioRepository;
import com.ipog.projetointegrador.util.PasswordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDataLoader implements CommandLineRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUsuarioData();
    }

    private void loadUsuarioData() {
        if(usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setEmail("admin@projetointegrador.com.br");
            admin.setLogin("admin");
            admin.setSenha("admin");
            admin.setStatus("A");
            admin.setTipo("A");

            usuarioRepository.save(admin);
        }
    }
}
