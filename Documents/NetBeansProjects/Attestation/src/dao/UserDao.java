/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
