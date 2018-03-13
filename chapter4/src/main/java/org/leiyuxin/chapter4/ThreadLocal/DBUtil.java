package org.leiyuxin.chapter4.ThreadLocal;

import java.sql.Connection;
import java.sql.DriverManager;

import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;

public class DBUtil {
	// 数据库配置
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/demo";
	private static final String username = "root";
	private static final String password = "root";
	// 定义一个数据库连接,多线程环境下回会导致连接关闭
	private static Connection conn = null;
	private static ThreadLocal<Connection> connContainer = new ThreadLocal<>();
	// 获取连接

	public static Connection getConnection() {
		Connection conn = connContainer.get();

		try {
			if (conn == null) {

				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);
			}
		} catch (Exception e) {
			AOPBeforAfterInfoUtil.info("context", e);
		}finally {
			connContainer.set(conn);
		}
		return conn;
	}

	public static void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			AOPBeforAfterInfoUtil.info("关闭连接异常", e);
		}finally {
			connContainer.remove();
		}
	}
}
