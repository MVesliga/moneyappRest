package hr.java.web.vesliga.moneyapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;


@Data
@NoArgsConstructor
@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message="Niste unjeli username!")
    @Size(min=5, max=15, message="Username mora imati između 5 i 15 znakova")
    private String username;
    @NotEmpty(message="Niste unjeli password!")
    //@Size(min=5, max=50, message="Password mora imati između 5 i 15 znakova")
    private String password;
    @Column(name="enabled")
    private boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, enabled);
    }
}
