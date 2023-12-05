package com.ipog.projetointegrador.controller;

import com.ipog.projetointegrador.model.Documento;
import com.ipog.projetointegrador.model.Grupo;
import com.ipog.projetointegrador.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("grupos/{grupoId}/documentos")
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    @PostMapping
    public Documento createDocumento(@RequestBody Documento documento) { return documentoService.createDocumento(documento); }

    @GetMapping
    public List<Documento> getAllDocumentos() { return documentoService.getAllDocumentos(); }

    @GetMapping("/{id}")
    public Optional<Documento> getDocumentoById(@PathVariable Long id) { return documentoService.getDocumentoById(id); }

    @PutMapping("/{id}")
    public Documento updateDocumento(@PathVariable Long id, @RequestBody Documento documentoDetails) {
        return documentoService.updateDocumento(id, documentoDetails);
    }

    @DeleteMapping
    public String deleteAllDocumentos() {
        documentoService.deleteAllDocumentos();
        return "Todos documentos deletados com sucesso";
    }

    @DeleteMapping("/{id}")
    public void deleteDocumento(@PathVariable Long id) { documentoService.deleteDocumento(id); }
}
