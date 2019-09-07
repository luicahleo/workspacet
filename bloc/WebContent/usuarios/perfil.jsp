<%@page import="fast.bloc.UsuariosDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, fast.bloc.Nota, fast.bloc.DAOException"%>

<jsp:useBean id="usuario" class="fast.bloc.Usuario" scope="session" />
<jsp:useBean id="usuarios" class="fast.bloc.UsuariosDAO"
	scope="application" />
<jsp:setProperty property="*" name="usuario" />


<%
	String peticioncadena = request.getQueryString();
	//String parametroclave = request.getParameter("clave");
	String parametroiguales = request.getParameter("iguales");
	String mensajeError = "";

	System.out.println(peticioncadena);
	System.out.println(parametroiguales);

	//if (parametroiguales != null) {
	if (request.getParameterMap().containsKey("claveActual")) {
		//if (parametroiguales.equals("true")) {
		System.out.println("hacemos consulta a BD");
		String nombre = usuario.getNombre();
		String parametroclave = request.getParameter("claveActual");
		System.out.println(parametroclave);

		boolean existeusuario = usuarios.existeUsuario(nombre, parametroclave);
		System.out.println(existeusuario);

		//si existeusuario = true, hacemos el cambio en la BD, sino actualizamos el mensajeError
		if(existeusuario){
			
			String parametronuevaclave = request.getParameter("nuevaclave");
			boolean actualizado = usuarios.actualiza(nombre, parametronuevaclave);
			
		
		}else{
			mensajeError="Usuario/Clave actual incorrectos";

		}
		//Muestra error o exito
		if (!mensajeError.isEmpty()) {
			%>
				<div id="error">
					<p>
						ERROR:
						<%=mensajeError%>
					</p>
				</div>
				<%
				} else {
				%>
					<div id="exito">
					<p>INFO: PERFIL ACTUALIZADO CORRECTAMENTE</p>
					</div>
					<%
					} 
		}
%>
<!DOCTYPE html>
<html>
<head>
<title>Bloc de Notas - FAST: Pefil</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="../css/estilo.css" />
<!-- 	<script src="js/crearnotas.js"></script>
 -->
<script type="text/javascript" src="js/cabecera.js"></script>

<script type="text/javascript" src="js/perfil.js"></script>

</head>
<body>
	<jsp:include page="cabecera.jsp" />

	<div id="crear">
		<h1>Editar Perfil del Usuario</h1>






		<div id="formcrear">

			<form id="notaform" method="post" action="">
				<div class="titulo-div">
					<label for="titulo"><strong>Nombre del usuario</strong></label> <input
						id="nombreUsuario" type="text" value="${usuario.nombre}"
						name="titulo" maxlength="100" required="required"
						readonly="readonly" />
				</div>

				<div class="titulo-div">
					<label for="claveActual"><strong>Clave actual</strong></label> <input
						id="claveActual" type="text" name="claveActual" maxlength="100" />
				</div>
				<div class="titulo-div">
					<label for="nuevaclave"><strong>Nueva Clave<span
							class="asterisco">*</span></strong></label> <input id="nuevaClave" type="text"
						name="nuevaclave" maxlength="100" />
				</div>
				<div class="titulo-div">
					<label for="titulo"><strong>Nueva Clave (Repetir)<span
							class="asterisco">*</span></strong></label> <input id="nuevaRepetirClave"
						type="text" name="titulo" maxlength="100" />
				</div>

				<input class="boton" id="enviarnota" type="submit" value="Guardar"
					name="enviarnota" /> <input class="boton" id="limpiar"
					type="reset" value="limpiar" name="limpiar" />
			</form>





		</div>


	</div>







	<%@include file="../pie.jsp"%>
</body>
</html>