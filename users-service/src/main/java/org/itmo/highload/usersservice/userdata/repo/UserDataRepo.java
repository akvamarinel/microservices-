package org.itmo.highload.usersservice.userdata.repo;

import org.itmo.highload.usersservice.userdata.model.UserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserDataRepo extends CrudRepository<UserData, UUID> {
    UserData findByLogin(String login);
    Boolean existsByLogin(String login);

}
