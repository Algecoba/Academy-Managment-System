package com.acb.ams.Models;


import java.time.LocalDate;
import java.util.List;

//Clase refetente a Corte/Periodo
public class GradingPeriod {
    private int corId;
    private LocalDate corFinicial;
    private LocalDate corFfinal;
    private int corNumCorte;

    // Relación con Notas
    private List<Qualification> notas;

    public GradingPeriod(int corId, LocalDate corFinicial, LocalDate corFfinal, int corNumCorte, List<Qualification> notas) {
        this.corId = corId;
        this.corFinicial = corFinicial;
        this.corFfinal = corFfinal;
        this.corNumCorte = corNumCorte;
        this.notas = notas;
    }

    public int getCorId() {
        return corId;
    }

    public void setCorId(int corId) {
        this.corId = corId;
    }

    public LocalDate getCorFinicial() {
        return corFinicial;
    }

    public void setCorFinicial(LocalDate corFinicial) {
        this.corFinicial = corFinicial;
    }

    public LocalDate getCorFfinal() {
        return corFfinal;
    }

    public void setCorFfinal(LocalDate corFfinal) {
        this.corFfinal = corFfinal;
    }

    public int getCorNumCorte() {
        return corNumCorte;
    }

    public void setCorNumCorte(int corNumCorte) {
        this.corNumCorte = corNumCorte;
    }

    public List<Qualification> getNotas() {
        return notas;
    }

    public void setNotas(List<Qualification> notas) {
        this.notas = notas;
    }


    
}
