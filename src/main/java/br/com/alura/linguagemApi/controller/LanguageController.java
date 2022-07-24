package br.com.alura.linguagemApi.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.linguagemApi.dto.LanguageDTO;
import br.com.alura.linguagemApi.model.Language;
import br.com.alura.linguagemApi.repository.LanguageRepository;

@RestController
public class LanguageController {

    @Autowired
    private LanguageRepository languageRepository;

    /* Get */
    @GetMapping("/languages")
    public List<LanguageDTO> getLanguages(){
        return languageRepository.findAll(Sort.by(Sort.Direction.ASC, "ranking")).stream().map(this::convertLanguageToDTO).collect(Collectors.toList());
    }

    @GetMapping("/languages/{title}")
    public List<LanguageDTO> getLanguagesById(@PathVariable(value = "title") String title){
        return languageRepository.findByTitle(title).stream().map(this::convertLanguageToDTO).collect(Collectors.toList());
    }

    /* Post */
    @PostMapping("/languages")
    public ResponseEntity<Language> postLanguage(@RequestBody LanguageDTO languageDTO){
        return new ResponseEntity<>(languageRepository.save(languageDTO.convertToObject()), HttpStatus.CREATED);
    }


    /* Edit */
    @PatchMapping("/languages/{id}")
    public ResponseEntity<Language> patchLanguageById(@PathVariable(value = "id") String id, @RequestBody Language newLanguage){
        Optional<Language> oldLanguage = languageRepository.findById(id);

        if(oldLanguage.isPresent()){
            Language language = oldLanguage.get();
            String newTitle = newLanguage.getTitle();
            String newImage = newLanguage.getImage();
            String newRanking = newLanguage.getRanking();

            if(newTitle != null){
                language.setTitle(newTitle);
            }
            if(newImage != null){
                language.setImage(newImage);
            }
            if(newRanking != null){
                language.setRanking(newRanking);
            }   

            languageRepository.save(language);
            return new ResponseEntity<>(language, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }

    /* Delete */
    @DeleteMapping("/languages/{id}")
    public ResponseEntity<Object> deleteLanguageById(@PathVariable(value = "id") String id){
        Optional<Language> language = languageRepository.findById(id);

        if(language.isPresent()){
            languageRepository.delete(language.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* Language to DTO */
    private LanguageDTO convertLanguageToDTO(Language language){
        LanguageDTO languageDTO = new LanguageDTO();

        languageDTO.setTitle(language.getTitle());
        languageDTO.setUrl(language.getImage());
        languageDTO.setRanking(language.getRanking());
        //languageDTO.setVote(language.getVote());

        return languageDTO;
    }

}
