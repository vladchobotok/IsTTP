package model.service;

import model.dao.classes.UserDAOImpl;
import model.entity.User;
import utils.factory.dao.DaoFactory;
import utils.factory.dao.DaoFactoryImpl;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static final DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    private static final UserDAOImpl userDao = daoFactory.createUserDAO();

    public UserService() { }

    public boolean registerUser(User user) {
        return userDao.addUser(user);
    }

    public Optional<User> signIn(String login, String password) {
        return userDao.findByEmailAndPass(login, password);
    }
    public int getUserIdByEmail(String email) {
        return userDao.findUserIdByEmail(email);
    }

    public Optional<User> getUserById(int userId) {
        return userDao.findById(userId);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public void updateRole(int role, int userId){
        userDao.updateRole(role, userId);
    }

    public boolean deleteUserById(int userId){
        return userDao.deleteUserById(userId);
    }
}
