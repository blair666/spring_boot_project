package com.blair.projectmgmtapp.repository;


import com.blair.projectmgmtapp.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
//    List<Quote> findByItemNameContainingIgnoreCase(String itemName);
//    List<Quote> findByProjectId(Long projectId);
}
