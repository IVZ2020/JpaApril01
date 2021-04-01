package by.tms.dao;

import by.tms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class UserDao {

    @Autowired
    private EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Transactional(readOnly = true)
    public User findUserById (long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional(readOnly = true)
    public User findUserByLogin (String login) {
        return entityManager.find(User.class, login);
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers () {
        return entityManager
                .createQuery("from User", User.class)
                .getResultList();
    }

    public void updateNameByUser (String name, String newName) {
        entityManager
                .createQuery("from User where name = :name", User.class)
                .getSingleResult().setName(newName);
    }
}
