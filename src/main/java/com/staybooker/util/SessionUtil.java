package com.staybooker.util;

import com.staybooker.entity.Apartment;
import com.staybooker.entity.Reservation;
import com.staybooker.entity.Review;
import com.staybooker.entity.User;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class SessionUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = getConfiguration().buildSessionFactory();
        }

        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        return new Configuration()
                .setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy())
                .setProperty("hibernate.connection.url", System.getenv("STAYBOOKER_URL"))
                .setProperty("hibernate.connection.username", System.getenv("STAYBOOKER_USERNAME"))
                .setProperty("hibernate.connection.password", System.getenv("STAYBOOKER_PASSWORD"))
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Apartment.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Reservation.class)
                .configure();
    }
}
