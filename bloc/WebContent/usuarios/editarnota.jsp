<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="java.util.List, fast.bloc.Nota, fast.bloc.DAOException"%>

<jsp:useBean id="usuario" class="fast.bloc.Usuario" scope="session" />
<jsp:useBean id="notas" class="fast.bloc.NotasDAO" scope="application" />
<jsp:useBean id="nota" class="fast.bloc.Nota" />
<jsp:setProperty property="*" name="nota"/>

<!DOCTYPE html>
<html>
<head>
	<title>Bloc de Notas - FAST: Crear/Editar nota</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="../css/estilo.css" />
	<script src="js/crearnotas.js"></script>
</head>
<body>
	<jsp:include page="cabecera.jsp" />
	
	<%
	String idStr = request.getParameter("eid");
	String mensajeError = "";
	
	// Comprobamos si esta solicitud proviene de:
	// 1) Pulsar en "Guardar" en el fornulario (solicitud contiene el parametro
	//   HTTP (POST) "titulo" del formulario.
	if ( request.getParameterMap().containsKey("titulo") )
	{
		// Guardar los nuevos valores de la nota (se ha pulsado en "Guardar")
		try {
			nota.setNombreUsuario(usuario.getNombre());
			if ( !notas.actualizar(Integer.parseInt(idStr), usuario.getNombre(), nota) ) {
				mensajeError = "No se ha podido insertar la nota";
			}
	
		} catch (DAOException e) {
			mensajeError = e.getMessage();
		} catch (NumberFormatException e) {
			mensajeError = "Parámetro incorrecto";
		}
		
	    //Muestra error o exito
		if ( ! mensajeError.isEmpty() ) {
		%>
			<div id="error"><p> ERROR: 
			<%= mensajeError %>
			</p></div>
		<%
		} else { %>
			<div id="exito"><p> INFO: NOTA ACTUALIZADA</p></div>
	<%
		}
	}
	
	// 2) Botón "Editar" de lista de notas: sin el parametro "titulo"
	// Comprobar si se ha recibido (parametro HTTP GET) el "id" de la nota a editar
	// y en dicho caso leer los valores de la nota de la BD
	else if ( idStr != null )
	{
		// Leer los datos de la nota indicada
		try {
			nota = notas.obtener(Integer.parseInt(idStr), usuario.getNombre());
			if ( nota == null )
			{
				mensajeError = "La nota no existe.";
			}

		} catch (DAOException e) {
			mensajeError = e.getMessage();
		} catch (NumberFormatException e) {
			mensajeError = "Parámetro incorrecto";
		}
	}
	%>
	
	<div id="crear">
		<h1>Editar nota</h1>
		<div id="formcrear">
	<%
		if ( idStr == null || nota == null || !mensajeError.isEmpty())
		{
			out.println("<script>alert('No se ha indicado ninguna nota válida');</script>");
		}
		else
		{
			 // se crea atributo del ámbito request, para rellenar el formulario
			request.setAttribute("nota", nota);
	%>
		<jsp:include page="formularionota.jsp" >
			<jsp:param name="mensajeReset" value="Recargar datos guardados" />
		</jsp:include>
	<% 	} %>

		
		</div>
	</div>
	<div id="botones-inferiores">
		<a href="listarnotas.jsp"  class="botonenl"><span class="acceso">Volver a la lista</span></a>
	</div>

	<%@include file="../pie.jsp"%>
  </body>
</html>