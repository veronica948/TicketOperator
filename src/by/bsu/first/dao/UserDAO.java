package by.bsu.first.dao;

import by.bsu.first.entity.User;
import by.bsu.first.exception.DAOException;
import by.bsu.first.md5.MD5Hashing;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Пользователь on 11.10.2014.
 */
public class UserDAO extends AbstractDAO {
    public static final String SQL_INSERT_USER = "INSERT INTO user (login, password, roleId, lastname,firstname,email) VALUES(?, ?, ?, ? ,?, ?)";
    public static final String SQL_FIND_USER = "SELECT userId, roleId FROM user WHERE login = ? and password = ?";
    public static final String SQL_FIND_USER_ID_BY_LOGIN = "SELECT userId FROM user WHERE login = ?";
    public static final String SQL_FIND_USER_INFO_BY_ID = "SELECT firstname,lastname,email FROM user WHERE userId = ?";
    private static final String SQL_CHANGE_USER_DATA = "UPDATE user SET email = ?, lastname = ?, firstname = ? WHERE userId = ?";
    private static final String SQL_CHANGE_PASSWORD = "UPDATE user SET password = ? WHERE userId = ?";
    private static final String SQL_GET_PASSWORD_BY_USER_ID = "SELECT password FROM user WHERE userId = ?";

    public UserDAO(Connection connection) {
        super(connection);
    }
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_FIND_USER);
            ps.setString(1,login);
            ps.setString(2,password);
            rs = ps.executeQuery();
            int roleId;
            int userId;
            if(rs.next()) {
                userId = rs.getInt(1);
                roleId = rs.getInt(2);
                user = new User(userId,login,roleId);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ",e);
        }
        finally {
            close(ps);
        }
        return user;
    }
    public void insert(String login, String password, int roleId, String lastName, String firstName, String email) throws DAOException{
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT_USER);
            ps.setString(1,login);
            ps.setString(2, password);
            ps.setInt(3, roleId);
            ps.setString(4, lastName);
            ps.setString(5,firstName);
            ps.setString(6,email);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
    }
    public boolean findUserByLogin(String login) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isExist = false;
        try {
            ps = connection.prepareStatement(SQL_FIND_USER_ID_BY_LOGIN);
            ps.setString(1,login);
            rs = ps.executeQuery();
            if(rs.next()) {
                isExist = true;
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return  isExist;
    }
    public int findUserIdByLogin(String login) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        int userId = 0;
        try {
            ps = connection.prepareStatement(SQL_FIND_USER_ID_BY_LOGIN);
            ps.setString(1,login);
            rs = ps.executeQuery();
            if(rs.next()) {
                userId = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return  userId;
    }
    public ArrayList<String> findUserInfoById(int userId) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        String firstname;
        String lastname;
        String email;
        ArrayList<String> info = new ArrayList<String>(3);
        try {
            ps = connection.prepareStatement(SQL_FIND_USER_INFO_BY_ID);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if(rs.next()) {
                firstname = rs.getString(1);
                lastname = rs.getString(2);
                email = rs.getString(3);
                info.add(firstname);
                info.add(lastname);
                info.add(email);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception in findUserInfoById (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return  info;
    }
    public void changeUserData(int userId, String lastName, String firstName, String email) throws DAOException{
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_CHANGE_USER_DATA);
            ps.setString(1,email);
            ps.setString(2, lastName);
            ps.setString(3, firstName);
            ps.setInt(4,userId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception in changeUserData (request or table failed):",e);
        }
        finally {
            close(ps);
        }
    }
    public void changePassword(int userId, String password) throws DAOException{
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_CHANGE_PASSWORD);
            ps.setString(1,password);
            ps.setInt(2,userId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception in changePassword (request failed):",e);
        }
        finally {
            close(ps);
        }
    }
    public String getPasswordByUserId(int userId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String password = null;
        try {
            ps = connection.prepareStatement(SQL_GET_PASSWORD_BY_USER_ID);
            ps.setInt(1,userId);
            rs = ps.executeQuery();
            if(rs.next()) {
                password = rs.getString(1);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception in getPasswordByUserId (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return  password;
    }
}
