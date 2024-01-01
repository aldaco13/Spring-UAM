package com.crud.administradorpedidos.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.crud.administradorpedidos.entidades.Pedido;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Component
public class ClienteRetornoDTO {
	private Long cliente;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correo;
	private String calle;
	private Long numero;
	private String colonia;
	private String delegacion;
	private String cp;
	private String ciudad;
	private List<Long> idPedidos;
	
	public void setIdPedidos(List<Pedido> pedidos) {
		
		idPedidos = new ArrayList<Long>();
		
		for(Pedido p : pedidos) {
			idPedidos.add(p.getIdentificador());
		}
	}
	
	
}
