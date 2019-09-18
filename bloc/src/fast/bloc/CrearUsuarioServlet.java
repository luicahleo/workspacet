package fast.bloc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class CrearUsuarioServlet
 */
@WebServlet("/admins/crearusuario")
public class CrearUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//El servlet toma los valores del formulario que esta en CrearUsuario.jsp
		
		String NombreUsuario = request.getParameter("nombre");
		String ClaveUsuario = request.getParameter("clave");
		String TipoUsuario = request.getParameter("tipo_usu"); //toma el campo value de option
		UsuariosDAO usuarios = (UsuariosDAO) getServletContext().getAttribute("usuarios");
		int tipoUsu = Integer.parseInt(TipoUsuario);
		System.out.println(tipoUsu);
		boolean usu_existe = false;
		String mensajeError="";
		
// 1º Forma: Utilizando las funciones de UsuariosDAO. Cambia la clave de un usuario pero si este no existe lo crea.
		
		try {
			if (NombreUsuario != null && ClaveUsuario != null && TipoUsuario != null && usuarios != null) {
				System.out.println("DEBUG::::::: entra");
				
				//Usuario usuario = usuarios.existeUsuario(NombreUsuario);
				//if(usuario != null) {
				//	usuario = usuarios.cambiaClave(NombreUsuario, ClaveUsuario); 
				//}else {
				//	usuario = usuarios.creaUsuario(NombreUsuario, ClaveUsuario, tipoUsu);
				//}
			}	
		}catch (NumberFormatException e) {
			// TODO: handle exception
			mensajeError="Parámetro Incorrecto";
		}
		response.sendRedirect("crearusuario.jsp?resultado");
	}
		
	/*// 2º Forma: Conectando directamente con la BBDD sin usar UsuariosDao. Cambia la clave de un usuario pero si este no existe lo crea.
	
		//Inicia la conexion con la BBDD y busca el usuario que coincide con el nombre de usuario
		try {
			DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
			Connection conn = ds.getConnection();
			String sql = "SELECT nombre FROM usuarios WHERE nombre=?";
			PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, NombreUsuario);
			ResultSet rs = pst.executeQuery();
		System.out.println("Se va a buscar el usuario con nombre"+NombreUsuario);
			while (rs.next()) {
				System.out.println("Se ha encontrado el usuario "+rs.getString(1));
				usu_existe = true;
		}
		rs.close();
		pst.close();
		//si existe ese usuario se le va a cambiar la clave
		if(usu_existe) {
			sql = "UPDATE usuarios SET clave=? where nombre=?";
			PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, ClaveUsuario);
				st.setString(2, NombreUsuario);
				int contador = st.executeUpdate();
				System.out.println("Se va a cambiar la clave del usuario:"+NombreUsuario);
				if (contador == 1) {
					System.out.println("Se ha actualizado la clave del usuario="+NombreUsuario);
			}
			st.close();
			//si no existe el usuario se crea junto con una clave y un tipo	
			}else {
			sql= "INSERT INTO usuarios (nombre, clave, tipo_usu) VALUES (?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, NombreUsuario);
				st.setString(2, ClaveUsuario);
				st.setInt(3, tipoUsu);
				int contador = st.executeUpdate();
			System.out.println("Se va a crear un usuario:"+NombreUsuario+" con la clave:"+ClaveUsuario+" y de tipo:"+tipoUsu);
				if (contador == 1) {
				System.out.println("Se ha creado correctamente");
			}
			st.close();
		}
			
		conn.close();	
	}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en obtener el usuario"+NombreUsuario+"");
		}
		response.sendRedirect("CrearUsuario.jsp?resultado");
	}
*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
