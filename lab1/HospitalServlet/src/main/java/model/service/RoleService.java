package model.service;

import model.dao.classes.RoleDAOImpl;
import model.entity.Role;
import utils.factory.dao.DaoFactory;
import utils.factory.dao.DaoFactoryImpl;

public class RoleService {
    private static DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    private static RoleDAOImpl roleDao = daoFactory.createRoleDAO();

    public RoleService() { }

    public Role getRoleById(int roleId) {
        return roleDao.findById(roleId);
    }

    public Role getRoleByName(String roleName) {
        return roleDao.findByName(roleName);
    }
}
