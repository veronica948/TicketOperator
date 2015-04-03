package by.bsu.first.entity;

/**
 * Created by Пользователь on 11.10.2014.
 */
public class User {
    private int userId;
    private String login;
    private int role;

    public User(int userId, String login, int role) {
        this.userId = userId;
        this.login = login;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getUserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "login=" + login +
                '}';
    }
}
