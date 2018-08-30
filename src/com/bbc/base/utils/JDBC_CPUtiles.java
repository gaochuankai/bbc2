package com.bbc.base.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBC_CPUtiles {

	private static ComboPooledDataSource comboPooledDataSource;
	private static QueryRunner queryRunner;

	static {
		comboPooledDataSource = new ComboPooledDataSource();
		queryRunner = new QueryRunner(comboPooledDataSource);
	}

	public static QueryRunner getQueryRunner() {
		return queryRunner;
	}

	public static ComboPooledDataSource getComboPooledDataSource() {
		return comboPooledDataSource;
	}

	public static Connection getConnection() throws SQLException {
		return comboPooledDataSource.getConnection();
	}

	public static void release(Statement statement, Connection connection) {
		try {
			if (statement != null) {
				statement.cancel();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void release(ResultSet resultSet, Statement statement,
			Connection connection) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (statement != null) {
				statement.cancel();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
