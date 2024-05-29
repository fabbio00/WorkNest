package com.ams.worknest.repositories;

import com.ams.worknest.model.entities.BookingBusiness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * BookingBusinessRepository interface.
 * This interface handles the data access layer for the BookingBusiness entity.
 */
public interface BookingBusinessRepository extends JpaRepository<BookingBusiness, UUID> {
}
