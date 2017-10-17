package edu.ifpb.dac.controller;

import edu.ifpb.dac.dao.interfaces.PedidoDAO;
import edu.ifpb.dac.dao.interfaces.ProdutoDAO;
import edu.ifpb.dac.entidades.Pedido;
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
public class ControllerPedido implements Serializable {

    @Inject
    private PedidoDAO pedidoDAO;
    @Inject
    private ProdutoDAO produtoDAO;
    private Pedido pedido = new Pedido();
    private Produto produtoSorteado = new Produto();

    public ControllerPedido() {
        //sorteio();
    }

    public String addProduto(Produto produtoAdd) {
        this.pedidoDAO.addProduto(pedido, produtoAdd);
        FacesUtil.addMsgInfo("Produto adicionado com sucesso!");
        sorteio();
        return null;
    }

    public String removerProduto(Produto produtoRemover) {
        this.pedidoDAO.removeProduto(pedido, produtoRemover);
        FacesUtil.addMsgInfo("Produto removido com sucesso!");
        return null;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProdutoSorteado() {
        return produtoSorteado;
    }

    public void setProdutoSorteado(Produto produtoSorteado) {
        this.produtoSorteado = produtoSorteado;
    }

    public List<Produto> getTodosOsProdutos() {
        return this.pedidoDAO.listar(pedido);
    }

    public void cancelarPedido() {
        this.pedidoDAO.finalizarPedido(pedido);
        FacesUtil.addMsgInfo("Pedido cancelado com sucesso!");
    }

    public void fecharPedido() {
        if (!pedido.getProdutos().isEmpty()) {
            this.pedidoDAO.cadastrarPedido(pedido);
            FacesUtil.addMsgInfo("Pedido fechado com sucesso!");
        } else {
            FacesUtil.addMsgErro("Não existe nenhum item no carrinho!");
        }
        this.pedidoDAO.finalizarPedido(pedido);
    }

    private void sorteio() {
        Random r = new Random();
        produtoSorteado = produtoDAO.listar().get(r.nextInt(produtoDAO.listar().size() - 1));
    }

}
