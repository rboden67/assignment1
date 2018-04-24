package com.jrb.assignment1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDaoJdbcImpl implements MemberDao {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private MappingSqlQuery<Member> memberByIdQuery;

	public void setMemberByIdQuery(MappingSqlQuery<Member> memberByIdQuery) {
		this.memberByIdQuery = memberByIdQuery;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public void insert(Member member) {
		PreparedStatementCreatorFactory psCreatorFactory = new PreparedStatementCreatorFactory(
				"insert into tblMembers (MemId, LastName, FirstName, MiddleName, Status, Password) values(?,?,?,?,?,?)",
				new int[] { Types.CHAR, Types.CHAR, Types.CHAR, Types.CHAR, Types.CHAR, Types.CHAR });
		int count = jdbcTemplate.update(
				psCreatorFactory.newPreparedStatementCreator(new Object[] { member.getMemid(), member.getLastname(),
						member.getFirstname(), member.getMiddlename(), member.getStatus(), member.getPassword() }));
		if (count != 1)
			throw new InsertFailedException("Cannot insert member");
	}

	public void update(Member member) {
		int count = jdbcTemplate.update("LastName, FirstName,MiddleName,Status,Password) = (?,?,?,?,?) where id = ?",
				member.getLastname(), member.getFirstname(), member.getMiddlename(), member.getStatus(),
				member.getPassword());
		if (count != 1)
			throw new UpdateFailedException("Cannot update account");

	}

	public void delete(String memid) {
		int count = jdbcTemplate.update("delete member where id = ?", memid);
		if (count != 1)
			throw new DeleteFailedException("Cannot delete account");
	}

	public Member find(String memid) {
		return jdbcTemplate.queryForObject("select MemID, LastName, FirstName, Status, MemDt from tblMembers where MemID = ?",
				new RowMapper<Member>() {
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member();
						member.setMemid(rs.getString("MemID"));
						member.setLastname(rs.getString("LastName"));
						member.setFirstname(rs.getString("FirstName"));
						member.setStatus(rs.getString("Status"));
						member.setMemdt(rs.getDate("Memdt"));
						return member;
					}
				}, memid);
	}

	public Member findByQuery(String memid) {
		return memberByIdQuery.findObject(memid);
	}

	public List<Member> findByStatus(String status) {
		PreparedStatementCreatorFactory psCreatorFactory = new PreparedStatementCreatorFactory(
				"select * from tblMembers where status = ?", new int[] { Types.CHAR });
		return jdbcTemplate.query(psCreatorFactory.newPreparedStatementCreator(new Object[] { status }),
				new RowMapper<Member>() {
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member();
						member.setMemid(rs.getString("MemID"));
						member.setLastname(rs.getString("LastName"));
						member.setFirstname(rs.getString("FirstName"));
						member.setStatus(rs.getString("Status"));
						member.setMemdt(rs.getDate("Memdt"));
						return member;
					}

				});

	}

}
