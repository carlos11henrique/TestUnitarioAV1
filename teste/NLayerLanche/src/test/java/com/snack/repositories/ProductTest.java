package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private ProductRepository productRepository;
    private Product product1;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        product1 = new Product(1, "Hot Dog", 10.4f, "");
    }

    @Test
    public void verificarSeOProdutoEstaNoArray() {
        productRepository.append(product1);
        Product productId1 = productRepository.getById(1);
        assertNotNull(productId1);
    }

    @Test
    public void verificarSeProdutoEhRecuperadoPorId() {
        productRepository.append(product1);
        Product retrievedProduct = productRepository.getById(1);
        assertEquals(product1, retrievedProduct);
    }

    @Test
    public void confirmarSeProdutoExiste() {
        productRepository.append(product1);
        assertTrue(productRepository.exists(1));
    }

    @Test
    public void testarSeProdutoEhRemovidoCorretamente() {
        productRepository.append(product1);
        productRepository.remove(1);
        assertFalse(productRepository.exists(1));
    }

    @Test
    public void verificarSeProdutoEhAtualizadoCorretamente() {
        productRepository.append(product1);
        Product updatedProduct = new Product(1, "Hot Dog", 12.0f, "");
        productRepository.update(updatedProduct);
        assertEquals(updatedProduct.getPrice(), productRepository.getById(1).getPrice());
    }

    @Test
    public void testarSeTodosOsProdutosSaoRecuperadosCorretamente() {
        Product product2 = new Product(2, "Hamburger", 8.5f, "");
        productRepository.append(product1);
        productRepository.append(product2);
        assertEquals(2, productRepository.getAll().size());
    }

    @Test
    public void verificarComportamentoAoRemoverProdutoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            productRepository.remove(999);
        });
    }

    @Test
    public void testarAtualizacaoDeProdutoInexistente() {
        Product updatedProduct = new Product(999, "Pizza", 15.0f, "");
        assertThrows(IllegalArgumentException.class, () -> {
            productRepository.update(updatedProduct);
        });
    }

    @Test
    public void verificarAdicaoDeProdutosComIdsDuplicados() {
        productRepository.append(product1);
        Product duplicateProduct = new Product(1, "Hot Dog Duplicate", 11.0f, "");
        assertThrows(IllegalArgumentException.class, () -> {
            productRepository.append(duplicateProduct);
        });
    }

    @Test
    public void confirmarListaVaziaAoInicializarRepositorio() {
        assertTrue(productRepository.getAll().isEmpty());
    }
}
