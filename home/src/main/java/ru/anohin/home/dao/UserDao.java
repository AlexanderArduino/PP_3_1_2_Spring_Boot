package ru.anohin.home.dao;



import ru.anohin.home.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(Long id);

}
