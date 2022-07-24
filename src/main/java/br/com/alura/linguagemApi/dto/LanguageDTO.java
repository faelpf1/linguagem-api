package br.com.alura.linguagemApi.dto;

import br.com.alura.linguagemApi.model.Language;
import lombok.Data;

@Data
public class LanguageDTO {

    private String title;
    private String url;
    private String ranking;
    //private int vote;

    /* DTO to Object */
    public Language convertToObject(){
        return new Language(title, url, ranking);
    }
    
}
