package org.leiyuxin.chapter4.ThreadLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;

public class ProductServic implements ProductService {
	private static final String UPDATE_PRODUCT_SQL = "update product set price = ? where id = ?";
	private static final String INSERT_LOG_SQL = "insert into log(created,description) values(?,?)";

	@Override
	public void updateProductPrice(long productId, int price) {
		try {
			// 获取连接
			Connection conn = DBUtil.getConnection();
			conn.setAutoCommit(false);// 关闭自动提交事务(开启事务)
			// 执行操作
			updateProduct(conn, UPDATE_PRODUCT_SQL, productId, price);

			insertLog(conn, INSERT_LOG_SQL, "Create Product.");

			// 提交事务	这里用到了JDBC 的高级特性Transaction ,
			conn.commit();
		} catch (Exception e) {
			AOPBeforAfterInfoUtil.info("增加数据失败", e);
		} finally {
			DBUtil.closeConnection();
		}

	}

	private void updateProduct(Connection conn, String updateProductSql, long productId, int price)
			throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(updateProductSql);
		pstmt.setInt(1, price);
		pstmt.setLong(2, productId);
		int rows = pstmt.executeUpdate();
		if (rows != 0) {
			AOPBeforAfterInfoUtil.info("UPdate product succee!");
		}
	}

	private void insertLog(Connection conn, String insertLogSql, String logDescription) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(insertLogSql);
		pstmt.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		pstmt.setString(2, logDescription);
		int rows = pstmt.executeUpdate();
		if(rows != 0) {
			AOPBeforAfterInfoUtil.info("Insert log success!");
		}
	}
}
