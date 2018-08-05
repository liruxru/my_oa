package com.bonc.dao.impl;

import com.bonc.dao.BaseDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    protected Class<T> domainClass;

  /*  public BaseDaoImpl(String domainClassName) {
        try {
            this.domainClass = (Class<T>) Class.forName(domainClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    public BaseDaoImpl() {
        // 获取泛型的父类
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = type.getActualTypeArguments();
        this.domainClass = (Class<T>) actualTypeArguments[0];
//        System.out.println(domainClass);
    }

    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);

    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public void delete(Long id) {
        T entity = this.getById(id);
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public T getById(Long id) {
        return this.getHibernateTemplate().get(domainClass,id);
    }

    @Override
    public List<T> findAll() {

        return this.getHibernateTemplate().loadAll(domainClass);
    }
}
