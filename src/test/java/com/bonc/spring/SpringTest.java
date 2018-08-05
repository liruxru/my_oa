package com.bonc.spring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    ApplicationContext app = new ClassPathXmlApplicationContext("spring.xml");
    @Test
    public void testSessionFactory(){
        SessionFactory sessionFactory = (SessionFactory) app.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        System.out.println("\n\n\n"+session);
    }
}
