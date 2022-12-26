package org.itmo.highload.restaurantservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePage {
    private Collection<?> collection;
    private boolean hasMore;

}
