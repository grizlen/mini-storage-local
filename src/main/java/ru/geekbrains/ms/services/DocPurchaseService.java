package ru.geekbrains.ms.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.ms.db.entities.DocumentEntity;
import ru.geekbrains.ms.db.repositories.DocPurchaseRepository;
import ru.geekbrains.ms.models.DocPurchase;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocPurchaseService {

    private final DocPurchaseRepository docPurchaseRepository;
    private final DocumentsService documentsService;
    private final DocItemsService docItemsService;
    public List<DocPurchase> getAll() {
        List<DocumentEntity> documentEntities = documentsService.getAllPurchase();
        List<DocPurchase> result = documentEntities.stream()
                .map(entity ->
                        DocPurchase.builder()
                                .id(entity.getId())
                                .date(entity.getDate())
                                .items(docItemsService.getByDoc(entity.getId()))
                                .build())
                .toList();
        return result;
    }

    public void save(DocPurchase doc) {
        log.info("save {}", doc);
    }
}
