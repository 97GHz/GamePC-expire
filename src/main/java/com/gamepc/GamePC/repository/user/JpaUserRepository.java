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
        return Optional.ofNullable(em.createQuery("select u from User u where id = :id", User.class)
                .setParameter("id", id)
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
