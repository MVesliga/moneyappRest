package hr.java.web.vesliga.moneyapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long id;


    private LocalDateTime createDate;

    @NotEmpty(message="Niste unjeli naziv troška!")
    @Size(min=5, max=30, message="Naziv troška mora imati između 5 i 30 znakova")
    private String expenseName;


    @NotNull(message="Niste unjeli iznos troška!")
    @DecimalMin(value="1",message="Možete unjeti minimalni iznos od 1 kn!")
    private Double amount;

    @Column(name="expenseType")
    @Enumerated(EnumType.STRING)
    @NotNull(message="Niste odabrali vrstu troška!")
    private expenseType type;

    @ManyToOne(targetEntity = Wallet.class)
    @JoinColumn(name="walletId")
    @JsonBackReference
    private Wallet wallet;

    public enum expenseType{
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
