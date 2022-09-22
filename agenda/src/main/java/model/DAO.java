package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/**  Modulo de conexao *. */

//	Parametro de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dados?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "2121";

/**
 * Conectar.
 *
 * @return the connection
 */
//	metodo de conexao
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);

			return con;
		} catch (Exception e) {
			System.out.print(e);

			return null;
		}
	}

/**
 * Inserir contacto.
 *
 * @param contacto the contacto
 */
//	 CRUD CREATE
	public void inserirContacto(JavaBeans contacto) {

		String create = "insert into conta(nome, num, email) values(?,?,?)";

		try {
//			abrir conexao
			Connection con = conectar();
//			 preparar a query para execusao no bando de dados
			PreparedStatement pst = con.prepareStatement(create);

//			 substituir os parametros pelos conteudos das variaveis JavaBeans
			pst.setString(1, contacto.getNome());
			pst.setString(2, contacto.getNum());
			pst.setString(3, contacto.getEmail());

//			 Executar a query
			pst.executeUpdate();

//			 Encerar a conexao com o baco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	CRUD READ

	/**
 * Listar contactos.
 *
 * @return the array list
 */
public ArrayList<JavaBeans> listarContactos() {
		ArrayList<JavaBeans> contactos = new ArrayList<>();
		String read = "select * from conta order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String num = rs.getString(3);
				String email = rs.getString(4);

				contactos.add(new JavaBeans(id, nome, num, email));
			}
			con.close();
			return contactos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

//	CRUD UPDATE
/**
 * Selecionar contacto.
 *
 * @param contacto the contacto
 */
//	selecionar contacto
	public void selecionarContacto(JavaBeans contacto) {
		String read2 = "select * from conta where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contacto.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				contacto.setId(rs.getString(1));
				contacto.setNome(rs.getString(2));
				contacto.setNum(rs.getString(3));
				contacto.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

/**
 * Alterar contacto.
 *
 * @param contacto the contacto
 */
//	editar contacto
	public void alterarContacto(JavaBeans contacto) {
		String create = "update conta set nome=?, num=?, email=? where id=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contacto.getNome());
			pst.setString(2, contacto.getNum());
			pst.setString(3, contacto.getEmail());
			pst.setString(4, contacto.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

/**
 * Deletar contacto.
 *
 * @param contacto the contacto
 */
//	CRUD DELETE
	public void deletarContacto(JavaBeans contacto) {
		String delete = "delete from conta where id=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contacto.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
