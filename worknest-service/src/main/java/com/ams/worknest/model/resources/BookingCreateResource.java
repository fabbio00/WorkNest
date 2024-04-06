package com.ams.worknest.model.resources;

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

    //private User user;

    //private WorkStation workStation;

}
