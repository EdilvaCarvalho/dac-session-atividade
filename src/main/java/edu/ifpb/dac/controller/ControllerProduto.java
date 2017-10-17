package edu.ifpb.dac.controller;

import edu.ifpb.dac.dao.interfaces.ProdutoDAO;
import edu.ifpb.dac.entidades.Produto;
import edu.ifpb.dac.util.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Edilva
 */
@Named
@SessionScoped
public class ControllerProduto implements Serializable {

    @Inject
    private ProdutoDAO produtoDAO;
    private Produto produto = new Produto();

    public ControllerProduto() {
    }

    public String salvarProduto() {
        this.produtoDAO.cadastrar(produto);
        FacesUtil.addMsgInfo("Produto cadastrado com sucesso!");
        this.produto = new Produto();
        return null;
    }

    public String removerProduto(Produto produtoRemover) {
        try {
            this.produtoDAO.remover(produtoRemover);
            FacesUtil.addMsgInfo("Produto removido com sucesso!");
        } catch (Exception e) {
            FacesUtil.addMsgErro("Esse produto não pode ser removido!");
        }

        return null;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getTodosOsProdutos() {
        return this.produtoDAO.listar();
    }
}
