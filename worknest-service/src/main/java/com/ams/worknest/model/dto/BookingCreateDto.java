package com.ams.worknest.model.dto;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookingCreateDto {

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private String status;

    private boolean hasPenalty;

    private UUID userId;

    private UUID workstationId;

}
