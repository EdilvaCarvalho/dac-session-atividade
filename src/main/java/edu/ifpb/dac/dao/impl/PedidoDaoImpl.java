package edu.ifpb.dac.dao.impl;

import edu.ifpb.dac.dao.interfaces.PedidoDAO;
import edu.ifpb.dac.entidades.Pedido;
import edu.ifpb.dac.entidades.Produto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edilva
 */
@Stateful
@Local(PedidoDAO.class)
public class PedidoDaoImpl implements PedidoDAO {

    @PersistenceContext(unitName = "atividade-session-beans-PU")
    private EntityManager em;

    @Override
    public void addProduto(Pedido pedido, Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("");
        }
        pedido.add(produto);
    }

    @Override
    public void removeProduto(Pedido pedido, Produto produto) {
        pedido.remove(produto);
    }

    @Override
    public List<Produto> listar(Pedido pedido) {
        return Collections.unmodifiableList(pedido.getProdutos());
    }

    @Override
    public void finalizarPedido(Pedido pedido) {
        pedido.setProdutos(new ArrayList<>());
    }

    @Override
    public void cadastrarPedido(Pedido pedido) {
        em.persist(pedido);
    }

}
