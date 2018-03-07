package org.leiyuxin.web.chapter2.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.leiyuxin.web.chapter2.util.CollectionUtil;
import org.leiyuxin.web.chapter2.util.PropsUtil;

/**
 * 数据库助手类； 执行一条select 语句需要编写一大堆代码，而且必须是try...catch...finally结构 开发效率不高 Apache
 * Commons DBUtils 就是来解决这个问题,使用了使用了Commons Dbutils 的QueryRunner
 * 我们不在面对PreparedStatement与ResultSet了
 */
public final class DatabaseHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	// 为了确保一个线程中只有一个Connection,我们可以使用ThreadLocal 来存放本地线程变量，也就是说将当前线程在中的Connection
	// 放入ThreadLocal中存起来，这些Connection一定不会出现不安全问题
	// 可以将ThreadLocal 理解为了一个隔离线程的容器。
	private static final ThreadLocal<Connection> CONNECTION_HOLDER;
	// 使用Dbutils 提供的QueryRunner对象可以面向实体进行查询
	// 实际上，Dbutils 首先执行SQL语句并返回一个ResultSet,随后通过反射去创建并初始化实体对象
	private static final QueryRunner QUERY_RUNNER;
	// 使用数据库连接池
	private static final BasicDataSource DATA_SOURCE;

	static {
		CONNECTION_HOLDER = new ThreadLocal<Connection>();

		QUERY_RUNNER = new QueryRunner();

		Properties conf = PropsUtil.loadProps("config.properties");
		String driver = conf.getProperty("jdbc.driver");
		String url = conf.getProperty("jdbc.url");
		String username = conf.getProperty("jdbc.username");
		String password = conf.getProperty("jdbc.password");

		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(driver);
		DATA_SOURCE.setUrl(url);
		DATA_SOURCE.setUsername(username);
		DATA_SOURCE.setPassword(password);
	}

	/**
	 * 获取数据库连接
	 */
	public static Connection getConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		if (conn == null) {
			try {
				conn = DATA_SOURCE.getConnection();
			} catch (SQLException e) {
				LOGGER.error("get connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
		return conn;
	}

	/**
	 * 执行查询语句，查询不一定是基于单表的，也有可能是连接多个表进行查询，因此提供一个更强大的查询方法，输入一个SQL与动态参数，输出一个List对象，
	 * 其中的Map 表示列名与列值得映射关系。注意这里没有具体的实体bean 因为是多表查询
	 */
	public List<Map<String, Object>> executeQuery(String sql, Object... params) {
		List<Map<String, Object>> result;
		try {
			Connection conn = getConnection();
			result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
		} catch (Exception e) {
			LOGGER.error("execute query failure", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 执行更新语句（包括：update、insert、delete）
	 */
	public static int executeUpdate(String sql, Object... params) {
		int rows = 0;
		try {
			Connection conn = getConnection();
			rows = QUERY_RUNNER.update(conn, sql, params);
		} catch (SQLException e) {
			LOGGER.error("execute update failure", e);
			throw new RuntimeException(e);
		}
		return rows;
	}

	/**
	 * 查询实体列表 ；由于我们需要返回的是List，因此使用BeanListHandler
	 */
	public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
		List<T> entityList;
		try {
			Connection conn = getConnection();
			entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		}
		return entityList;
	}

	/**
	 * 查询实体
	 */
	public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
		T entity;
		try {
			Connection conn = getConnection();
			entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity failure", e);
			throw new RuntimeException(e);
		}
		return entity;
	}

	/**
	 * 插入实体
	 */
	public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not insert entity: fieldMap is empty");
			return false;
		}

		String sql = "INSERT INTO " + getTableName(entityClass);
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(", ");
			values.append("?, ");
		}
		columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
		values.replace(values.lastIndexOf(", "), values.length(), ")");
		sql += columns + " VALUES " + values;

		Object[] params = fieldMap.values().toArray();

		return executeUpdate(sql, params) == 1;
	}

	/**
	 * 更新实体
	 */
	public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not update entity: fieldMap is empty");
			return false;
		}

		String sql = "UPDATE " + getTableName(entityClass) + " SET ";
		StringBuilder columns = new StringBuilder();
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(" = ?, ");
		}
		sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id = ?";

		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = paramList.toArray();

		return executeUpdate(sql, params) == 1;
	}

	/**
	 * 删除实体
	 */
	public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
		String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id = ?";
		return executeUpdate(sql, id) == 1;
	}

	/**
	 * 执行 SQL 文件
	 */
	public static void executeSqlFile(String filePath) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			String sql;
			while ((sql = reader.readLine()) != null) {
				if (!(sql.indexOf("--") != -1)) {
					executeUpdate(sql);
				}
			}
		} catch (Exception e) {
			LOGGER.error("execute sql file failure", e);
			throw new RuntimeException(e);
		}
	}

	private static String getTableName(Class<?> entityClass) {
		return entityClass.getSimpleName();
	}
}
