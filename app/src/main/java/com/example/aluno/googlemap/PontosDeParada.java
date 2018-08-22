package com.example.aluno.googlemap;

public class PontosDeParada
{
    private String hora;
    private String parada;

    public PontosDeParada(){  }

    public PontosDeParada(String hora, String parada)
    {
      this.hora = hora;
      this.parada = parada;
    }

    public String getHora()
    {
      return hora;
    }

    public void setHora(String hora)
    {
      this.hora = hora;
    }

    public String getParada()
    {
      return parada;
    }

    public void setParada(String parada)
    {
      this.parada = parada;
    }
}