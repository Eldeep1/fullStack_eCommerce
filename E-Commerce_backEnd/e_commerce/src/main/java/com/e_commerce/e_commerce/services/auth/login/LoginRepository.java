package com.e_commerce.e_commerce.services.auth.login;

import com.e_commerce.e_commerce.models.User;




import java.sql.*;

import com.e_commerce.e_commerce.helper.DatabaseHelper;

public class LoginRepository extends DatabaseHelper
{

    
    public User checkForCredentials(String email, String password) {
        if (setConnection()) {
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Credentials are correct, construct and return a User object
                        int id = resultSet.getInt("id");
                        String firstName = resultSet.getString("firstName");
                        String lastName = resultSet.getString("lastName");
                        String userName = resultSet.getString("userName");
                        String token = resultSet.getString("token");
                        // Construct User object with retrieved data
                        return new User(id,token,firstName,lastName,userName,email);
                    } else {
                        // User not found or credentials are incorrect
                        return null;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Log the error
            } finally {
                closeConnection();
            }
        }
        return null;
    }
    

}
