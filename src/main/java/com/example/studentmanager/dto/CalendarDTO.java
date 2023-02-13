package com.example.studentmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDTO {
  private Integer type;
  private String timeStart;
  private String timeEnd;
  private String position;
  private Date date;
}
