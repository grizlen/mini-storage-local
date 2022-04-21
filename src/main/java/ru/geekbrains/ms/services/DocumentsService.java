package ru.geekbrains.ms.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.ms.db.entities.DocumentEntity;
import ru.geekbrains.ms.db.repositories.DocumentsRepository;
import ru.geekbrains.ms.models.Document;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentsService {

    private final DocumentsRepository documentsRepository;
    public List<DocumentEntity> getAllPurchase() {
        Specification<DocumentEntity> spec = Specification.where(
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get("type"), Document.PURCHASE));
        return documentsRepository.findAll(spec);
    }
}
