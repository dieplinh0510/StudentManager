package com.example.studentmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Integer type;
    private Long userId;
}
