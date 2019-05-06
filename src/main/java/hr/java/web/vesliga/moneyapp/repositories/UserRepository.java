package hr.java.web.vesliga.moneyapp.repositories;

import hr.java.web.vesliga.moneyapp.model.User;

public interface UserRepository {
    Iterable<User> findAll();
    User findOne(Long id);
    User save(User user);
    void delete(Long id);
    void update(Long id, User user);
}
