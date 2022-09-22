<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%

@ SuppressWarnings ("unchecked")
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("conta");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" href="img/FontBook.png">
<link rel="stylesheet" href="style.css">
<title>Agenda de contactos</title>
</head>
<body>
	<h1>Agenda de Contactos</h1>
	<a href="novo.html">Novo Contacto</a>
	<a href="report">Gerar relatorio</a>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Numero</th>
				<th>E-mail</th>
				<th>Opcoes</th>
			</tr>

		</thead>

		<tbody>

			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getNum()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td><a href="select?id=<%=lista.get(i).getId()  %>" class="editar">Editar</a></td>
				<td><a href="javascript: confirmar(<%=lista.get(i).getId() %>)" class="excluir">Excluir</a></td>
			</tr>

			<%
			}
			%>

		</tbody>
	</table>
	<script type="text/javascript" src="script.js"></script>
</body>
</html>