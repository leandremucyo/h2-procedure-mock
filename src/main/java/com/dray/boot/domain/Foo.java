package com.dray.boot.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@SequenceGenerator(name = "SEQ_ID", sequenceName = "action_seq", allocationSize = 100)
public class Foo {

  @Id
  @GeneratedValue(generator = "SEQ_ID", strategy = GenerationType.SEQUENCE)
  @Access(AccessType.PROPERTY)
  private Long id;

  @Column(length = 100, updatable = false, nullable = false)
  @Enumerated(EnumType.STRING)
  private FooType fooType;

  public enum FooType {
    American,
    Asian,
    African,
    European
  }
}
