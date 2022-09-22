package controler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controler.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controler extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contacto. */
	JavaBeans contacto = new JavaBeans();

	/**
	 * Instantiates a new controler.
	 */
	public Controler() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/main")) {
			contactos(request, response);
		} else if (action.equals("/insert")) {
			novoContacto(request, response);
		} else if (action.equals("/select")) {
			listarContacto(request, response);
		} else if (action.equals("/update")) {
			editarContacto(request, response);
		} else if (action.equals("/delete")) {
			removerContacto(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

/**
 * Contactos.
 *
 * @param request the request
 * @param response the response
 * @throws ServletException the servlet exception
 * @throws IOException Signals that an I/O exception has occurred.
 */
//	listar contactos
	protected void contactos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		criando um objecto para receber os dados javaBeans
		ArrayList<JavaBeans> lista = dao.listarContactos();
//		encaminhar a lista ao documento agenda.jsp
		request.setAttribute("conta", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

/**
 * Novo contacto.
 *
 * @param request the request
 * @param response the response
 * @throws ServletException the servlet exception
 * @throws IOException Signals that an I/O exception has occurred.
 */
//	novo contacto
	protected void novoContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contacto.setNome(request.getParameter("nome"));
		contacto.setNum(request.getParameter("num"));
		contacto.setEmail(request.getParameter("email"));

//		chamar o metodo inseririContacto
		dao.inserirContacto(contacto);
//		rederecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

/**
 * Listar contacto.
 *
 * @param request the request
 * @param response the response
 * @throws ServletException the servlet exception
 * @throws IOException Signals that an I/O exception has occurred.
 */
//	editar contacto
	protected void listarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		recebimento do id do contacto a ser editado
		String id = request.getParameter("id");
//		setar a variavel JavaBeans
		contacto.setId(id);
//		executar o metodo selecionar contacto (DAO)
		dao.selecionarContacto(contacto);

		request.setAttribute("id", contacto.getId());
		request.setAttribute("nome", contacto.getNome());
		request.setAttribute("num", contacto.getNum());
		request.setAttribute("email", contacto.getEmail());

//		encaminhar ao jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contacto.setId(request.getParameter("id"));
		contacto.setNome(request.getParameter("nome"));
		contacto.setNum(request.getParameter("num"));
		contacto.setEmail(request.getParameter("email"));

		dao.alterarContacto(contacto);

		response.sendRedirect("main");
	}

/**
 * Remover contacto.
 *
 * @param request the request
 * @param response the response
 * @throws ServletException the servlet exception
 * @throws IOException Signals that an I/O exception has occurred.
 */
//	remover contacto
	protected void removerContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		contacto.setId(id);

		dao.deletarContacto(contacto);
		
		response.sendRedirect("main");
	}
	
/**
 * Gerar relatorio.
 *
 * @param request the request
 * @param response the response
 * @throws ServletException the servlet exception
 * @throws IOException Signals that an I/O exception has occurred.
 */
//	relatorio pdf
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		Document documento = new Document();
		
		try {
			response.setContentType("apllication/pdf");
			
			response.addHeader("Content-Disposition", "inline; filename=" + "relatorio.pdf");
			
			PdfWriter.getInstance(documento, response.getOutputStream());
			
			documento.open();
			
			documento.add(new Paragraph("Lista de Contactos: "));
			documento.add(new Paragraph(" "));
			
			PdfPTable tabela = new PdfPTable(3);
			
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Numero"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			
			ArrayList<JavaBeans> lista = dao.listarContactos();
			
			for (int i=0; i<lista.size();i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getNum());
				tabela.addCell(lista.get(i).getEmail());
			}
			
			documento.add(tabela);
			
			documento.close();
			} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
