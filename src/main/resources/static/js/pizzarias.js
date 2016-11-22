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
});