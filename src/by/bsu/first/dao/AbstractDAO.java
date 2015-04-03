package by.bsu.first.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Пользователь on 11.10.2014.
 */
public abstract class AbstractDAO {
    static Logger logger = Logger.getLogger(AbstractDAO.class);
    protected Connection connection;
    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }
    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            logger.error("SqlException(impossible to close statement)");
        }
    }
}
