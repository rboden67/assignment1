package com.jrb.assignment1;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws SQLException {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				Assgn1Configuration.class);

		MemberDao memberDao = applicationContext.getBean(MemberDao.class);

		// find by member Id with inline anonymous class declaration
		Member member = memberDao.find("A043");
		System.out.println("\nOutput for find with inline anonymous class");
		System.out.println(member.toString());

		// find by member Id with encapsulation SQL query
		member = memberDao.findByQuery("G116");
		System.out.println("\nOutput for find with encapsulation SQL query");
		System.out.println(member.toString());

		// findByStatus
		List<Member> members = memberDao.findByStatus("T");
		Iterator<Member> iterator = members.iterator();
		System.out.println("\nOutput for find by status");
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}

		// insert new member
		Member newMember = new Member();
		newMember.setMemid("A123");
		newMember.setLastname("Smith");
		newMember.setFirstname("John");
		newMember.setStatus("C");
		newMember.setMemdt(new Date());
		memberDao.insert(newMember);
		member = memberDao.find(newMember.getMemid());
		System.out.println("\nNew member inserted");
		System.out.println(member.toString());

		// account.setBalance(30.0);

		applicationContext.close();
	}
}