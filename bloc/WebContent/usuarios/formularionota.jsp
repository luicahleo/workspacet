<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="java.util.List"%>
<jsp:useBean id="notas" class="fast.bloc.NotasDAO" scope="application" />
<jsp:useBean id="usuario" class="fast.bloc.Usuario" scope="session" />
<jsp:useBean id="nota" class="fast.bloc.Nota" scope="request" />
<%
	List<String> categorias = notas.obtenerCategorias(usuario.getNombre());
	List<String> colores = notas.obtenerColores(usuario.getNombre());
%>

<form id="notaform" method="post" action="">
	<div class="titulo-div">
		<label for="titulo"><strong>T&iacute;tulo de la nota</strong></label>
		<input id="titulo" type="text" value="${nota.titulo}" name="titulo" maxlength="100"
			required="required" />
	</div>
	<div class="detalle-div">
		<label for="categoria"><strong>Categoría</strong></label>
		<select id="categoria" name="categoria">
			<%
				for (String notacategoria : categorias) {
					if (null != notacategoria) {
			%>
			<option value="<%=notacategoria%>" <%= notacategoria.equals(nota.getCategoria()) ? "selected" : ""%> >
			 	<%=notacategoria%>
			 </option>
			<%
					}
				}
			%>
		</select> <input type="button" id="otracategoria" value="+" />
	</div>
	<div class="detalle-div">
		<label for="color"><strong>Color</strong></label>
		<select id="color" name="color">
			<%
				for (String notacolor : colores) {
					if (null != notacolor) {
			%>
			<option value="<%=notacolor%>" <%= notacolor.equals(nota.getColor()) ? "selected" : ""%> >
				<%=notacolor%>
			</option>
			<%
					}
				}
			%>
		</select> <span>+</span> <input type="color" id="otrocolor" />
	</div>
	<div class="imagen-div">
		<label for="urlimagen"><strong>URL de la imagen</strong></label>
		<input id="urlimagen" type="text" value="${nota.urlimagen}" name="urlimagen" />
	</div>
	<div class="nota-div">
		<label for="nota"><strong>Nota</strong></label>
		<textarea id="nota" name="nota" cols="100%" rows="100%" required="required">${nota.nota}</textarea>
	</div>
	<input class="boton" id="enviarnota" type="submit" value="Guardar" name="enviarnota" />
	<input class="boton" id="limpiar" type="reset" value="${param.mensajeReset}" name="limpiar" />
</form>
