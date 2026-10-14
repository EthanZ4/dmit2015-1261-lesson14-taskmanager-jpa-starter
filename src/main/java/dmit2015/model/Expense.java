package dmit2015.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.datafaker.Faker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="expense_id")
    private int id;

    @Column(name="expense_date")
    private LocalDate date;

    private String description;
    private BigDecimal amount;

    @Version
    private Integer version;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @PrePersist
    void PrePersist() {
        LocalDateTime now = LocalDateTime.now();
        createTime = now;
        updateTime = now;
    }

    @PreUpdate
    void PreUpdate() {
        updateTime = LocalDateTime.now();
    }

    public static Expense of(Faker faker) {
        Expense newExpense = new Expense();
        newExpense.setDescription(faker.breakingBad().episode());
        newExpense.setAmount(new BigDecimal(faker.number().numberBetween(1,99)));
        newExpense.setDate(LocalDate.now());
        return newExpense;
    }
}
