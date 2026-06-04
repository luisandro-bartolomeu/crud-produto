package co.ao.isaf.crud_produto.api.service;

import co.ao.isaf.crud_produto.api.repository.EncomendaRepository;
import co.ao.isaf.crud_produto.api.repository.ProdutoRepository;
import co.ao.isaf.crud_produto.api.repository.UserRepository;
import co.ao.isaf.crud_produto.domain.dto.EncomendaItemRequest;
import co.ao.isaf.crud_produto.domain.dto.EncomendaRequest;
import co.ao.isaf.crud_produto.domain.enums.Estado;
import co.ao.isaf.crud_produto.domain.model.Encomenda;
import co.ao.isaf.crud_produto.domain.model.EncomendaItem;
import co.ao.isaf.crud_produto.error.EncomendaNaoEncontradaException;
import co.ao.isaf.crud_produto.domain.model.Produto;
import co.ao.isaf.crud_produto.domain.model.Utilizador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EncomendaService {

    private final EncomendaRepository encomendaRepository;
    private final UserRepository userRepository;
    private final ProdutoRepository produtoRepository;

    public EncomendaService(EncomendaRepository encomendaRepository,
                            UserRepository userRepository,
                            ProdutoRepository produtoRepository) {
        this.encomendaRepository = encomendaRepository;
        this.userRepository = userRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Encomenda> listarEncomendas() {
        return encomendaRepository.findAll();
    }

    public Encomenda buscarPorId(Long id) {
        return encomendaRepository.findById(id)
                .orElseThrow(() -> new EncomendaNaoEncontradaException(id));
    }

    @Transactional
    public Encomenda criarEncomenda(EncomendaRequest request) {
        Utilizador usuario = buscarUsuario(request.usuarioId());
        Encomenda encomenda = new Encomenda();
        encomenda.setUsuario(usuario);
        encomenda.setEstado(request.estado() != null ? request.estado() : Estado.PENDENTE);

        List<EncomendaItem> itens = montarItens(request.itens(), encomenda);
        encomenda.setItens(itens);
        encomenda.setTotal(calcularTotal(itens));

        return encomendaRepository.save(encomenda);
    }

    @Transactional
    public Encomenda actualizarEncomenda(Long id, EncomendaRequest request) {
        Encomenda encomenda = buscarPorId(id);
        Utilizador usuario = buscarUsuario(request.usuarioId());

        encomenda.setUsuario(usuario);
        if (request.estado() != null) {
            encomenda.setEstado(request.estado());
        }

        encomenda.getItens().clear();
        List<EncomendaItem> itens = montarItens(request.itens(), encomenda);
        encomenda.getItens().addAll(itens);
        encomenda.setTotal(calcularTotal(itens));

        return encomendaRepository.save(encomenda);
    }

    public void removerEncomenda(Long id) {
        Encomenda encomenda = buscarPorId(id);
        encomendaRepository.delete(encomenda);
    }

    private Utilizador buscarUsuario(Long usuarioId) {
        return userRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + usuarioId));
    }

    private List<EncomendaItem> montarItens(List<EncomendaItemRequest> requests, Encomenda encomenda) {
        List<EncomendaItem> itens = new ArrayList<>();
        for (EncomendaItemRequest itemRequest : requests) {
            Produto produto = produtoRepository.findById(itemRequest.produtoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado para o ID: " + itemRequest.produtoId()));

            EncomendaItem item = new EncomendaItem();
            item.setEncomenda(encomenda);
            item.setProduto(produto);
            item.setQuantidade(itemRequest.quantidade());
            item.setPrecoUnitario(itemRequest.precoUnitario() != null ? itemRequest.precoUnitario() : produto.getPreco());
            itens.add(item);
        }
        return itens;
    }

    private Double calcularTotal(List<EncomendaItem> itens) {
        return itens.stream()
                .mapToDouble(item -> item.getPrecoUnitario() * item.getQuantidade())
                .sum();
    }
}
