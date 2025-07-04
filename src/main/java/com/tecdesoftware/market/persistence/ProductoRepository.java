package com.tecdesoftware.market.persistence;
import com.tecdesoftware.market.persistence.crud.ProductoCrudRepository;
import com.tecdesoftware.market.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Esta anotación le dice a Spring que este archivo se enlaza con la Base de Datos(BD)
@Repository
public class ProductoRepository
{
    private ProductoCrudRepository productoCrudRepository;

    //Equivalente a poner SELECT * FROM productos
    public List<Producto> getAll() {
        //Se castea de iterable a lista
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria) {
        return productoCrudRepository.findByIdCategoriaOrderByNameAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    // Obtener un producto dado el id
    public Optional<Producto> getProducto(int idProducto) {
        return productoCrudRepository.findById(idProducto);
    }

    // Obtener un producto dado el id
    public List<Producto> save(Producto producto) {
        return (List<Producto>) productoCrudRepository.save(producto);
    }

    // Eliminar producto por Id
    public void delete(int idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }
}