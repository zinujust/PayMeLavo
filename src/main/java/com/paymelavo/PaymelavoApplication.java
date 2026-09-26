package com.paymelavo;

import com.paymelavo.employee.Employee;
import com.paymelavo.employee.EmploymentStatus;
import com.paymelavo.payrun.PayRun;
import com.paymelavo.payrun.PayRunStatus;
import com.paymelavo.payrunline.PayRunLine;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class PaymelavoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymelavoApplication.class, args);
	}

}
