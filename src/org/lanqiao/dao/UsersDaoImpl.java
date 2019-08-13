package org.lanqiao.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.entity.Users;

public class UsersDaoImpl extends BaseDao implements UsersDao {
	public String driver = "oracle.jdbc.driver.OracleDriver";
	public String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	public String name = "scott";
	public String pass = "tiger";

	public Connection getConnection() {
		Connection conn = null;
		try {
			// 1.加载驱动
			Class.forName(driver);
			// 2，创建链接
			conn = DriverManager.getConnection(url, name, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeAll(ResultSet rs, Statement stat, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeAll(Statement stat, Connection conn) {
		try {
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int executeUpdate(String sql) {
		Statement stat = null;
		Connection conn = null;
		int ret = 0;
		try {
			conn = getConnection();
			// 3.使用statment或者perpareredStatment 发送sql到数据库
			stat = conn.createStatement();
			// 4.调用excute返回结果DML->executeUpdate(); DQL executeQuery()
			ret = stat.executeUpdate(sql);
			// 5处理返回结果
			// 6.关闭资源
			// TODO Auto-generated method stub
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(stat, conn);
		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(int id) {
		return executeUpdate("delete from users where id=?" ,new Object[] {id});
	}

	@Override
	public int insertUser(Users user) {
		return executeUpdate(
				"inser into users (id,name,pass) value(seq_users.nextval,?,?)",
				new Object[] {user.getName(),user.getPass()});
	}

	@Override
	public int updateUser(Users user) {
		return executeUpdate("update users set pass = ? where id= ?",new Object[] {user.getPass(),user.getId()});
	}

	@Override
	public Users getUserById(int id) {
		Users user = new Users();
		try {
			Connection conn = getConnection();
			// 3.使用statment或者perpareredStatment 发送sql到数据库
			Statement stat = conn.createStatement();
			// 4.调用excute返回结果DML->executeUpdate(); DQL executeQuery()
			ResultSet rs = stat.executeQuery("select * from users order by id");
			// 5处理返回结果
			while (rs.next()) {
				user.setId(rs.getInt(id));
				user.setName(rs.getString("name"));// name列名
				user.setPass(rs.getString("pass"));

			}
			// 6.关闭资源
			stat.close();
			conn.close();
			// TODO Auto-generated method stub
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<Users> getAllUserList() {
		List<Users> list = new ArrayList<Users>();
		try {
			Connection conn = getConnection();
			// 3.使用statment或者perpareredStatment 发送sql到数据库
			Statement stat = conn.createStatement();
			// 4.调用excute返回结果DML->executeUpdate(); DQL executeQuery()
			ResultSet rs = stat.executeQuery("select * from users order by id");
			// 5处理返回结果
			while (rs.next()) {
				Users user = new Users();
				user.setId(rs.getInt(1));
				user.setName(rs.getString("name"));// name列名
				user.setPass(rs.getString("pass"));
				list.add(user);
			}
			// 6.关闭资源
			stat.close();
			conn.close();
			// TODO Auto-generated method stub
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		UsersDao userDao = new UsersDaoImpl();
	}

}
