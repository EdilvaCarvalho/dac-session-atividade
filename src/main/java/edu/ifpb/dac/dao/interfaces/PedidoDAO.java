package edu.ifpb.dac.dao.interfaces;

import edu.ifpb.dac.entidades.Pedido;
import edu.ifpb.dac.entidades.Produto;
import java.util.List;

/**
 *
 * @author Edilva
 */
public interface PedidoDAO {

    void addProduto(Pedido pedido, Produto produto);

    void removeProduto(Pedido pedido, Produto produto);

    List<Produto> listar(Pedido pedido);

    void finalizarPedido(Pedido pedido);
    
    void cadastrarPedido(Pedido pedido);

}
