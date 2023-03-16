package com.stefanini.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.stefanini.entity.Stefamon;

public class JogadorDTO {

    
    private Long id;

    
    private String nickname;

    
    private BigDecimal saldo;

    private List<Stefamon> stefamons = new ArrayList<>();

    

    public JogadorDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Stefamon> getStefamons() {
        return stefamons;
    }

    public void setStefamons(List<Stefamon> stefamons) {
        this.stefamons = stefamons;
    }

    
    
}
