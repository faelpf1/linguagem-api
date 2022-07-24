package br.com.alura.linguagemApi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.alura.linguagemApi.model.Language;

public interface LanguageRepository extends MongoRepository<Language, String>{
    Optional<Language> findByTitle(String title);
}
