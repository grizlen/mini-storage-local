package ru.geekbrains.ms.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.ms.db.entities.DocItemEntity;
import ru.geekbrains.ms.db.repositories.DocItemsRepository;
import ru.geekbrains.ms.models.DocItem;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocItemsService {

    private final DocItemsRepository docItemsRepository;
    public List<DocItem> getByDoc(Long docId) {
        List<DocItemEntity> entities = docItemsRepository.findAllByDocId(docId);
        return entities.stream()
                .map(entity -> DocItem.fromEntity(entity))
                .toList();
    }
}
