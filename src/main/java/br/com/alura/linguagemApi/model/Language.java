package br.com.alura.linguagemApi.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter @NoArgsConstructor
@Document(collection = "languages")
public class Language {

    @Id
    private String id;
    private String title;
    private String image;
    private String ranking;
    //private int vote;

    public Language(String title, String image, String ranking) {
        this.title = title;
        this.image = image;
        this.ranking = ranking;
        //this.vote = vote;
    }

    
}
