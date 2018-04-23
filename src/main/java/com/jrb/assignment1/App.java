package com.jrb.assignment1;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws SQLException {
		 AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ch4Configuration.class);
	        
	        MemberDao memberDao = applicationContext.getBean(MemberDao.class);
	        
	        //find by member ID
	        Member member = memberDao.find("A043");	        
	        System.out.println(member.getMemid());
	        System.out.println(member.getFirstname());
	        System.out.println(member.getLastname());
	
	        //findByStatus
	        List<Member> members = memberDao.findByStatus("T");
	        System.out.println(members);
	}
}
