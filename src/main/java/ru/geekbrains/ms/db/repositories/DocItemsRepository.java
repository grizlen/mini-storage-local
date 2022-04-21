package ru.geekbrains.ms.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.ms.db.entities.DocItemEntity;

import java.util.List;

@Repository
public interface DocItemsRepository extends JpaRepository<DocItemEntity, Long> {
    List<DocItemEntity> findAllByDocId(Long docId);
}
