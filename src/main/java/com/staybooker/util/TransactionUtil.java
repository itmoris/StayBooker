package com.staybooker.util;

import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.function.Consumer;
import java.util.function.Function;

@UtilityClass
public class TransactionUtil {
    private static final SessionFactory sessionFactory = SessionUtil.buildSessionFactory();

    public static <R> R executeWithResult(Function<Session, R> function) {
        R result;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = function.apply(session);
            session.getTransaction().commit();
        }

        return result;
    }

    public static void executeWithoutResult(Consumer<Session> consumer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            consumer.accept(session);
            session.getTransaction().commit();
        }
    }

}
