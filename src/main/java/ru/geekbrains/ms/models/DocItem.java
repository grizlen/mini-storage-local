package ru.geekbrains.ms.models;

import ru.geekbrains.ms.db.entities.DocItemEntity;

public class DocItem {
    public static DocItem fromEntity(DocItemEntity entity) {
        return new DocItem();
    }
}
