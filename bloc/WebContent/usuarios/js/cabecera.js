"use strict";

var ultClick = true;

function muestraOculta() {

	if(ultClick){
		document.getElementById("subseccion").style.display="block";
		ultClick = false;
	}
	else{
		document.getElementById("subseccion").style.display="none";
		ultClick = true;

	}
	

}

// Asigna eventos al cargar la pagina
window.addEventListener("load", function() {
	var subseccion = document.getElementById("seccion");
	subseccion.onclick = muestraOculta;

});
