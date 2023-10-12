package com.grumvalski.yogaLifebackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DateTimeException;
import java.util.Date;

@Document("Event")
public class Event {

    @Id
    private String id;

    private String nome;
    private String durata;
    private int prezzo;
    private String inizioFine;
    private String giorni;
    private String orario;

    private int posti;

    public Event() {
    }

    public Event(String id, String nome, String durata, int prezzo, String inizioFine, String giorni, String orario, int posti) {
        this.id = id;
        this.nome = nome;
        this.durata = durata;
        this.prezzo = prezzo;
        this.inizioFine = inizioFine;
        this.giorni = giorni;
        this.orario = orario;
        this.posti=posti;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public String getInizioFine() {
        return inizioFine;
    }

    public void setInizioFine(String inizioFine) {
        this.inizioFine = inizioFine;
    }

    public String getGiorni() {
        return giorni;
    }

    public void setGiorni(String giorni) {
        this.giorni = giorni;
    }

    public String getOrario() {
        return orario;
    }

    public void setOrario(String orario) {
        this.orario = orario;
    }

    public int getPosti() {
        return posti;
    }

    public void setPosti(int posti) {
        this.posti = posti;
    }
}
