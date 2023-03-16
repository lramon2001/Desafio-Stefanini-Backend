package com.stefanini.mappers;

import java.util.ArrayList;
import java.util.List;

import com.stefanini.dto.JogadorDTO;
import com.stefanini.entity.Jogador;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JogadorMapperImpl implements JogadorMapperInterface{

    @Override
    public JogadorDTO paraJogadorDTO(Jogador jogador) {
        JogadorDTO jogadorDTO = new JogadorDTO();
        jogadorDTO.setId(jogador.getId());
        jogadorDTO.setNickname(jogador.getNickname());
        jogadorDTO.setSaldo(jogador.getSaldo());
        jogadorDTO.setStefamons(jogador.getStefamons());
        
        return jogadorDTO;
    }

    @Override
    public List<JogadorDTO> paraListaDeJogadoresDTO(List<Jogador> jogadoresLista) {
       List<JogadorDTO> jogadorDTOsLista = new ArrayList<JogadorDTO>();
       jogadoresLista.forEach(jogadorEntidade->{
        jogadorDTOsLista.add(paraJogadorDTO(jogadorEntidade));
       });

       return jogadorDTOsLista;

    }

    @Override
    public Jogador paraEntidade(JogadorDTO jogadorDTO) {
        Jogador jogador = new Jogador();
        jogador.setId(jogadorDTO.getId());
        jogador.setNickname(jogadorDTO.getNickname());
        jogador.setSaldo(jogadorDTO.getSaldo());
        jogador.setStefamons(jogadorDTO.getStefamons());
        return jogador;
    }
    
}
