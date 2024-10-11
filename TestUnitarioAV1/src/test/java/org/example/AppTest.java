package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void testCriarProdutoComValoresValidos() {
        Produto produto = new Produto("manga", 33.0, 7);
        assertEquals("manga", produto.getNome());
        assertEquals(33.0, produto.getPreco());
        assertEquals(7, produto.getEstoque());
    }

    @Test
    void testCriarProdutoComPrecoNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Produto("manga", -1.0, 7);
        });
        assertEquals("Preço não pode ser negativo", exception.getMessage());
    }

    @Test
    void testCriarProdutoComEstoqueNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Produto("manga", 33.0, -1);
        });
        assertEquals("Estoque não pode ser negativo", exception.getMessage());
    }

    @Test
    void testAlterarNomeProduto() {
        Produto produto = new Produto("abacaxi", 10.0, 5);
        produto.setNome("Produto Novo");
        assertEquals("Produto Novo", produto.getNome());
    }

    @Test
    void testAlterarPrecoProduto() {
        Produto produto = new Produto("Maconha", 10.0, 5);
        produto.setPreco(15.0);
        assertEquals(15.0, produto.getPreco());
    }

    @Test
    void testAlterarEstoqueProduto() {
        Produto produto = new Produto("feijao", 10.0, 5);
        produto.setEstoque(10);
        assertEquals(10, produto.getEstoque());
    }

    @Test
    void testDiminuirEstoqueComQuantidadeMenorQueEstoque() {
        Produto produto = new Produto("banana", 10.0, 5);
        assertTrue(produto.diminuirEstoque(3));
        assertEquals(2, produto.getEstoque());
    }

    @Test
    void testDiminuirEstoqueComQuantidadeIgualEstoque() {
        Produto produto = new Produto("HD", 11.0, 5);
        assertTrue(produto.diminuirEstoque(5));
        assertEquals(0, produto.getEstoque());
    }

    @Test
    void testDiminuirEstoqueComQuantidadeMaiorQueEstoque() {
        Produto produto = new Produto("cenoura", 10.0, 5);
        assertFalse(produto.diminuirEstoque(6));
        assertEquals(5, produto.getEstoque());
    }

    @Test
    void testAumentarEstoque() {
        Produto produto = new Produto("maconha", 10.0, 5);
        produto.aumentarEstoque(3);
        assertEquals(8, produto.getEstoque());
    }

    @Test
    void testAlterarPrecoProdutoParaNegativo() {
        Produto produto = new Produto("produto", 10.0, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            produto.setPreco(-5.0);
        });
        assertEquals("Preço não pode ser negativo", exception.getMessage());
    }

    @Test
    void testCalcularTotalVenda() {
        Produto produto = new Produto("produto", 10.0, 5);
        double total = produto.calcularTotalVenda(3);
        assertEquals(30.0, total);
    }

    @Test
    void testVendaComQuantidadeNegativa() {
        Produto produto = new Produto("produto", 10.0, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            produto.diminuirEstoque(-1);
        });
        assertEquals("Quantidade deve ser positiva", exception.getMessage());
    }

    @Test
    void testRealizarVendaComEstoqueInsuficiente() {
        Produto produto = new Produto("produto", 10.0, 5);
        boolean resultado = produto.diminuirEstoque(6);
        assertFalse(resultado);
        assertEquals(5, produto.getEstoque());
    }

    @Test
    void testRealizarVendaDeProdutoQueNaoExiste() {
        Estoque estoque = new Estoque();
        Produto produto = new Produto("produto inexistente", 10.0, 0);
        estoque.adicionarProduto(produto);
        boolean resultado = estoque.venderProduto("produto inexistente", 1);
        assertFalse(resultado);
    }

    @Test
    void testAumentoDeEstoqueAposReposicao() {
        Produto produto = new Produto("produto", 10.0, 5);
        produto.aumentarEstoque(10);
        assertEquals(15, produto.getEstoque());
        produto.diminuirEstoque(5);
        assertEquals(10, produto.getEstoque());
    }

    @Test
    void testComportamentoDeVendaComEstoqueInicialZero() {
        Produto produto = new Produto("produto", 10.0, 0);
        assertFalse(produto.diminuirEstoque(1));
    }

    @Test
    void testAlterarPrecoECalcularTotalVenda() {
        Produto produto = new Produto("produto", 10.0, 5);
        produto.setPreco(15.0);
        double total = produto.calcularTotalVenda(2);
        assertEquals(30.0, total);
    }

    @Test
    void testCriarVariosProdutosERealizarVendasComEstoqueCompartilhado() {
        Produto produto1 = new Produto("produto1", 10.0, 5);
        Produto produto2 = new Produto("produto2", 15.0, 10);

        produto1.diminuirEstoque(2);
        produto2.diminuirEstoque(3);

        assertEquals(3, produto1.getEstoque());
        assertEquals(7, produto2.getEstoque());
    }

    @Test
    void testAlterarEstoqueAposTentativaDeVendaComEstoqueInsuficiente() {
        Produto produto = new Produto("produto", 10.0, 5);
        produto.diminuirEstoque(6);
        assertEquals(5, produto.getEstoque());
    }
}
