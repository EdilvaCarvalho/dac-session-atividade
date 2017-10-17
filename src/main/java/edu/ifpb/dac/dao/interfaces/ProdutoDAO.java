package edu.ifpb.dac.dao.interfaces;

import edu.ifpb.dac.entidades.Produto;
import java.util.List;

/**
 *
 * @author Edilva
 */
public interface ProdutoDAO {

    void cadastrar(Produto produto);

    void remover(Produto produto);

    Produto getProduto(int id);

    List<Produto> listar();
}
