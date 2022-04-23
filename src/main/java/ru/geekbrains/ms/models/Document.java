package ru.geekbrains.ms.models;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class Document {
    public static final String PURCHASE = "PURCHASE";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    public static String formatDate(Date date) {
        return formatter.format(date);
    }
    private Long id;
    private Date date;
    private String type;

    public Document() {
        date = new Date();
    }

    private String getDateStr() {
        return formatDate(date);
    }

    @Override
    public String toString() {
        return String.format("%s %d от %s", type, id, getDateStr());
    }
}
