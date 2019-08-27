<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="usuario" class="fast.bloc.Usuario" scope="session" />

<!DOCTYPE html>
<html>
<head>
<title>Bloc de Notas - FAST: Menú</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/estilo.css" />
</head>
<jsp:include page="../usuarios/cabecera.jsp" />
<div id="menu">
	<h1>Menú de Administradores</h1>
	<ol>
		<li><a href="usuarios/crearnota.jsp"><span class="fondo">
					<span class="textoopcion">Nueva</span>
			</span></a></li>
		<li><a href="usuarios/listarnotas.jsp"><span class="fondo">
					<span class="textoopcion">Mostrar <br />propias
				</span>
			</span></a></li>
		<li><a href="admins/listarnotasTodas.jsp"><span class="fondo">
					<span class="textoopcion">Mostrar <br />todas
				</span>
			</span></a></li>
		<li><a href="admins/listarusuarios.jsp"><span class="fondo">
					<span class="textoopcion">Mostrar <br />usuarios
				</span>
			</span></a></li>
	</ol>
</div>


<%@include file="../pie.jsp"%>
</body>
</html>