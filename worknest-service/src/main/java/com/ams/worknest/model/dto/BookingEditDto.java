package com.ams.worknest.model.dto;

import com.ams.worknest.model.entities.WorkStation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEditDto {

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private UUID workStationId;


}
