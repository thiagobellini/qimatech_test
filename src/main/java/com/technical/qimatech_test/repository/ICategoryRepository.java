package com.technical.qimatech_test.repository;

import com.technical.qimatech_test.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, UUID> {
}
