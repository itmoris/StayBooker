package com.staybooker.dao;

import com.staybooker.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.staybooker.util.TransactionUtil.executeWithResult;
import static com.staybooker.util.TransactionUtil.executeWithoutResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<User, Long> {
    @Getter
    private static final UserDao INSTANCE = new UserDao();

    @Override
    public User getById(Long key) {
        return executeWithResult(session -> session.get(User.class, key));
    }

    public User getByEmail(String email) {
        return executeWithResult(session ->
                session.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                        .setParameter("email", email)
                        .uniqueResult()
        );
    }

    @Override
    public List<User> getAll() {
        return executeWithResult(session ->
                session.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class).list()
        );
    }

    @Override
    public void save(User entity) {
        executeWithoutResult(session -> session.persist(entity));
    }

    @Override
    public void update(User entity) {
        executeWithoutResult(session -> session.merge(entity));
    }

    @Override
    public void delete(User entity) {
        executeWithoutResult(session -> session.remove(entity));
    }
}
