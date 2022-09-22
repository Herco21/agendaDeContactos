<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agenda</title>
<link rel="icon" href="img/FontBook.png">
<link rel="stylesheet" href="style.css">
</head>
<h1>Editar contacto</h1>

<form name="formu" action="update">
	<input type="Text" name="id" readonly class="readonly"
		value="<%out.print(request.getAttribute("id"));%>"> <input
		type="Text" name="nome" required="required"
		value="<%out.print(request.getAttribute("nome"));%>"> <input
		type="Text" name="num" required="required"
		value="<%out.print(request.getAttribute("num"));%>"> <input
		type="email" name="email" required="required"
		value="<%out.print(request.getAttribute("email"));%>">
	<button type="submit">Guardar</button>
</form>
</html>