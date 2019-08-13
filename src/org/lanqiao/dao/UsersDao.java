package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.*;

public interface UsersDao {
	// DML �ӿڹ̶�д�� int Ӱ������
	public int insertUser(Users user);

	public int deleteUser(int id);

	public int updateUser(Users user);

	// DQL �ӿڲ��̶�
	public Users getUserById(int id);

	public List<Users> getAllUserList();
}
