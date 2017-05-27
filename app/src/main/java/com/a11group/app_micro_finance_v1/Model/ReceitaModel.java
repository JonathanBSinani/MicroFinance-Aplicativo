package com.a11group.app_micro_finance_v1.Model;

/**
 * Created by Jonathan on 25/05/2017.
 */

public class ReceitaModel {

    private Integer codigo;
    private String  descricaoReceita;
    private String  tipoReceita;
    private String  dataReceita;
    private double  valorReceita;

    public Integer getCodigo(){
        return codigo;
    }

    public void setCodigo(Integer codigo){
        this.codigo = codigo;
    }

    public String getDescricaoReceita(){
        return descricaoReceita;
    }

    public void setDescricaoReceita(String descricaoReceita){
        this.descricaoReceita = descricaoReceita;
    }

    public String getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(String tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public String getDataReceita() {
        return dataReceita;
    }

    public void setDataReceita(String dataReceita) {
        this.dataReceita = dataReceita;
    }

    public double getValorReceita() {
        return valorReceita;
    }

    public void setValorReceita(double valorReceita) {
        this.valorReceita = valorReceita;
    }

}
