$(document).ready(function(){
	
	aplicarListener();
	
	aplicarListenerBtnSalvar();
	
});

var limparModal = function(){
	$('#id').val('');
	$('#nome').val('');
	$('#preco').val('');
	$('#categoria').val('');
	$('#ingredientes option').attr('selected', false);
};

var aplicarListenerBtnSalvar = function(){
	
	/*Função de salvar uma pizza*/
	$('#btn-salvar').on('click', function(){
		
		// informando a url a ser enviado o formulário
		var url = "pizza";
		
		// recebendo as informações do formulário e armazenando em uma variavel
		var dadosPizza = $('#form-pizza').serialize();
		
		// metodo para enviar o form pela url
		$.post(url, dadosPizza)
			.done(function(pagina){
				// alterando o conteudo da seção
				$('#secao-pizza').html(pagina);
				aplicarListener();
			})
			.fail(function(){
				alert('Erro ao salvar');
			})
			.always(function(){
				$('#modal-pizza').modal('hide');
			});
	});
}

var aplicarListener = function(){
	
	$('#modal-pizza').on('hide.bs.modal', limparModal);
	
	$('.btn-deletar').on('click', function(){
		
		// recebendo o id do objeto
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();
		
		$.ajax({
			url: "pizza/"+id,
			type: "DELETE",
			headers: {'X-CSRF-TOKEN': csrf},
			success: function(result){
				var pizzas = parseInt($('#quantidade-pizzas').text());
				
				$('tr[data-id="' + id + '"]').remove();
				$('#quantidade-pizzas').text(pizzas - 1);
			}
		});
		
	});
	
	
	/*Função para o btn editar*/
	$('.btn-editar').on('click', function(){
		
		var id = $(this).parents('tr').data('id');
		var url = "pizza/" + id;
		
		$.get(url)
			.done(function(pizza){
				
				$('#id').val(pizza.id);
				$('#nome').val(pizza.nome);
				$('#preco').val(pizza.preco);
				$('#categoria').val(pizza.categoria);
				
				pizza.ingredientes.forEach(function(ingrediente){
					var id = ingrediente.id;
					$('#ingredientes option[value = '+ id +']').attr('selected', true);
				});
				
				$('#modal-pizza').modal('show');
			});
	});
	
}