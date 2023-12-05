package com.ipog.projetointegrador.service;

import com.ipog.projetointegrador.model.Documento;
import com.ipog.projetointegrador.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {
    @Autowired
    private DocumentoRepository documentoRepository;

    public Documento createDocumento(Documento documento) { return documentoRepository.save(documento); }

    public List<Documento> getAllDocumentos() { return documentoRepository.findAll(); }

    public Optional<Documento> getDocumentoById(Long id) { return documentoRepository.findById(id); }

    public Documento updateDocumento(Long id, Documento documentoDetails) {
        Optional<Documento> documento = documentoRepository.findById(id);
        if(documento.isPresent()) {
            Documento existingDocumento = documento.get();
            existingDocumento.setNome(documentoDetails.getNome());
            existingDocumento.setNomeArmazenamento(documentoDetails.getNomeArmazenamento());
            existingDocumento.setGrupo(documentoDetails.getGrupo());
            return documentoRepository.save(existingDocumento);
        }
        return null;
    }

    public void deleteAllDocumentos() { documentoRepository.deleteAll(); }

    public void deleteDocumento(Long id) { documentoRepository.deleteById(id); }
}
