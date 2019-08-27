"use strict";


function crearCategoria(){
	var categoria = prompt("Introduce nueva categoria", "categoria");
	if (categoria) {
		var select = document.getElementById("categoria");
		var option = document.createElement("option");
		option.text = categoria.toLowerCase();
		select.add(option);
		select.value = categoria;
	}
}

function crearColor(){
	var color = document.getElementById("otrocolor").value;
	if (color != null) {
		var select = document.getElementById("color");
		var option = document.createElement("option");
		option.text = color.toLowerCase();
		select.add(option);
		select.value = color;
		cambiarColor();
	}
}


function cambiarColor() {
	var textarea = document.getElementById("nota");
	textarea.style.backgroundColor = document.getElementById("color").value;
}

window.addEventListener("load", function() {
	cambiarColor();
	//Asignamos eventos a los elementos de HTML
	document.getElementById("otracategoria").onclick = crearCategoria;
	document.getElementById("color").onchange = cambiarColor;
	document.getElementById("otrocolor").onchange = crearColor;
});
