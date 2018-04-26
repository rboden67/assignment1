package com.jrb.assignment1;

import java.util.List;

public interface MemberDao {
	public void insert(Member member);
    public void update(Member member);
    public void delete(String memid);
    public Member find(String memid);
    public Member findByQuery(String memid);
    public List<Member> findByStatus(String status);
}
