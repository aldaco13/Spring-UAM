package com.crud.administradorpedidos.security.configuracion;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

public class JwtConfiguracionToken {

	public static final SecretKey LLAVE_SECRETA = Jwts.SIG.HS256.key().build();
	public static final String PREFIJO_TOKEN = "Bearer ";
	public static final String HADER_AUTORIZACION = "Authorization";
	public static final String CONTENT_TYPE = "application/json";
}
