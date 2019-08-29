<%@page import="fast.bloc.UsuariosDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, fast.bloc.Nota, fast.bloc.Usuario"%>
	
<jsp:useBean id="usuario" class="fast.bloc.Usuario" scope="session" />
<jsp:useBean id="notas" class="fast.bloc.NotasDAO" scope="application" />
<jsp:useBean id="usua" class="fast.bloc.UsuariosDAO" scope="application" />

<%
//List<Nota> lista = notas.obtenerTitulos(usuario.getNombre());
//List<Usuario> listaUsuarios1 = usua.obtenerUsuarios();
List<Usuario> listaUsuarios = notas.obtenerUsuarios();


%>


<!DOCTYPE html>
<html>
  <head>
    <title>Bloc de Notas - FAST: Mostrar usuarios</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="../css/estilo.css" />
    <script src="js/listarusuarios.js"></script>
  </head>
  <body>	
	<jsp:include page="../usuarios/cabecera.jsp" />

	<div id="lista">
		<h1>Lista de TODOS los Usuarios</h1>
		<div id="lista-div">
			<table id="lista-tabla">
			<%
			int i = 0;
			
			
			for (Usuario usu: listaUsuarios) {
				//Generamos tabla
			%>
				<tr id='fila-<%=i%>'>
					<td class="infonota">
						<p id='nombreUsuario-<%=i%>'><strong><%=usu.getNombre()%></strong></p>
						<div class='detalle' id='detalle-<%=i%>'></div>
					
					</td>
				
				
				
				
				</tr>
				
			<%
			i++;
			}
			%>
			</table>
		</div>
	</div>
	<div id="botones-inferiores">
			<button class='boton' id="desMarcarTodas"     >Des/Marcar Todas</button>
			<button class='boton' id="borrarSeleccionadas">Borrar Seleccionadas</button>
	</div>
	<%@include file="../pie.jsp"%>
  </body>
</html>