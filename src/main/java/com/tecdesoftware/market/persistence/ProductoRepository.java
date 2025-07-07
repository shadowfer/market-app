package com.tecdesoftware.market.persistence;
import com.tecdesoftware.market.domain.Product;
import com.tecdesoftware.market.domain.repository.ProductRepository;
import com.tecdesoftware.market.persistence.crud.ProductoCrudRepository;
import com.tecdesoftware.market.persistence.entity.Producto;
import com.tecdesoftware.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//Esta anotación le dice a Spring que este archivo se enlaza con la BD
@Repository
public class ProductoRepository implements ProductRepository {

    //Auto inyectado: Spring se encarga de crear la instancia
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper productMapper;

    //Equivalente a poner Select * from productos
    @Override
    public List<Product> getAll() {
        //Se castea de Iterable a Lista
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarceProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        //No hay un mapper que convierta una lista de opcionales, por lo que a los productos se le hará un map
        return productos.map(prods -> productMapper.toProducts(prods));
    }

    //Obtener un producto dado el id
    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> productMapper.toProduct(producto));
    }

    //Guardar un producto
    @Override
    public Product save(Product product) {
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }


    //Eliminar producto por IdProducto
    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}