package com.jrb.assignment1;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.MappingSqlQuery;

@Configuration
public class Ch4Configuration {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/CLUB294?serverTimezone=UTC");
        dataSource.setUsername("webapp_user");
        dataSource.setPassword("testing123");
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
    
    @Bean
    public MemberDao memberDao() {
        MemberDaoJdbcImpl memberDao = new MemberDaoJdbcImpl();
        memberDao.setJdbcTemplate(jdbcTemplate());
        memberDao.setMemberByIdQuery(memberByIdQuery());
        return memberDao;
    }
  
    
    @Bean
    public MappingSqlQuery<Member> memberByIdQuery() {
        MemberByIdQuery query = new MemberByIdQuery(dataSource());
        return query;
    }


}
