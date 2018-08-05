package com.bonc.daotest;

import com.bonc.dao.RoleDao;
import com.bonc.dao.UserDao;
import com.bonc.dao.impl.RoleDaoImpl;
import com.bonc.dao.impl.UserDaoImpl;
import org.junit.Test;

public class BaseDaoTest {
    @Test
    public void test(){
        RoleDao rle = new RoleDaoImpl();
        UserDao ud = new UserDaoImpl();
    }
}
