package com.ams.worknest.model.resources;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WorkStationResource {

    private String name;

    private double pricePerH;

    private String equipment;

    private String type;

    private int floor;

}
