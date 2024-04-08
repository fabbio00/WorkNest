package com.ams.worknest.model.resources;

import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.entities.WorkStation;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingEditResource {

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private WorkStation workStation;
}
