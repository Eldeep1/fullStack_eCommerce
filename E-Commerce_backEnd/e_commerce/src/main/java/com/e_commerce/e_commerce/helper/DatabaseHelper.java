package com.e_commerce.e_commerce.helper;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;



public class DatabaseHelper {
    private String databaseName;
    private String databaseUserName;
    private String databasePassword;
    protected Connection connection = null;

    public DatabaseHelper() {
        this("market", "root", "1234");
    }
    public DatabaseHelper(String databaseName, String databaseUserName, String databasePassword) {
        this.databaseName = databaseName;
        this.databaseUserName = databaseUserName;
        this.databasePassword = databasePassword;
    }

    
    public Map<Object, Object> getData(String tableName, String attributeName, String attributeValue) {
        if (setConnection()) {
            Map<Object, Object> data = new LinkedHashMap<>();
            String sql = "SELECT * FROM " + tableName + " WHERE " + attributeName + " = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, attributeValue);
                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    while (resultSet.next()) {
                        Map<String, Object> rowData = new LinkedHashMap<>();
                        for (int i = 1; i <= columnCount; i++) {
                            String columnName = metaData.getColumnName(i);
                            Object columnValue = resultSet.getObject(i);
                            rowData.put(columnName, columnValue);
                        }
                        data.put(resultSet.getRow(), rowData);
                    }
                }
                return data;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
        return Collections.emptyMap();
    }


    protected boolean setConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.databaseName, this.databaseUserName, this.databasePassword);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
