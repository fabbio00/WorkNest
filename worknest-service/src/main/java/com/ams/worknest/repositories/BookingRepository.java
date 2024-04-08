package com.ams.worknest.repositories;

import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query(value = "SELECT * FROM booking WHERE DATE(start_date) = :date", nativeQuery = true)
    List<Booking> findByStartDateOnly(LocalDate date);

    List<Booking> findByUser(User user);

}
