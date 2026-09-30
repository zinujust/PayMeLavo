package com.paymelavo.payrun;

import com.paymelavo.payrunline.PayRunLine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "pay_runs"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayRun {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "payRun", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PayRunLine> lines = new ArrayList<>();

    public void addLine(PayRunLine line) {
        lines.add(line);
        line.setPayRun(this);
    }

    @NotNull
    @Column(name = "start_date",  nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date",   nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",  nullable = false)
    private PayRunStatus status;

    @Column(name = "finalized_at",  nullable = true, updatable = false)
    private LocalDateTime finalizedAt;
}
