package hr.java.web.vesliga.moneyapp.repositories;

import hr.java.web.vesliga.moneyapp.model.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
    Iterable<Wallet> findAll();
    Optional<Wallet> findById(Long id);
    Wallet findByUserNameLike(String username);
    void deleteById(Long id);
    Wallet save(Wallet wallet);
    /*void delete(Long id);*/
}
