package com.ipog.projetointegrador.service;

import com.ipog.projetointegrador.model.Grupo;
import com.ipog.projetointegrador.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {
    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo createGrupo(Grupo grupo) { return grupoRepository.save(grupo); }

    public List<Grupo> getAllGrupos() { return grupoRepository.findAll(); }

    public Optional<Grupo> getGrupoById(Long id) { return grupoRepository.findById(id); }

    public Grupo updateGrupo(Long id, Grupo grupoDetails) {
        Optional<Grupo> grupo = grupoRepository.findById(id);
        if(grupo.isPresent()) {
            Grupo existingGrupo = grupo.get();
            existingGrupo.setDescricao(grupoDetails.getDescricao());
            return grupoRepository.save(existingGrupo);
        }
        return null;
    }

    public void deleteAllGrupos() { grupoRepository.deleteAll(); }

    public void deleteGrupo(Long id) { grupoRepository.deleteById(id); }
}
