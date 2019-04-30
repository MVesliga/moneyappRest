package hr.java.web.vesliga.moneyapp.repositories;

import hr.java.web.vesliga.moneyapp.model.Wallet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcWalletRepository implements WalletRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert walletInserter;

    public JdbcWalletRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.walletInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("Wallets").usingGeneratedKeyColumns("id");
    }

    @Override
    public Iterable<Wallet> findAll() {

        return jdbcTemplate.query("select * from Wallets", this::mapRowToWallet);
    }

    @Override
    public Wallet findOne(Long id) {

        return jdbcTemplate.queryForObject("select * from Wallets where id = ?", this::mapRowToWallet, id);
    }

    @Override
    public Wallet findOneByUsername(String username) {
        return jdbcTemplate.queryForObject("select * from Wallets where userName = ?", this::mapRowToWallet, username);
    }

    @Override
    public Wallet save(Wallet wallet) {

        wallet.setCreateDate(LocalDateTime.now());
        wallet.setId(saveWalletDetails(wallet));

        return wallet;
    }

    private Wallet  mapRowToWallet(ResultSet resultSet, int rowNum)throws SQLException {
        Wallet wallet = new Wallet();
        wallet.setId(resultSet.getLong("id"));
        wallet.setWalletName(resultSet.getString("walletName"));
        wallet.setCreateDate(resultSet.getTimestamp("createDate").toLocalDateTime());
        wallet.setUserName(resultSet.getString("userName"));
        wallet.setWalletType(Wallet.walletType.valueOf(resultSet.getString("walletType")));

        return wallet;
    }

    private long saveWalletDetails(Wallet wallet){
        Map<String,Object> values = new HashMap<>();
        values.put("walletName", wallet.getWalletName());
        values.put("createDate", wallet.getCreateDate());
        values.put("walletType", wallet.getWalletType());
        values.put("userName", wallet.getUserName());

        return walletInserter.executeAndReturnKey(values).longValue();
    }

    @Override
    public void delete(Long id) {

    }
}
