package hr.java.web.vesliga.moneyapp.repositories;

import hr.java.web.vesliga.moneyapp.model.Wallet;

public interface WalletRepository {
    Iterable<Wallet> findAll();
    Wallet findOne(Long id);
    Wallet findOneByUsername(String username);
    Wallet save(Wallet wallet);
    void delete(Long id);
}
