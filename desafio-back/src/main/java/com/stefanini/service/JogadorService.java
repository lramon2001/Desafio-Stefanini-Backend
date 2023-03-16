package com.stefanini.service;

import com.stefanini.entity.Jogador;
import com.stefanini.entity.Stefamon;
import com.stefanini.exceptions.RegraDeNegocioException;
import com.stefanini.login.Login;
import com.stefanini.repository.JogadorRepository;
import com.stefanini.repository.StefamonRepository;

import java.util.Objects;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.Valid;

@ApplicationScoped
public class JogadorService {

    @Inject
    JogadorRepository jogadorRepository;
    @Inject
    StefamonRepository repository;

    Logger logger = LoggerFactory.getLogger(JogadorService.class);

    public void salvar(Jogador jogador) {
        jogadorRepository.save(jogador);
    }

    public Jogador pegarPorId(Long id) {
        var jogador = jogadorRepository.findById(id);
        if (Objects.isNull(jogador)) {
            throw new RegraDeNegocioException("Ocorreu um erro ao buscar o Jogador de id " + id,
                    Response.Status.NOT_FOUND);
        }
        return jogador;
    }

    public void alterar(Jogador jogadorNovo, Long id) {
        Jogador jogador = jogadorRepository.findById(id);
        jogador.setNickname(jogadorNovo.getNickname());
        jogador.setPassword(jogadorNovo.getPassword());
        jogador.setSaldo(jogadorNovo.getSaldo());
        jogador.setStefamons(jogadorNovo.getStefamons());
        jogadorRepository.update(jogador);
    }

    public void deletar(Long id) {
        jogadorRepository.delete(id);
    }

    public List<Jogador> listarTodos() {

        return jogadorRepository.listAll();
    }

    public boolean authenticate(Login login) throws Exception {

        Jogador jogador = jogadorRepository.encontraPorNickname(login.getLogin());

        if (Objects.isNull(jogador)) {
            logger.info(" O Usuario de login: " + login.getLogin() + " não foi encontrado");
            throw new RuntimeException();
        }

        return jogador.getPassword().equals(login.getSenha());
    }

    public void escolherStefamon(Long idJogador, Long idStefamon) {
        Jogador jogador = jogadorRepository.findById(idJogador);
        Stefamon stefamon = repository.findById(idStefamon);
        if (jogador.getSaldo().intValue() >= stefamon.getPreco() && jogador.getStefamons().size() < 6) {
            jogador.setSaldo(jogador.getSaldo().subtract(new BigDecimal(stefamon.getPreco())));
            jogador.getStefamons().add(stefamon);
            jogadorRepository.update(jogador);
        }
    }

    public Long batalhar(Jogador jogador1, Jogador jogador2) {
        long somaJogador1 =jogador1.getStefamons().stream().mapToInt(Stefamon::getPreco).sum();
        long somaJogador2= jogador2.getStefamons().stream().mapToInt(Stefamon::getPreco).sum();

        return somaJogador1 > somaJogador2 ? jogador1.getId() : jogador2.getId();
    }

    public long batalharBot(@Valid Jogador jogador1) {
        Random rand = new Random();
        int resultado = rand.nextInt(10) + 1;
        if(resultado <= 9) {
            return jogador1.getId();
        } else {
            return 0;
        }
    }
}
