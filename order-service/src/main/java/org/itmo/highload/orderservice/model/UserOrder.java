package org.itmo.highload.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_order")
public class UserOrder {
    @Id
    @Column(name = "id")
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_time")
    private Date orderTime;

    @Column(name = "customer_id")
    private UUID customerId;

//    @Column(name = "delivery_id")
//    private UUID deliveryId;

}
