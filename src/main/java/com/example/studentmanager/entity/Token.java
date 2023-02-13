package com.example.studentmanager.entity;


import com.example.studentmanager.entity.base.AbstractAuditingEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@Entity
//@Table(name = "tokens")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Token extends AbstractAuditingEntity {
  private String value;
  private Date expireTime;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

}
