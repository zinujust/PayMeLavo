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

	@Bean
	public CommandLineRunner testEntityPersistence(
			EntityManager entityManager,
			TransactionTemplate transactionTemplate) {

		return args -> transactionTemplate.execute(status -> {
			System.out.println("\n--------------------------------------------------");
			System.out.println("🚀 Testing Entity Persistence via CommandLineRunner");
			System.out.println("--------------------------------------------------\n");

			// 1. Create and persist Employee
			Employee employee = Employee.builder()
					.firstName("Sami")
					.lastName("Khan")
					.email("sami.khan@example.com")
					.phoneNumber("+6799991234")
					.address("Lot 3, Vula Rd, Makoi, Nasinu, Fiji")
					.tin("123456789")
					.employmentStatus(EmploymentStatus.ACTIVE)
					.startDate(LocalDate.of(2026, 1, 15))
					.payRate(BigDecimal.valueOf(25.00))
					.createdAt(LocalDateTime.of(2026, 1, 15, 12, 00))
					.updatedAt(LocalDateTime.of(2026, 1, 15, 12, 00))
					.build();

			entityManager.persist(employee);
			System.out.println("✅ Saved Employee ID: " + employee.getId());

			// 2. Create and persist PayRun
			PayRun payRun = PayRun.builder()
					.startDate(LocalDate.of(2026, 9, 1))
					.endDate(LocalDate.of(2026, 9, 15))
					.status(PayRunStatus.OPEN)
					.build();

			entityManager.persist(payRun);
			System.out.println("✅ Saved PayRun ID: " + payRun.getId());

			// 3. Create and persist PayRunLine with Snapshot Data
			PayRunLine line = PayRunLine.builder()
					.normalHours(BigDecimal.valueOf(40.00))
					.overtimeHours(BigDecimal.valueOf(20.00))
					.payRun(payRun)
					.employee(employee)
					.payRate(employee.getPayRate())
					.grossPay(BigDecimal.valueOf(2000.00))
					.taxablePay(BigDecimal.valueOf(20.00))
					.fnpfEmployee(BigDecimal.valueOf(20.00))
					.fnpfEmployer(BigDecimal.valueOf(20.00))
					.paye(BigDecimal.valueOf(300.00))
					.netPay(BigDecimal.valueOf(1700.00))
					.totalEmployerCost(BigDecimal.valueOf(2020.00))
					.build();

			entityManager.persist(line);
			System.out.println("✅ Saved PayRunLine ID: " + line.getId());

			// 4. Force Hibernate to issue INSERT SQL queries to PostgreSQL
			entityManager.flush();
			entityManager.clear(); // Clear cache to force a fresh DB SELECT read

			// 5. Query back and verify persistence
			PayRunLine fetchedLine = entityManager.find(PayRunLine.class, line.getId());

			System.out.println("\n--- PERSISTENCE VERIFICATION ---");
			System.out.println("Line ID: " + fetchedLine.getId());
			System.out.println("Employee Name: " + fetchedLine.getEmployee().getFirstName() + " " + fetchedLine.getEmployee().getLastName());
			System.out.println("Applied Rate (Snapshot): $" + fetchedLine.getPayRate());
			System.out.println("Net Pay: $" + fetchedLine.getNetPay());
			System.out.println("Created At (Auditing): " + fetchedLine.getEmployee().getCreatedAt());
			System.out.println("--------------------------------------------------\n");

			return null;
		});
	}
}
