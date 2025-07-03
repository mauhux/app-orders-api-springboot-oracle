package dev.mauhux.apps.orders.business.data.repositories;

import dev.mauhux.apps.orders.business.data.model.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
}
