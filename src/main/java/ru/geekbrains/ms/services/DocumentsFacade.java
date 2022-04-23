package ru.geekbrains.ms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.ms.models.DocPurchase;
import ru.geekbrains.ms.models.Document;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentsFacade {

    @Autowired
    DocPurchaseService docPurchaseService;
    public List<Document> getAll() {
        ArrayList<Document> result = new ArrayList<>();
        result.addAll(docPurchaseService.getAll());
        return result;
    }

    public void savePurchase(DocPurchase doc) {
        docPurchaseService.save(doc);
    }
}
