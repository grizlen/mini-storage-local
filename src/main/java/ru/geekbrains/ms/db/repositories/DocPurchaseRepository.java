package ru.geekbrains.ms.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.ms.db.entities.DocPurchaseEntity;

@Repository
public interface DocPurchaseRepository extends JpaRepository<DocPurchaseEntity, Long> {
}
