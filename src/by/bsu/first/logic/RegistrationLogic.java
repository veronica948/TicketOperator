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

import java.sql.Connection;

/**
 * Created by Пользователь on 10.11.2014.
 */
public class RegistrationLogic {

    public static boolean loginAlreadyExist(String login) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            UserDAO dao = new UserDAO(cn);
            boolean isExist = dao.findUserByLogin(login);
            return isExist;
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static User insertUser(String login, String password, int roleId, String lastName, String firstName, String email)
            throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            UserDAO dao = new UserDAO(cn);
            dao.insert(login, MD5Hashing.hashingPassword(password), roleId, lastName, firstName, email);
            int userId = dao.findUserIdByLogin(login);
            User user = new User(userId, login, roleId);
            return user;
        } catch (PoolConnectionException | DAOException | HashingException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }
}
