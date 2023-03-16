package com.stefanini.mappers;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.stefanini.dto.JogadorDTO;
import com.stefanini.entity.Jogador;

@ApplicationScoped
public interface JogadorMapperInterface {
    JogadorDTO paraJogadorDTO(Jogador jogador);

    List<JogadorDTO> paraListaDeJogadoresDTO(List<Jogador> jogadoresLista);

    Jogador paraEntidade(JogadorDTO Jogador);
    
}
