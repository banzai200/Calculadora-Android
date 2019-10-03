package com.example.trabalhochato;

import static java.lang.Math.sqrt;

public class Calculus {
    private Double pot;
    private Double ten;
    private Double cor;
    private Double res;

    public Double getPotencia ()
    {
        if (this.pot != null){return pot;}
        if (this.ten != null && this.cor != null){return cor*ten;}
        if (this.res != null && this.cor != null){return res*(cor*cor);}
        if (this.ten != null && this.res != null){return (ten*ten)/res;}
        return null;
    }
    public void setPotencia (Double potencia)
    {
        this.pot = potencia;
    }
    public Double getTensao ()
    {
        if (this.ten != null){return ten;}
        if (this.res != null && this.cor != null){return res*cor;}
        if (this.pot != null && this.cor != null){return pot/cor;}
        if (this.pot != null && this.res != null){return sqrt(pot*res);}
        return null;
    }
    public void setTensao (Double tensao)
    {
        this.ten = tensao;
    }
    public Double getResistencia ()
    {
        if (this.res != null){return res;}
        if (this.ten != null && this.cor != null){return ten/cor;}
        if (this.ten != null && this.pot != null){return (ten*ten)/pot;}
        if (this.pot != null && this.cor != null){return pot/(cor*cor);}
        return null;
    }
    public void setResistencia (Double resistencia)
    {
        this.res = resistencia;
    }
    public Double getCorrente ()
    {
        if (this.cor != null){return cor;}
        if (this.ten != null && this.res != null){return ten/res;}
        if (this.ten != null && this.pot != null){return pot/ten;}
        if (this.pot != null && this.res != null){return sqrt(pot/res);}
        return null;
    }
    public void setCorrente (Double corrente)
    {
        this.cor = corrente;
    }
    public void reset()
    {
        ten = null;
        pot = null;
        cor = null;
        res = null;
    }
}
