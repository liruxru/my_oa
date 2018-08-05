package com.bonc.dao.impl;

import com.bonc.dao.UserDao;
import com.bonc.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
   /* public UserDaoImpl() {
        this.domainClass=User.class;
    }*/
}
