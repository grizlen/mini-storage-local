package ru.geekbrains.ms.models;

import ru.geekbrains.ms.db.entities.DocPurchaseEntity;

import java.time.chrono.Chronology;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocPurchase extends Document {
    private final List<DocItem> items = new ArrayList<>();

    public DocPurchase() {
        setType(Document.PURCHASE);
    }

    public List<DocItem> getItems() {
        return items;
    }

    public void setItems(List<DocItem> list) {
        items.clear();
        list.forEach(this::addItem);
    }

    public void addItem(DocItem item) {
        items.add(item);
    }

    public static PurchaseBuilder builder() {
        return new PurchaseBuilder();
    }

    public static class PurchaseBuilder {
        private final DocPurchase doc;

        private PurchaseBuilder() {
            doc = new DocPurchase();
        }

        public DocPurchase build() {
            return doc;
        }

        public PurchaseBuilder id(Long id) {
            doc.setId(id);
            return this;
        }

        public PurchaseBuilder date(Date date) {
            doc.setDate(date);
            return this;
        }

        public PurchaseBuilder items(List<DocItem> items) {
            doc.setItems(items);
            return this;
        }
    }
}
