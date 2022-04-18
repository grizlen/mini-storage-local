package ru.geekbrains.ms.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Document {
    private Long id;
    private Date date;
    private String type;

    public Document() {
        date = new Date();
    }
}
