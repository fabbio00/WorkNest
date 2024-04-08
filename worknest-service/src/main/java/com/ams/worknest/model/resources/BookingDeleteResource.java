package com.ams.worknest.model.resources;

import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.entities.WorkStation;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookingDeleteResource {

    private UUID bookingId;

    private String status;

}
