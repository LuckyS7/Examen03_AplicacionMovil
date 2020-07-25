package com.example.rest.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.rest.entidades.Pedido;


import lombok.extern.apachecommons.CommonsLog;

@Path("/servicios")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@CommonsLog
public class ServicioRest {

	
	private PedidoModel daoPedido = new PedidoModel();
	
	@POST
	@Path("/pedido")
	public Response registroPedido(Pedido obj) {
		log.info("rest -> registroPedido ");
		return Response.ok(daoPedido.inserta(obj)).build();
	}


}