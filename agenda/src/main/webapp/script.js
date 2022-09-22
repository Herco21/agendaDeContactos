/**
*Confirmar a exclusao do contacto
*
*@author Herco ZauZau
*@param id
*/


function confirmar(id) {
	let resposta = confirm(`Excluir ${id}?`);

	if (resposta == true) {
		window.location.href = "delete?id=" + id;
	}
}