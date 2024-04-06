package com.ams.worknest.model.resources;

import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.entities.WorkStation;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookingCreateResource {

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private User user;

    private WorkStation workStation;

}
