package hr.java.web.vesliga.moneyapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.NaturalId;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name="Wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="create_date")
    @JsonIgnore
    private LocalDateTime createDate;

    @Column(name="wallet_name")
    private String walletName;

    @Enumerated(EnumType.STRING)
    @Column(name="wallet_type")
    private walletType walletType;

    @Column(name="user_name")
    private String userName;


    @OneToMany(mappedBy = "wallet", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Expense> listOfExpenses = new ArrayList<>();

    public enum walletType{
        Gotovina
    }

}
