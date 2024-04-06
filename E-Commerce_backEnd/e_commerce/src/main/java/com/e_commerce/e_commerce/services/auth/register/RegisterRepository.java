package com.e_commerce.e_commerce.services.auth.register;


import com.e_commerce.e_commerce.models.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.e_commerce.e_commerce.helper.DatabaseHelper;

public class RegisterRepository extends DatabaseHelper
{

    
    public boolean setUser(User user) {
        if (setConnection()) {
            String sql = "INSERT INTO user (userName, firstName, lastName, email, password, token) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLastName());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getPassword());
                statement.setString(6, user.getToken());


                int rowsAffected = statement.executeUpdate();
                
                // Check if insertion was successful
                if (rowsAffected > 0) {
                    return true; 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
        return false; //
    }
    public Boolean checkForUsedEmails(String email) {
        if (setConnection()) {
            String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1)>0;
                    }
                }
            } catch (SQLException e) {
                // Handle SQL exception properly, e.g., log the error or throw a custom exception
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
        return false;
    }
}
