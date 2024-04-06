package com.ams.worknest.model.dto;

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
public class BookingCreateDto {

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private String status;

    private boolean hasPenalty;

    private UUID userId;

    private UUID workstationId;

}
