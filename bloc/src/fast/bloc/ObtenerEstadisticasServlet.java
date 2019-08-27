package fast.bloc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ObtenerEstadisticasServlet
 */
@WebServlet("/usuarios/estadisticas")
public class ObtenerEstadisticasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObtenerEstadisticasServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGett(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		String nombre = usuario.getNombre();
		int tipoUsu = usuario.getTipo_usu();
		String todos = request.getParameter("todos");
		int numMensajes = 0;
		float mediaMensajes = 0;
		NotasDAO notas = (NotasDAO) getServletContext().getAttribute("notas");
		List<String> listaTextoNotas = null;

		if (todos != null && tipoUsu == Usuario.ADMINISTRADOR)
			listaTextoNotas=notas.obtenerTextoNotas(true, nombre); 
		else
			listaTextoNotas=notas.obtenerTextoNotas(false, nombre); 

		if (!listaTextoNotas.isEmpty()) {
			for(String temp: listaTextoNotas) {
				numMensajes++;
				mediaMensajes += temp.length();
			}
			mediaMensajes /= numMensajes; // no hace falta comprobar !=0, por el if
		} else {
			System.out.println("No se han encontrado mensajes");
		}

		// La creación de JSON se puede simplificar usando librerías, pero aquí
		// lo hacemos directamente
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("{ \"numMensajes\": " + numMensajes + ", \"mediaMensajes\": " + mediaMensajes + "}");

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
