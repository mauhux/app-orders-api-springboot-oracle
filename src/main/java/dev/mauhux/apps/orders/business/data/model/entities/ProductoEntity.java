package dev.mauhux.apps.orders.business.data.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NamedStoredProcedureQuery(
        name = "Producto.getAllProductos",
        procedureName = "SP_GET_PRODUCTOS",
        resultClasses = ProductoEntity.class,
        parameters = {
                @StoredProcedureParameter(
                        mode = ParameterMode.REF_CURSOR,
                        name = "RESULTADO",
                        type = void.class
                )
        }
)

@Entity
@Table(name = "PRODUCTOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTOS_SEQ")
    @SequenceGenerator(name = "PRODUCTOS_SEQ", sequenceName = "PRODUCTOS_SEQ", allocationSize = 1)
    private Integer id;

    private String descripcion;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    private BigDecimal stock;

    private String estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}
