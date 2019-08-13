package org.lanqiao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDao<T> {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private String name = "scott";
	private String pass = "tiger";

	protected Connection getConnection() {
		Connection conn = null;
		try {
			// 1.��������
			Class.forName(driver);
			// 2����������
			conn = DriverManager.getConnection(url, name, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	protected void closeAll(ResultSet rs, Statement stat, Connection conn) {
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

	protected void closeAll(Statement stat, Connection conn) {
		try {
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected int executeUpdate(String sql,Object[] objects) {
		PreparedStatement stat = null;
		Connection conn = null;
		int ret = 0;
		try {
			conn = getConnection();
			// 3.ʹ��statment����perpareredStatment ����sql�����ݿ�
			stat = conn.prepareStatement(sql);
			// 4.����excute���ؽ��DML->executeUpdate(); DQL executeQuery()
			for(int i=0;i<objects.length;i++) {
				stat.setObject(i+1, objects[i]);
			}
			
			ret = stat.executeUpdate();
			// 5�����ؽ��
			// 6.�ر���Դ
			// TODO Auto-generated method stub
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(stat, conn);
		}
		// TODO Auto-generated method stub
		return 0;
	}

}

