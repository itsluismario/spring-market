package com.spring_market.persistence.mapper;

import com.spring_market.domain.Purchase;
import com.spring_market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.spring_market.persistence.entity.ComprasProducto;
import com.spring_market.persistence.entity.ComprasProductoPK;
import java.util.stream.Collectors;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {
    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items"),
    })
    Purchase toPurchase(Compra compra);
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    default Compra toCompra(Purchase purchase) {
        Compra compra = new Compra();
        compra.setIdCliente(purchase.getClientId());
        compra.setFecha(purchase.getDate());
        compra.setMedioPago(purchase.getPaymentMethod());
        compra.setComentario(purchase.getComment());
        compra.setEstado(purchase.getState());

        if (purchase.getItems() != null) {
            compra.setProductos(purchase.getItems().stream()
                    .map(item -> {
                        ComprasProducto producto = new ComprasProducto();
                        ComprasProductoPK pk = new ComprasProductoPK();
                        pk.setIdProducto(item.getProductId());
                        producto.setId(pk);
                        producto.setCantidad(item.getQuantity());
                        producto.setTotal(item.getTotal());
                        producto.setEstado(item.isActive());
                        producto.setCompra(compra);
                        return producto;
                    })
                    .collect(Collectors.toList()));
        }

        return compra;
    }
}
