package com.example.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.example.rest.entidades.Pedido;

import com.example.rest.util.MySqlDBConexion;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class PedidoModel {

	public int inserta(Pedido pedido) {
		log.info("---> En PedidoModel -> inserta");

		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm1 = null, pstm2 = null, pstm3 = null;
		try {
			conn = MySqlDBConexion.getConexion();

			conn.setAutoCommit(false);
			log.info(pedido.getUbigeo().getIdUbigeo() + "");

			String sql1 = "insert into pedido values(null,curtime(),?,?,'PENDIENTE',?,?,?)";
			pstm1 = conn.prepareStatement(sql1);
			pstm1.setDate(1, pedido.getFechaEntrega());
			pstm1.setString(2, pedido.getLugarEntrega());
			pstm1.setInt(3, pedido.getCliente().getIdCliente());
			pstm1.setInt(4, pedido.getUbigeo().getIdUbigeo());
			pstm1.setInt(5, pedido.getUsuario().getIdUsuario());
			pstm1.executeUpdate();
			log.info(pstm1);

			String sql2 = "select max(idPedido) from pedido";
			pstm2 = conn.prepareStatement(sql2);
			log.info(pstm2);
			ResultSet rs = pstm2.executeQuery();
			rs.next();
			int idPedido = rs.getInt(1);

			String sql3 = "insert into pedido_has_producto values(?,?,?,?)";
			pstm3 = conn.prepareStatement(sql3);
			for (PedidoDetalle aux : pedido.getDetalles()) {
				pstm3.setInt(1, idPedido);
				pstm3.setInt(2, aux.getIdProducto());
				pstm3.setDouble(3, aux.getPrecio());
				pstm3.setInt(4, aux.getCantidad());
				pstm3.executeUpdate();
				log.info(pstm3);
			}

			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstm1.close();
				pstm2.close();
				pstm3.close();

			} catch (SQLException e) {
			}
		}
		return contador;
	}

	
}
