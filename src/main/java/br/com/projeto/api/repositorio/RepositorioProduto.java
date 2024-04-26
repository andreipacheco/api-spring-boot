package br.com.projeto.api.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.modelo.Produto;


@Repository
public interface RepositorioProduto extends CrudRepository<Produto, Integer> {

    Produto findByCodigo(int codigo);

    int countByCodigo(int codigo);
}
