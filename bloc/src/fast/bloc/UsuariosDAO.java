package fast.bloc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
	private DataSource ds = null;

	public UsuariosDAO() {
	}

	public UsuariosDAO(DataSource ds) {
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
	 * Comprueba si un usuario existe y la clave es correcta. Devuelve objeto
	 * Usuario relleno si existe
	 * 
	 * @param nombre Nombre del usuario
	 * @param clave  Clave del usuario
	 * @return Objeto Usuario relleno o null si no existe.
	 */
	Usuario existe(String nombre, String contra) {

		Usuario usuario = null;
		Connection conn;
		try {
			System.out.println("Se va a comprobar el usuario=" + nombre + " y la clave=" + contra);
			conn = ds.getConnection();
			String sql = "SELECT * FROM usuarios WHERE nombre=? AND clave=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nombre);
			st.setString(2, contra);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				System.out.println("Se ha encontrado el usuario y la clave coincide.");
				usuario = new Usuario();
				usuario.setNombre(nombre);
				usuario.setTipo_usu(rs.getInt(3));
				System.out.println("El tipo de usuario es=" + usuario.getTipo_usu());
			} else {
				System.out.println("No se ha encontrado el usuario o la clave no coincide.");
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en existe(" + nombre + ", " + contra + ") de UsuariosDAO");
		}

		return usuario;
	}

	/**
	 * Obtiene una lista de todos los usuarios de la tabla usuarios.
	 * 
	 * @return Lista con TODAS los usuarios o vacio si no hay nadie
	 */

	public List<Usuario> obtenerUsuarios() {
		List<Usuario> lista = new ArrayList<>();
		Connection conn;

		try {
			conn = ds.getConnection();
			String sql = "SELECT DISTINCT nombre FROM usuarios";
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

	/*
	 * public List<Usuario> obtenerUsuarios() {
	 * 
	 * List<Usuario> lista = new ArrayList<>(); Connection conn;
	 * 
	 * try { conn = ds.getConnection(); String sql = "SELECT nombre FROM usuarios";
	 * PreparedStatement st = conn.prepareStatement(sql); ResultSet rs =
	 * st.executeQuery(); System.out.println("Se van a buscar TODOS los usuarios");
	 * while (rs.next()) { Usuario usuario = new Usuario();
	 * usuario.setNombre(rs.getString(1));
	 * System.out.println("Se ha encontrado el usuario con nombre_usuario=" +
	 * usuario.getNombre()); lista.add(usuario); } rs.close(); st.close();
	 * conn.close(); } catch (SQLException e) { e.printStackTrace();
	 * System.out.println("Error en obtenerTitulos() de NotasDAO."); } return lista;
	 * 
	 * }
	 */

}
