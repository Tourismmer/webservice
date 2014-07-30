package com.tourismmer.app.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.tourismmer.app.util.Util;

public class ConnectionDAO {
	
	protected static void setStatement(int posicao, Object param, PreparedStatement stmt) throws SQLException {
		
		if (param instanceof String) {
			stmt.setString(posicao, (String) param);
			return;
		}
		
		if (param instanceof Integer) {
			stmt.setInt(posicao, (Integer) param);
			return;
		}
		
		if (param instanceof Long) {
			stmt.setLong(posicao, (Long) param);
			return;
		}
		
		if (param instanceof Date) {
			stmt.setString(posicao, Util.dateToMysql((Date) param));
			return;
		}
		
		stmt.setString(posicao, Util.getString(param));
		
	}
	
}
