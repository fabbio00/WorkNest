package com.ams.worknest.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    private String to;

    private String subject;

    private String text;

}
