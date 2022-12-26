package org.itmo.highload.categoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;
}

