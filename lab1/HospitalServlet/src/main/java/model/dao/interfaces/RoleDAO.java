package model.dao.interfaces;

import model.entity.Role;

public interface RoleDAO {

    /**
     * The method finds role by id and returns it
     *
     * @param id - id of role
     * @return - expected role
     */
    Role findById(int id);

    /**
     * The method finds role by name and returns it
     *
     * @param name - name of role
     * @return - expected role
     */
    Role findByName(String name);
}
