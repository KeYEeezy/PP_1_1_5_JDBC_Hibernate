package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS USERS \n" +
                    "(\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    name VARCHAR(255) NOT NULL,\n" +
                    "    lastName VARCHAR(30) NOT NULL,\n" +
                    "    age int NOT NULL\n" +
                    "    \n" +
                    ")").executeUpdate();
            Util.getSessionFactory().close();


        } catch (HibernateException e) {

            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS USERS;").executeUpdate();
            Util.getSessionFactory().close();

        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            System.out.println("User с именем - " + name + " добавлен в базу");
            transaction.commit();
            Util.getSessionFactory().close();


        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }


    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            Util.getSessionFactory().close();


        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }


    }

    @Override
    public List<User> getAllUsers() {
        List<User> res = null;
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            res = session.createQuery("from User").getResultList();
            transaction.commit();
            Util.getSessionFactory().close();


        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return res;

    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery("delete from users").executeUpdate();
            Util.getSessionFactory().close();

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }
}
