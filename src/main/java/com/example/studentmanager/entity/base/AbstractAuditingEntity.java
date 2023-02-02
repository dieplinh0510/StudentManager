package com.example.studentmanager.entity.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public abstract class AbstractAuditingEntity implements Serializable {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "active_flag")
  private Boolean activeFlag = Boolean.TRUE;

  @Column(name = "delete_flag")
  private Boolean deleteFlag = Boolean.FALSE;

  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "last_modified_by")
  private Long lastModifiedBy;

  @CreationTimestamp
  @Column(name = "created_date")
  private Timestamp createdDate;

  @UpdateTimestamp
  @Column(name = "last_modified_date")
  private Timestamp lastModifiedDate;
}
