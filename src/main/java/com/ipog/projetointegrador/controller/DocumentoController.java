package com.ipog.projetointegrador.controller;

import com.ipog.projetointegrador.model.Documento;
import com.ipog.projetointegrador.model.Grupo;
import com.ipog.projetointegrador.service.DocumentoService;
import com.ipog.projetointegrador.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("grupos/{grupoId}/documentos")
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private GrupoService grupoService;

    @PostMapping
    public Documento createDocumento(@RequestBody Documento documento, @PathVariable Long grupoId) { return documentoService.createDocumento(documento); }

    @GetMapping
    public List<Documento> getAllDocumentos(@PathVariable Long grupoId) { return documentoService.getAllDocumentos(); }

    @GetMapping("/{id}")
    public Optional<Documento> getDocumentoById(@PathVariable Long id, @PathVariable Long grupoId) { return documentoService.getDocumentoById(id); }

    @PutMapping("/{id}")
    public Documento updateDocumento(@PathVariable Long id, @RequestBody Documento documentoDetails, @PathVariable Long grupoId) {
        Optional<Grupo> grupo = grupoService.getGrupoById(grupoId);
        if(grupo.isPresent()) {
            Grupo existingGrupo = grupo.get();
            documentoDetails.setGrupo(existingGrupo);
        }
        return documentoService.updateDocumento(id, documentoDetails);
    }

    @DeleteMapping
    public String deleteAllDocumentos(@PathVariable Long grupoId) {
        documentoService.deleteAllDocumentos();
        return "Todos documentos deletados com sucesso";
    }

    @DeleteMapping("/{id}")
    public void deleteDocumento(@PathVariable Long id, @PathVariable Long grupoId) { documentoService.deleteDocumento(id); }
}
