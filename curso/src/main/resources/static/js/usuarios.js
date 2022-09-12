// Call the dataTables jQuery plugin 
$(document).ready(function() {
	cargarUsuarios();

	$('#usuarios').DataTable();
	actualizarEmailDeUsuario();
}); 

function actualizarEmailDeUsuario(){
	document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

async function cargarUsuarios() {
	const request = await fetch('api/usuario', {
		method: 'GET',
		headers: getHeaders()
	});
	const usuarios = await request.json();


	
	let listadoHtml = '';
	for (let usuario of usuarios) {
		let telefonoText= usuario.telefono == null ? '-' : usuario.telefono;
		let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i>';
		let usuarioHtml = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + '' + usuario.apellido + '</td><td>'
			+ usuario.email + '</td><td>' + telefonoText
			+ '</td><td>' + botonEliminar + '</td></tr>'
		listadoHtml += usuarioHtml;  
	}

	console.log(usuarios);



	document.querySelector('#usuarios tbody').innerHTML = listadoHtml;

} 

function getHeaders(){
	return {
		'Accept': 'application/json',
			'Content-Type': 'application/json',
			'Authorization' : localStorage.tokenJwt
	};
}

async function eliminarUsuario(id) {


	if (!confirm('Desea eliminar este usuario?')) { //Este if solo entrara si hiciero click en false
		return;
	}
	
	
	const request = await fetch('api/usuario/' + id, {
		method: 'DELETE',
		headers: getHeaders()
	}); 
	
	location.reload()
}


