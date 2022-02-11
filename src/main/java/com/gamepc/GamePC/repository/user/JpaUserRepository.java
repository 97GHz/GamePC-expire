package com.gamepc.GamePC.repository.user;

import com.gamepc.GamePC.domain.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(em.createQuery("select u from User u where name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(em.createQuery("select u from User u where email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult());
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
