
package dao;

import dao.entities.User;
import java.util.List;

/**
 *
 * @author nastja
 */
public interface UserDao {
    User create(String login, String password, String name);
    User find(int id);
    List<User> findAll();
    boolean update(User user);
    boolean delete(User user);
    User find(String password, String login);

    public boolean findByLogin(String login);

    public boolean findByPassword(char[] password);
}
