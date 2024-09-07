package com.staybooker.dao;

import com.staybooker.dto.ApartmentFilterDto;
import com.staybooker.entity.Apartment;
import com.staybooker.entity.Reservation;
import jakarta.persistence.criteria.JoinType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.staybooker.util.TransactionUtil.executeWithResult;
import static com.staybooker.util.TransactionUtil.executeWithoutResult;

@Slf4j
public class ApartmentDao implements Dao<Apartment, Long> {
    @Getter
    private static final ApartmentDao INSTANCE = new ApartmentDao();

    private ApartmentDao() {
    }

    @Override
    public Apartment getById(Long key) {
        return executeWithResult(session -> session.get(Apartment.class, key));
    }

    @Override
    public List<Apartment> getAll() {
        return executeWithResult(session ->
                session.createQuery("SELECT a FROM Apartment a", Apartment.class).list()
        );
    }

    public List<Apartment> getAll(int offset, int limit) {
        return executeWithResult(session ->
                session.createQuery("SELECT a FROM Apartment a", Apartment.class)
                        .setFirstResult(offset)
                        .setMaxResults(limit)
                        .getResultList()
        );
    }

    public List<Apartment> getAll(ApartmentFilterDto filterDto, int offset, int limit) {
        return executeWithResult(session -> {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            JpaCriteriaQuery<Apartment> query = cb.createQuery(Apartment.class);
            JpaRoot<Apartment> root = query.from(Apartment.class);
            JpaJoin<Apartment, Reservation> reservationsJoin = root.join("reservations", JoinType.LEFT);

            JpaPredicate[] restrictions = buildRestrictions(root, reservationsJoin, cb, filterDto);

            return session
                    .createQuery(
                            query.select(root).where(restrictions)
                    )
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        });
    }

    public long countAll(ApartmentFilterDto filterDto) {
        return executeWithResult(session -> {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            JpaCriteriaQuery<Long> query = cb.createQuery(Long.class);
            JpaRoot<Apartment> root = query.from(Apartment.class);
            JpaJoin<Apartment, Reservation> reservationsJoin = root.join("reservations", JoinType.LEFT);

            JpaPredicate[] restrictions = buildRestrictions(root, reservationsJoin, cb, filterDto);

            return session
                    .createQuery(
                            query.select(cb.count(root)).where(restrictions)
                    )
                    .uniqueResult();
        });
    }

    public long countAll() {
        return executeWithResult(session ->
                session.createQuery("SELECT count(a) FROM Apartment a", Long.class)
                        .uniqueResult()
        );
    }

    @Override
    public void save(Apartment entity) {
        executeWithoutResult(session -> session.persist(entity));
    }

    @Override
    public void update(Apartment entity) {
        executeWithoutResult(session -> session.merge(entity));
    }

    @Override
    public void delete(Apartment entity) {
        executeWithoutResult(session -> session.remove(entity));
    }

    private JpaPredicate[] buildRestrictions(
            JpaRoot<Apartment> root,
            JpaJoin<Apartment, Reservation> join,
            HibernateCriteriaBuilder cb,
            ApartmentFilterDto filterDto) {
        List<JpaPredicate> restrictions = new ArrayList<>();

        String city = filterDto.getCity();
        if (Objects.nonNull(city)) {
            restrictions.add(cb.ilike(root.get("location"), "%" + city + "%"));
        }

        Integer guests = filterDto.getGuests();
        if (Objects.nonNull(guests)) {
            restrictions.add(cb.ge(root.get("maxGuests"), guests));
        }

        LocalDate checkIn = filterDto.getCheckIn();
        LocalDate checkOut = filterDto.getCheckOut();
        if (Objects.nonNull(checkIn) && Objects.nonNull(checkOut)) {
            restrictions.add(
                    cb.or(
                            cb.or(
                                    cb.isNull(join.get("checkIn")),
                                    cb.isNull(join.get("checkOut"))
                            ),
                            cb.and(
                                    cb.greaterThanOrEqualTo(join.get("checkOut"), checkIn),
                                    cb.lessThanOrEqualTo(join.get("checkIn"), checkOut)
                            ).not()
                    )
            );
        }

        return restrictions.toArray(JpaPredicate[]::new);
    }
}
