package dev.mauhux.apps.orders.security.repository;

import dev.mauhux.apps.orders.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserNameIgnoreCase(String user);
}
