
<div id="cabecera">
	<div id="logotipo">
		<a href="${pageContext.request.contextPath}/menu.jsp"> <img
			src="${pageContext.request.contextPath}/imagenes/blocnotasfast-p.png"
			alt="Bloc de Notas FAST" />
		</a>
	</div>

	<%-- 		<a href="${pageContext.request.contextPath}/index.jsp?salir"><span class="acceso">Cerrar sesi�n</span></a>
 --%>
	<div id='seccion' >
		<span class='acceso' id='nombreusuario'>${usuario.nombre}</span>
		<div id='subseccion'>
			<ul>
				<li id='perfilId'><a href="${pageContext.request.contextPath}/perfil.jsp">Perfil</a></li>
				<li id='notasId'><a href="${pageContext.request.contextPath}/listarnotas.jsp">Notas</a></li>
				<li id='salirId'><a href="${pageContext.request.contextPath}/index.jsp?salir">Cerrar
						sesion</a></li>
			</ul>

		</div>
	</div>

</div>
