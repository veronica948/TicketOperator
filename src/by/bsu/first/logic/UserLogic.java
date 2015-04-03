package by.bsu.first.logic;

import by.bsu.first.dao.UserDAO;
import by.bsu.first.exception.DAOException;
import by.bsu.first.exception.HashingException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.exception.PoolConnectionException;
import by.bsu.first.md5.MD5Hashing;
import by.bsu.first.pool.Pool;
import java.sql.Connection;
import java.util.ArrayList;
/**
 * Created by Пользователь on 01.03.2015.
 */
public class UserLogic {
    public static ArrayList<String> getInfoAboutUser(int userId)
            throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            UserDAO dao = new UserDAO(cn);
            ArrayList<String> info = dao.findUserInfoById(userId);
            return info;
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static void changeData(int userId, String lastName, String firstName, String email)
            throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            UserDAO dao = new UserDAO(cn);
            dao.changeUserData(userId, lastName, firstName, email);
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static void changePassword(int userId, String password)
            throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            UserDAO dao = new UserDAO(cn);
            dao.changePassword(userId, MD5Hashing.hashingPassword(password));
        } catch (PoolConnectionException | DAOException | HashingException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static boolean equalsToOldPassword(int userId, String password)
            throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            UserDAO dao = new UserDAO(cn);
            String oldPassword = dao.getPasswordByUserId(userId);
            String hashingPassword = MD5Hashing.hashingPassword(password);
            if (oldPassword.equals(hashingPassword)) {
                return true;
            } else {
                return false;
            }

        } catch (PoolConnectionException | DAOException | HashingException e) {
            throw new LogicException(e.getCause());
        }finally {
            Pool.getPool().returnConnection(cn);
        }
    }

}
