package hr.java.web.vesliga.moneyapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Name;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name="Expenses")

public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name="create_date")
    private LocalDateTime createDate;

    @NotEmpty(message="{msg.validation.expenseName}")
    @Size(min=5, max=30, message="{msg.validation.expenseName.length}")
    @Column(name="expense_name")
    private String expenseName;


    @NotNull(message="{msg.validation.expenseAmount}")
    @DecimalMin(value="1",message="Možete unjeti minimalni iznos od 1 kn!")
    private Double amount;

    @Column(name="expense_type")
    @Enumerated(EnumType.STRING)
    @NotNull(message="{msg.validation.expenseAmount}")
    private ExpenseType type;

    @ManyToOne(targetEntity = Wallet.class)
    @JoinColumn(name="wallet_id")
    @JsonBackReference
    private Wallet wallet;

    public enum ExpenseType{
        Hrana,
        Piće,
        Režije,
        Stanarina,
        Dugovanje,
        Donacija
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(id, expense.id) &&
                Objects.equals(createDate, expense.createDate) &&
                Objects.equals(expenseName, expense.expenseName) &&
                Objects.equals(amount, expense.amount) &&
                type == expense.type &&
                Objects.equals(wallet, expense.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, expenseName, amount, type, wallet);
    }
    */
}
