var buscar = function(){
	
	var nomePizza = $('#pizza_pesquisa').val();
	var url = '/pizzas/' + nomePizza;
	
	$.get(url)
		.done(function(view){
			
			$('#secao-pizzarias').html(view);
		});
};

$(document).ready(function(){
	
	$('#btn_buscar').on('click', buscar);
	
	/*Plugin Mask*/
	$('#dataCadastro').mask('00/00/0000', {placeholder: "__/__/____"});
	$('#telefone').mask('(00)0000-0000', {placeholder: "(__)____-____"});
});