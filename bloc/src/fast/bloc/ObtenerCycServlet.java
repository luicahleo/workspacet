package fast.bloc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ObtenerEstadisticasServlet
 */
@WebServlet("/admins/cycs")
public class ObtenerCycServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObtenerCycServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGett(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		NotasDAO notas = (NotasDAO) getServletContext().getAttribute("notas");
		String nombre_usu = request.getParameter("nombre_usu");
		UsuariosDAO usuarios = (UsuariosDAO) getServletContext().getAttribute("usuarios");

		String nombre = nombre_usu;
		int tipoUsu = usuario.getTipo_usu();
		List<String> listaCategorias = null;
		List<String> listaColores = null;
		listaCategorias = notas.obtenerCategorias(nombre);
		listaColores = notas.obtenerColores(nombre);
		String errorCategoria = "No se han encontrado categorías";
		String errorColor = "No se han encontrado colores";
		String errorUsuario = "No se existe este usuario";

		StringBuffer error = new StringBuffer();
		StringBuffer categorias = new StringBuffer();
		StringBuffer colores = new StringBuffer();
		
		if (nombre_usu != null) {

			boolean usuarioExiste = usuarios.existeUsuario(nombre);
			if (usuarioExiste) {
				
				
				
				if (!listaCategorias.isEmpty()) {// vemos si no esta vacia
					String[] tablaCategorias = new String[listaCategorias.size()]; // creamos una tabla del mismo tamano
																					// que la lista devuelta
					int i = listaCategorias.size();// una variable del tamano
					i = 0;
					for (String cat : listaCategorias) {// llenamos la tabla con los datos de la lista
						tablaCategorias[i] = cat;
						i++;
					}

					for (int j = 0; j < tablaCategorias.length; j++) {// hacemos el armado del mensaje aunque este nos
																		// devolvera en el
																		// indice[0] y los dos ultimos indices
																		// caracteres demas
						tablaCategorias[j] = "\"" + tablaCategorias[j] + "\"" + ",";

					}

					for (int x = 0; x < tablaCategorias.length; x++) {// lo transformamos a un solo string, este es
																		// StringBuffer
																		// esto lo busqye de internet
						categorias = categorias.append(tablaCategorias[x]);// el stringBuffer tiene el metodo append()
					}
					categorias.deleteCharAt(categorias.length() - 1);// quitamos ultimo caracter
					categorias.deleteCharAt(categorias.length() - 1);// penultimo
					categorias.deleteCharAt(0);// primero

					System.out.println(categorias);// ahora si la cadena esta correcta

				} else {
					error.append(errorCategoria);// si la lista de categorias esta vacia llenamos el mensaje de error
													// para JSON
				}

				if (!listaColores.isEmpty()) {////////// todo es lo mismo que categoria
					String[] tablaColores = new String[listaColores.size()];
					int i = listaColores.size();
					i = 0;
					for (String col : listaColores) {
						tablaColores[i] = col;
						i++;
					}

					for (int j = 0; j < tablaColores.length; j++) {
						tablaColores[j] = "\"" + tablaColores[j] + "\"" + ",";

					}
					for (String string : tablaColores) {
						System.out.println(string);
					}

					for (int x = 0; x < tablaColores.length; x++) {
						colores = colores.append(tablaColores[x]);
					}
					colores.deleteCharAt(colores.length() - 1);
					colores.deleteCharAt(colores.length() - 1);
					colores.deleteCharAt(0);

					System.out.println(colores);

				} else {
					error.append(errorColor);
				}
			}else
				error.append(errorUsuario);

			
		} else {
			System.out.println("El parametro " + nombre_usu + "no es del tipo admin");
			error.append(errorUsuario);

		}

		// La creación de JSON se puede simplificar usando librerías, pero aquí
		// lo hacemos directamente
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("{\"categorias\":[\"" + categorias + "\"],\"colores\":[\"" + colores + "\"]"
				+ ",\"error\":[\"" + error + "\"]}");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
