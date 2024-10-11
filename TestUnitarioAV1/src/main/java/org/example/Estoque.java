package org.example;

import org.example.Produto;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<String, Produto> produtos = new HashMap<>();

    public void adicionarProduto(Produto produto) {
        if (produtos.containsKey(produto.getNome())) {
            throw new IllegalArgumentException("Produto já existe no estoque");
        }
        produtos.put(produto.getNome(), produto);
    }

    public boolean venderProduto(String nome, int quantidade) {
        Produto produto = produtos.get(nome);
        if (produto == null) {
            System.out.println("Produto não encontrado");
            return false;
        }
        if (!produto.diminuirEstoque(quantidade)) {
            System.out.println("Estoque insuficiente");
            return false;
        }
        return true;
    }

    public Produto obterProduto(String nome) {
        return produtos.get(nome);
    }

    public Map<String, Produto> listarProdutos() {
        return new HashMap<>(produtos); // Retorna uma cópia do mapa
    }

    public void atualizarProduto(Produto produto) {
        if (!produtos.containsKey(produto.getNome())) {
            throw new IllegalArgumentException("Produto não encontrado no estoque");
        }
        produtos.put(produto.getNome(), produto); // Atualiza o produto existente
    }

    public void removerProduto(String nome) {
        if (!produtos.containsKey(nome)) {
            throw new IllegalArgumentException("Produto não encontrado no estoque");
        }
        produtos.remove(nome);
    }
}
