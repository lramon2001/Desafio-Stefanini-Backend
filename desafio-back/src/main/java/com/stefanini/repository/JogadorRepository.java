package com.stefanini.repository;

import com.stefanini.dao.GenericDAO;
import com.stefanini.entity.Jogador;
import javax.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class JogadorRepository extends GenericDAO<Jogador, Long> {

    public Jogador encontraPorNickname(String nickname) {
        return (Jogador) getEntityManager()
                .createQuery("select j from Jogador j where j.nickname = :nickname")
                .setParameter("nickname", nickname)
                .getSingleResult();
    }
}
