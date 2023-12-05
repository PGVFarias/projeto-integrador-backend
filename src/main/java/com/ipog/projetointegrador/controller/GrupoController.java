package com.ipog.projetointegrador.controller;

import com.ipog.projetointegrador.model.Grupo;
import com.ipog.projetointegrador.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private GrupoService grupoService;

    @PostMapping
    public Grupo createGrupo(@RequestBody Grupo grupo) { return grupoService.createGrupo(grupo); }

    @GetMapping
    public List<Grupo> getAllGrupos() { return grupoService.getAllGrupos(); }

    @GetMapping("/{id}")
    public Optional<Grupo> getGrupoById(@PathVariable Long id) { return grupoService.getGrupoById(id); }

    @PutMapping("/{id}")
    public Grupo updateGrupo(@PathVariable Long id, @RequestBody Grupo grupoDetails) {
        return grupoService.updateGrupo(id, grupoDetails);
    }

    @DeleteMapping
    public String deleteAllGrupos() {
        grupoService.deleteAllGrupos();
        return "Todos grupos deletados com sucesso";
    }

    @DeleteMapping("/{id}")
    public void deleteGrupo(@PathVariable Long id) { grupoService.deleteGrupo(id); }

}
