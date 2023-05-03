package service;

import model.User;
import util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService{
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM User");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setCountry(resultSet.getString("Country"));
                user.setEmail(resultSet.getString("Email"));
                user.setName(resultSet.getString("Name"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                ConnectionDB.closeConnection(conn);
            }
        }
        return users;
    }

    @Override
    public void save(User user) {
        if (findById(user.getId()) == null) {
            createUser(user);
        }
    }

    private void createUser(User user) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO User(Name, Email, Country) VALUES (?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                ConnectionDB.closeConnection(conn);
            }
        }
    }
    @Override
    public User findById(int id) {
        for (User user: findAll()) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void remove(int id) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM User WHERE ID = " + id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                ConnectionDB.closeConnection(conn);
            }
        }
    }
}
