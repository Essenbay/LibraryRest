package com.example.libraryweb.services;

import com.example.libraryweb.data.modules.Ebook;
import com.example.libraryweb.data.repositories.EbookRepository;
import com.example.libraryweb.data.repositories.TextbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EbookService {

    private final EbookRepository ebookRepository;
    @Autowired
    public EbookService(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }

    public List<Ebook> getAllEbooks() {
        return ebookRepository.findAll();
    }

    public Optional<Ebook> getEbookById(Long id) {
        return ebookRepository.findById(id);
    }

    public Ebook saveEbook(Ebook ebook) {
        return ebookRepository.save(ebook);
    }

    public void deleteEbookById(Long id) {
        ebookRepository.deleteById(id);
    }
}
