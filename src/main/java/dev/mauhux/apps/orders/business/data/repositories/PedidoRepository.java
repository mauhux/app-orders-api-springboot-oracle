package dev.mauhux.apps.orders.business.data.repositories;

import dev.mauhux.apps.orders.business.data.model.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

    @Query("""
      SELECT p
      FROM PedidoEntity p
      WHERE p.fecha BETWEEN :desde AND :hasta
        AND (:estado IS NULL OR p.estado = :estado)
      ORDER BY p.fecha DESC
      """)
    List<PedidoEntity> findByFechaBetweenAndEstado(
            @Param("desde") LocalDate desde,
            @Param("hasta") LocalDate hasta,
            @Param("estado") String estado
    );
}
