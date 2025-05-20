package com.ascendion.libraryManagementService.repository;

import com.ascendion.libraryManagementService.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

}
