package com.jrb.assignment1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 *
 * @author rob
 */
public class AccountDaoJdbcImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private MappingSqlQuery<Account> accountByIdQuery;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public void insert(Account account) {
        // TODO Auto-generated method stub

    }

    public void update(Account account) {
        // TODO Auto-generated method stub

    }

    public void update(List<Account> accounts) {
        // TODO Auto-generated method stub

    }

    public void delete(long accountId) {
        // TODO Auto-generated method stub

    }

    public Account find(long accountId) {
    	return accountByIdQuery.findObject(accountId);
    }
    
    public List<Account> find(List<Long> accountIds) {
        return null;
    }

    public List<Account> find(String ownerName) {
        return namedParameterJdbcTemplate.query(
                "select id,owner_name,balance,access_time,locked from account where owner_name = :ownerName", Collections.singletonMap("ownerName", ownerName),
                new RowMapper<Account>() {
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account();
                account.setId(rs.getLong("id"));
                account.setOwnerName(rs.getString("owner_name"));
                account.setBalance(rs.getDouble("balance"));
                account.setAccessTime(rs.getTimestamp("access_time"));
                account.setLocked(rs.getBoolean("locked"));
                return account;
            }
        });

    }
    
	public void setAccountByIdQuery(MappingSqlQuery<Account> accountByIdQuery) {
	    this.accountByIdQuery = accountByIdQuery;
	}
	
	
    public List<Account> find(boolean locked) {
        // TODO Auto-generated method stub
        return null;
    }

    //method implementations...
}