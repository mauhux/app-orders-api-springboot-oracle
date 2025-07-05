package dev.mauhux.apps.orders.business.data.repositories;

import dev.mauhux.apps.orders.business.data.model.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {

    @Procedure(name = "Producto.getAllProductos")
    List<ProductoEntity> findAllProductosFromProcedure();

    @Query(value = "SELECT ID, DESCRIPCION, PRECIO_UNITARIO, STOCK, ESTADO, FECHA_CREACION " +
            " FROM PRODUCTOS " +
            " ORDER BY DESCRIPCION ", nativeQuery = true)
    List<ProductoEntity> findAllProductosFromNativeQuery();
}
