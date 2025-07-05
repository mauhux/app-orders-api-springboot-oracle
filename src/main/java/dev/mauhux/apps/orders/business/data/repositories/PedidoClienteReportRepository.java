package dev.mauhux.apps.orders.business.data.repositories;

import dev.mauhux.apps.orders.business.data.model.entities.PedidoClienteReportVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoClienteReportRepository extends JpaRepository<PedidoClienteReportVo, Integer> {
}
