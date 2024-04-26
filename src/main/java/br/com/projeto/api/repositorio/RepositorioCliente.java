package br.com.projeto.api.repositorio;

// import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.modelo.Cliente;


@Repository
public interface RepositorioCliente extends CrudRepository<Cliente, Integer> {

    // List<Cliente> findAll();

    Cliente findByCodigo(int codigo);

    int countByCodigo(int codigo);

}
