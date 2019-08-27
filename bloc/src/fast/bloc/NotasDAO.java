package fast.bloc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class NotasDAO {
	private DataSource ds = null;
	
	public NotasDAO() {
	}
	
	public NotasDAO(DataSource ds) {
		super();
		this.ds = ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public DataSource getDs() {
		return ds;
	}
	
	/**
	 * Borra un anota que coincida con (id)
	 * @param id
	 * @return true si se ha borrado una tupla o false en caso contrario
	 * @throws DAOException
	 */
	public boolean borrar(int id) throws DAOException {
		Connection conn;
		boolean resultado=false;
		
		try {
			System.out.println("Se va a borrar la nota con id="+id);
			conn = ds.getConnection();
			String sql = "DELETE FROM notas WHERE id=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			int contador = st.executeUpdate();
			if (contador == 1) {
				System.out.println("Se ha borrado la nota con id="+id);
				resultado=true;
			}
			System.out.println("borrados="+contador);
			st.close();
			conn.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw (new DAOException("Error en borrar("+id+") de NotasDAO"));
		}
		return resultado;
	}
	
	/**
	 * Borra una nota que coincida con (id,usuario)
	 * @param id
	 * @param usuario
	 * @return true si se ha borrado una tupla o false en caso contrario
	 * @throws DAOException
	 */
	public boolean borrar(int id, String usuario) throws DAOException {
		Connection conn;
		boolean resultado=false;
		
		try {
			System.out.println("Se va a borrar la nota con id="+id+" del usuario="+usuario);
			conn = ds.getConnection();
			String sql = "DELETE FROM notas WHERE id=? AND nombre_usuario=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			st.setString(2, usuario);
			int contador = st.executeUpdate();
			if (contador == 1) {
				System.out.println("Se ha borrado la nota con id="+id);
				resultado=true;
			}
			System.out.println("borrados="+contador);
			st.close();
			conn.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw (new DAOException("Error en borrar("+id+", "+usuario+") de NotasDAO"));
		}
		return resultado;
	}
	
	/**
	 * Obtiene una nota que coincida con (id)
	 * @param id
	 * @return nota con ese valor (id) o null si no se encuentra
	 * @throws DAOException
	 */
	public Nota obtener(int id) throws DAOException {
		Nota nota = null;
		Connection conn;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM notas WHERE id=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			System.out.println("Se va a buscar la nota con id="+id);
			if (rs.next()) {
				nota = new Nota();
				nota.setId(rs.getInt(1)); 
				nota.setNombreUsuario(rs.getString(2));
				nota.setTitulo(rs.getString(3));
				nota.setNota(rs.getString(4));
				nota.setUrlimagen(rs.getString(5));
				nota.setCategoria(rs.getString(6));
				nota.setColor(rs.getString(7));
				System.out.println("Se ha encontrado la nota con id="+nota.getId());
			}
			rs.close();
			st.close();
			conn.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw (new DAOException("Error en obtener("+id+") de NotasDAO"));
		}
		return nota;
	}
	
	/**
	 * Obtiene una nota que coincida con (id,usuario)
	 * @param id
	 * @param usuario
	 * @return nota con esa pareja (id,usuario) o null si no se encuentra
	 * @throws DAOException
	 */
	public Nota obtener(int id, String usuario) throws DAOException {
		Nota nota = null;
		Connection conn;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM notas WHERE id=? AND nombre_usuario=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			st.setString(2, usuario);
			ResultSet rs = st.executeQuery();
			System.out.println("Se va a buscar la nota con id="+id+" del usuario="+usuario);
			if (rs.next()) {
				nota = new Nota();
				nota.setId(rs.getInt(1)); 
				nota.setNombreUsuario(rs.getString(2));
				nota.setTitulo(rs.getString(3));
				nota.setNota(rs.getString(4));
				nota.setUrlimagen(rs.getString(5));
				nota.setCategoria(rs.getString(6));
				nota.setColor(rs.getString(7));
				System.out.println("Se ha encontrado la nota con id="+nota.getId());
			}
			rs.close();
			st.close();
			conn.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw (new DAOException("Error en obtener("+id+", "+usuario+") de NotasDAO"));
		}
		return nota;
	}
	
	/**
	 * Obtiene una lista de notas con los títulos e id solo.
	 * @param usuario
	 * @return Lista con las notas del usuario, o lista vacía
	 */
	public List<Nota> obtenerTitulos(String usuario) {
		List<Nota> lista = new ArrayList<>();
		Connection conn;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT id, titulo FROM notas WHERE nombre_usuario=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, usuario);
			ResultSet rs = st.executeQuery();
			System.out.println("Se van a buscar las notas  del usuario="+usuario);
			while (rs.next()) {
				Nota nota = new Nota();
				nota.setId(rs.getInt(1)); 
				nota.setTitulo(rs.getString(2));
				System.out.println("Se ha encontrado la nota con id="+nota.getId()+" y titulo="+nota.getTitulo());
				lista.add(nota);
			}
			rs.close();
			st.close();
			conn.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en obtenerTitulos("+usuario+") de NotasDAO.");
		}
		return lista;
	}

	/**
	 * Obtiene categorías de las notas de un usuario 
	 * @param usuario
	 * @return Lista con las categorias únicas (DISTINCT) de un usuario
	 */
	public List<String> obtenerCategorias(String usuario) {
		List<String> lista = new ArrayList<>();
		Connection conn;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT DISTINCT categoria FROM notas WHERE nombre_usuario=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, usuario);
			ResultSet rs = st.executeQuery();
			System.out.println("Se van a buscar las categorías en las notas  del usuario="+usuario);
			while (rs.next()) {
				System.out.println("Se ha encontrado la categoria "+rs.getString(1));
				lista.add(rs.getString(1));
			}
			rs.close();
			st.close();
			conn.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en obtenerCategorias("+usuario+") de NotasDAO.");
		}
		return lista;
	}

	/**
	 * Obtiene colores de las notas de un usuario
	 * @param usuario
	 * @return Lista con los colores únicos (DISTINCT) de un usuario
	 */
	public List<String> obtenerColores(String usuario) {
		List<String> lista = new ArrayList<>();
		Connection conn;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT DISTINCT color FROM notas WHERE nombre_usuario=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, usuario);
			ResultSet rs = st.executeQuery();
			System.out.println("Se van a buscar los colores en las notas  del usuario="+usuario);
			while (rs.next()) {
				System.out.println("Se ha encontrado el color "+rs.getString(1));
				lista.add(rs.getString(1));
			}
			rs.close();
			st.close();
			conn.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en obtenerColores("+usuario+") de NotasDAO.");
		}
		return lista;
	}

	/**
	 * Obtiene el texto de las notas.
	 *  Si todas es true devuelve la de todos los usuarios
	 *  Si todas es false devuelve la del usuario nombre 
	 * @param todas
	 * @param nombre
	 * @return
	 */
	public List<String> obtenerTextoNotas(boolean todas, String nombre) {
		List<String> lista = new ArrayList<>();
		Connection conn;

		try {
			conn = ds.getConnection();
			String sql = null;
			if (todas) {
				sql = "SELECT nota FROM notas";
			}else {
				sql = "SELECT nota FROM notas WHERE nombre_usuario=?";
			}
			PreparedStatement st = conn.prepareStatement(sql);
			if (!todas)
				st.setString(1, nombre);
			ResultSet rs = st.executeQuery();
			System.out.println("Se va a buscar el texto de las notas");
			while (rs.next()) {
				System.out.println("Se ha encontrado "+rs.getString(1));
				lista.add(rs.getString(1));
			}
			rs.close();
			st.close();
			conn.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en obtenerTextoNotas("+nombre+") de NotasDAO.");
		}
		return lista;
	}
	

	/**
	 * Obtiene una lista de TODAS las notas con los títulos, id y nombre_usuario solo.
	 * @return Lista con TODAS las notas (de todos los usuarios), o lista vacía
	 */
	public List<Nota> obtenerTitulos() {
		List<Nota> lista = new ArrayList<>();
		Connection conn;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT id, titulo, nombre_usuario FROM notas";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			System.out.println("Se van a buscar TODAS las notas  de todos los usuarios");
			while (rs.next()) {
				Nota nota = new Nota();
				nota.setId(rs.getInt(1)); 
				nota.setTitulo(rs.getString(2));
				nota.setNombreUsuario(rs.getString(3));
				System.out.println("Se ha encontrado la nota con id="+nota.getId()+", titulo="+nota.getTitulo()+" y nombre_usuario="+nota.getTitulo());
				lista.add(nota);
			}
			rs.close();
			st.close();
			conn.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en obtenerTitulos() de NotasDAO.");
		}
		return lista;
	}
	
	/**
	 * Inserta una nota en la tabla notas
	 * Los campos que ya tienen datos son: nombre_usuario, titulo, nota, urlimagen, categoria, color
	 * El campo id lo genera el SGBBDD
	 * @param nota
	 * @return true si se ha insertado o false si no
	 * @throws DAOException
	 */
	public boolean insertar(Nota nota) throws DAOException {
		Connection conn;
		boolean resultado=false;
		
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color) VALUES (?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nota.getNombreUsuario());
			st.setString(2, nota.getTitulo());
			st.setString(3, nota.getNota());
			st.setString(4, nota.getUrlimagen());
			st.setString(5, nota.getCategoria());
			st.setString(6, nota.getColor());
			System.out.println("Se va a insertar la nota del usuario="+nota.getNombreUsuario());
			int contador = st.executeUpdate();
			if (contador == 1) {
				System.out.println("Se ha insertado la nota del usuario="+nota.getNombreUsuario());
				resultado=true;
			}
			st.close();
			conn.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw (new DAOException("Error en insertar(nota) de NotasDAO"));
		}
		return resultado;
	}
	
	/**
	 * Actualiza los datos de la nota que coincida con (id,usuario)
	 * @param id
	 * @param usuario
	 * @param nota
	 * @return true si se ha actualizado o false si no
	 * @throws DAOException
	 */
	public boolean actualizar(int id, String usuario, Nota nota) throws DAOException {
		Connection conn;
		boolean resultado=false;
		
		try {
			conn = ds.getConnection();
			String sql = "UPDATE notas "
					+ "SET titulo=?, nota=?, urlimagen=?, categoria=?, color=? "
					+ "WHERE id=? AND nombre_usuario=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nota.getTitulo());
			st.setString(2, nota.getNota());
			st.setString(3, nota.getUrlimagen());
			st.setString(4, nota.getCategoria());
			st.setString(5, nota.getColor());
			st.setInt(6, id);
			st.setString(7, usuario);
			System.out.println("Se va a actualizar la nota con id="+id+" del usuario="+usuario);
			int contador = st.executeUpdate();
			if (contador == 1) {
				System.out.println("Se ha actualizado la nota del usuario="+nota.getNombreUsuario());
				resultado=true;
			}
			st.close();
			conn.close();	

		}
		catch (SQLException e) {
			e.printStackTrace();
			throw (new DAOException("Error en actualizar("+id+", "+usuario+",nota) de NotasDAO"));
		}
		return resultado;
	}
	
	public List<Usuario> obtenerUsuarios() {
		List<Usuario> lista = new ArrayList<>();
		Connection conn;

		try {
			conn = ds.getConnection();
			String sql = "SELECT DISTINCT nombre_usuario FROM notas";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			System.out.println("Se van a buscar los nombres de los usuarios dentro de la tabla USUARIOS");
			while (rs.next()) {
				Usuario usu = new Usuario();
				usu.setNombre(rs.getString(1));
				System.out.println("Se ha encontrado el usuario: " + usu.getNombre());
				lista.add(usu);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en obtenerUsuarios en UsuariosDAO.");
		}
		return lista;
	}
	
}
