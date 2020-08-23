package com.github.parkhuiwo0.learningreactvie.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reactivedb")
public class Chapter {

    @Id
    private String id;

    private String name;

    public Chapter(String name) {
        this.name = name;
    }
}
