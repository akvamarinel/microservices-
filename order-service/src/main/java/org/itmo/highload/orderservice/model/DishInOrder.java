package org.itmo.highload.orderservice.model;

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
@Table(name = "dish_in_order")
public class DishInOrder {
    @Id
    @Column(name="id")
    private UUID id;

    @Column(name="order_id")
    private UUID userOrderId;

    @Column(name="dish_id")
    private UUID dishId;

    @Column(name="count")
    private Integer count;

}
