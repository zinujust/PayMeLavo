package com.paymelavo.payrun;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRunRepository extends JpaRepository<PayRun, Long> {
}
