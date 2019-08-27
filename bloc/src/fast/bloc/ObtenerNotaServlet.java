package fast.bloc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ObtenerNotaServlet
 */
//@WebServlet("/usuarios/nota")
@WebServlet(urlPatterns= {"/usuarios/nota", "/admins/nota"})
public class ObtenerNotaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerNotaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		// Creamos respuesta en formato JSON 
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		String idStr = request.getParameter("id");
		String mensajeError = "";
		String mensajeNota ="";
		String mensajeUrlImagen ="";
	    NotasDAO notas = (NotasDAO) getServletContext().getAttribute("notas");
		Nota nota = null;
		try {
			if (usuario.getTipo_usu()==fast.bloc.Usuario.ADMINISTRADOR) {
				nota = notas.obtener(Integer.parseInt(idStr));
			} else {
				nota = notas.obtener(Integer.parseInt(idStr), usuario.getNombre());
			}
			if ( nota == null ) {
				mensajeError = "La nota no existe.";
			} else { // solo se puede usar nota si no es null
				mensajeNota = nota.getNota();
				mensajeUrlImagen = nota.getUrlimagen();
			}

		} catch (DAOException e) {
			mensajeError = e.getMessage();
		} catch (NumberFormatException e) {
			mensajeError = "Parámetro incorrecto";
		}
		
		//La creación de JSON se puede simplificar usando librerías, pero aquí
		// lo hacemos directamente
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(
				"{ \"nota\":\"" + mensajeNota + "\" , \"imagen\":\"" + mensajeUrlImagen
				+ "\", \"categoria\": \"" + nota.getCategoria() + "\", \"color\": \"" + nota.getColor()
				+ "\", \"error\":\"" + mensajeError + "\" }");
	
	}

}
