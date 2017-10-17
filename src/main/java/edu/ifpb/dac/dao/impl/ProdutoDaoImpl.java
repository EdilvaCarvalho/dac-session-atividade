
package edu.ifpb.dac.dao.impl;

import edu.ifpb.dac.dao.interfaces.ProdutoDAO;
import edu.ifpb.dac.entidades.Produto;
import java.util.Collections;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Edilva
 */
@Stateless
@Local(ProdutoDAO.class)
public class ProdutoDaoImpl implements ProdutoDAO{
    
    @PersistenceContext(unitName = "atividade-session-beans-PU")
    private EntityManager em;

    @Override
    public void cadastrar(Produto produto) {
        em.persist(produto);
    }

    @Override
    public void remover(Produto produto) {
        Produto p = em.find(Produto.class, produto.getId());
        em.remove(p);
    }

    @Override
    public Produto getProduto(int id) {
        return em.find(Produto.class, id);
    }

    @Override
    public List<Produto> listar() {
        try {
            Query query = em
                    .createQuery("SELECT p FROM Produto p");

            List<Produto> list = query.getResultList();

            if (list == null || list.isEmpty()) {
                return Collections.EMPTY_LIST;
            } else {
                return list;
            }

        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }
    
}
