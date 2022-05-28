package model.dao.interfaces;

import model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    /**
     * The method returns all users
     *
     * @return - list of users
     */
    List<User> findAll();

    /**
     * The method finds user by email and password and returns it
     *
     * @param email - email of user
     * @param password - password of user
     * @return - expected user
     */
    Optional<User> findByEmailAndPass(String email, String password);

    /**
     * The method finds user by email and returns user id
     *
     * @param email - email of user
     * @return - id of user
     */
    int findUserIdByEmail(String email);

    /**
     * The method inserts new user into database
     *
     * @param user - created object of user
     * @return - is user successfully inserted
     */
    boolean addUser(User user);

    /**
     * The method finds user by id and returns user
     *
     * @param id - id of user
     * @return - expected user
     */
    Optional<User> findById(int id);

    /**
     * The method updates role of specific user
     *
     * @param role - id of role
     * @param userId - id of user
     */
    void updateRole(int role, int userId);

    /**
     * The method deletes user from database
     *
     * @param userId - id of user
     * @return - is user successfully deleted
     */
    boolean deleteUserById(int userId);
}
