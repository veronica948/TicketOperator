package by.bsu.first.logic;

import by.bsu.first.dao.UserDAO;
import by.bsu.first.entity.User;
import by.bsu.first.exception.DAOException;
import by.bsu.first.exception.HashingException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.exception.PoolConnectionException;
import by.bsu.first.md5.MD5Hashing;
import by.bsu.first.pool.Pool;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Пользователь on 07.10.2014.
 */
public class LoginLogic {
    public static User checkUser(String login, String password) throws LogicException {
        Connection cn = null;
        User user = null;
        UserDAO userDAO = null;
        try {
            cn = Pool.getPool().getConnection();
            userDAO = new UserDAO(cn);
            user = userDAO.findUserByLoginAndPassword(login, MD5Hashing.hashingPassword(password));
        } catch (DAOException | PoolConnectionException |HashingException e) {
            throw new LogicException(e.getCause());
        }finally {
            Pool.getPool().returnConnection(cn);
        }
        return user;
    }
}
