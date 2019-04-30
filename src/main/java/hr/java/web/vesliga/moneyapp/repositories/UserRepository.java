package hr.java.web.vesliga.moneyapp.repositories;

import hr.java.web.vesliga.moneyapp.model.User;

public interface UserRepository {
    Iterable<User> findAll();
    User findOne(String username);
    User save(User user);
    void delete(User user);
}
