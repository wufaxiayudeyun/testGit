package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.*;

public interface UsersDao {
	// DML 接口固定写法 int 影响条数
	public int insertUser(Users user);

	public int deleteUser(int id);

	public int updateUser(Users user);

	// DQL 接口不固定
	public Users getUserById(int id);

	public List<Users> getAllUserList();
}
