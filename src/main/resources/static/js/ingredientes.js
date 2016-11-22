$(document).ready(function(){
	
	aplicarListener();
	
});

var limparModal = function(){
	
	$('#id').val('');
	$('#nome').val('');
	$('#categoria').val('');
}

var aplicarListener = function(){
	
	$('#modal-ingrediente').on('hide.bs.modal', limparModal);
	
	/*Função para o btn editar*/
	$('.btn-editar').on('click', function(){
		
		var id = $(this).parents('tr').data('id');
		var url = "ingrediente/" + id;
		
		$.get(url)
			.done(function(ingrediente){
				
				$('#id').val(ingrediente.id);
				$('#nome').val(ingrediente.nome);
				$('#categoria').val(ingrediente.categoria);
				
				$('#modal-ingrediente').modal('show');
		});
		
		
		
		/*$('#modal-ingrediente').modal('show');*/
	});
	
	/*Função para cadastrar um ingrediente*/
	$('#btn-salvar').on('click', function(){
		
		var url = "ingrediente";
		var dadosIngrediente = $('#form-ingrediente').serialize();
		
		$.post(url, dadosIngrediente)
			.done(function(pagina){
				$('#secao-ingredientes').html(pagina);
				aplicarListener();
			})
			.fail(function(){
				alert('Erro ao salvar');
			})
			.always(function(){
				$('#modal-ingrediente').modal('hide');
			});
	});
	
	/*Função para deletar um ingrediente*/
	$('.btn-deletar').on('click', function(){
		
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();
		
		$.ajax({
			url: "ingrediente/"+id,
			type: "DELETE",
			headers: {'X-CSRF-TOKEN': csrf},
			success: function(result){
				/*Trazendo a quantidade de ingredientes da lista*/
				var ingredientes = parseInt($('#quantidade-ingredientes').text());
				
				$('tr[data-id="' + id + '"]').remove();
				$('#quantidade-ingredientes').text(ingredientes - 1);
			}
		});
	});
}