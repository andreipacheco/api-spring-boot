package br.com.projeto.api.servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.Cliente;
import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.modelo.Produto;
import br.com.projeto.api.repositorio.RepositorioPessoa;
import br.com.projeto.api.repositorio.RepositorioCliente;
import br.com.projeto.api.repositorio.RepositorioProduto;

@Service
public class Servico {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private RepositorioPessoa repo_pessoa;

    @Autowired
    private RepositorioCliente repo_cliente;

    @Autowired
    private RepositorioProduto repo_produto;
    
    // Método para cadastrar pessoas
    public ResponseEntity<?> cadastrar(Pessoa obj){

        if(obj.getNome().equals("")){
            mensagem.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getIdade() < 0){
            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getSexo().equals("")){
            mensagem.setMensagem("Informe sexo válido (Masculino/Feminino/Outros)");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(repo_pessoa.save(obj), HttpStatus.CREATED);
        }
    }

    // Método para cadastrar Clientes
    public ResponseEntity<?> cadastrarCliente(Cliente obj){
        return new ResponseEntity<>(repo_cliente.save(obj), HttpStatus.CREATED);
    }

    // Método para cadastrar Produtos
    public ResponseEntity<?> cadastrarProduto(Produto obj){
        return new ResponseEntity<>(repo_produto.save(obj), HttpStatus.CREATED);
    }

    // Método para selecionar pessoas
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(repo_pessoa.findAll(), HttpStatus.OK);
    }

    // Método para selecionar pessoas através do código
    public ResponseEntity<?> selecionarPeloCodigo(int codigo){

        if(repo_pessoa.countByCodigo(codigo) == 0){
            mensagem.setMensagem("Não foi encontrada nenhuma pessoa");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(repo_pessoa.findByCodigo(codigo), HttpStatus.OK);
        }

    }

    // Método para editar dados
    public ResponseEntity<?> editar(Pessoa obj){

        if(repo_pessoa.countByCodigo(obj.getCodigo()) == 0){
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else if(obj.getNome().equals("")){
            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getIdade() < 0){
            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(repo_pessoa.save(obj), HttpStatus.OK);
        }

    }

    // Método para remover registros
    public ResponseEntity<?> remover(int codigo){

        if(repo_pessoa.countByCodigo(codigo) == 0){
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else{

            Pessoa obj = repo_pessoa.findByCodigo(codigo);
            repo_pessoa.delete(obj);

            mensagem.setMensagem("Pessoa removida com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);

        }

    }

}