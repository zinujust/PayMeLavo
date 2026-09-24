package com.paymelavo.employee;

import com.paymelavo.payrunline.PayRunLine;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "employees"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "first_Name", nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "last_Name", nullable = false)
    private String lastName;

    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @NotBlank
    @Column(name = "tin", unique = true, nullable = false, length = 9)
    private String tin;

    @NotNull
    @Column(name = "employment_status",  nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @Column(name = "pay_rate", precision = 12, scale = 2, nullable = false)
    private BigDecimal payRate;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
