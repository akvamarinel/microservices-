package org.itmo.highload.usersservice.delivery.model;

import lombok.*;
import org.itmo.highload.usersservice.userdata.model.UserData;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @Column(name = "id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_data_id", referencedColumnName = "id")
    private UserData userData;
}

