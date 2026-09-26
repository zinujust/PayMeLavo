package com.paymelavo.payrunline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRunLineRepository extends JpaRepository<PayRunLine, Long> {
}
