package com.example.libraryweb.services;

import com.example.libraryweb.data.modules.Textbook;
import com.example.libraryweb.data.repositories.TextbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TextbookService {

    private final TextbookRepository textbookRepository;

    @Autowired
    public TextbookService(TextbookRepository textbookRepository) {
        this.textbookRepository = textbookRepository;
    }


    public List<Textbook> getAllTextbooks() {
        return textbookRepository.findAll();
    }

    public Optional<Textbook> getTextbookById(Long id) {
        return textbookRepository.findById(id);
    }

    public Textbook saveTextbook(Textbook textbook) {
        return textbookRepository.save(textbook);
    }

    public void deleteTextbookById(Long id) {
        textbookRepository.deleteById(id);
    }
}
