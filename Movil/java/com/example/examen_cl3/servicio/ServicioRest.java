package com.example.examen_cl3.servicio;

import com.example.examen_cl3.entidad.RegistroPedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServicioRest {

   @POST("registrapedido")
    public  abstract Call<RegistroPedido> Registra(@Body RegistroPedido registra);
}
