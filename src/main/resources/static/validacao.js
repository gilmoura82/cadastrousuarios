// Salvar No Banco de Dados
function salvarUsuario() {
		
		var id = $("#id").val();
		var nome = $("#nome").val();
		var idade = $("#idade").val(); 
		
		
		if (nome == "") {
			alert("Informe o Nome") 
		} else if (idade == 0) {
			alert("Informe a Idade") 
		} else {
			$.ajax({
			method: "POST",
			url: "salvar",
			data: JSON.stringify({ id: id, nome: nome, idade: idade }),
			contentType: "application/json; charset=utf-8",
			success: function(response) {
				alert("Cadastro Realizado Com Sucesso!!");
			}
			}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao salvar o Cadastro: " + xhr.responseText);
			});
		}	
		
	
}
		

// Pesquisa Por Nome
function pesquisaUsuarios() {
	
	var nome = $('#nameBusca').val();
	
	if (nome != null && nome.trim() != '') {
		$.ajax({
			 method: "GET",
			 url: "buscarPorNome",
			 data: "name= " + nome,
			 success: function (response) {
				$('#tabelaUsuarios > tbody > tr').remove();
				
				for(var i = 0; i < response.length; i++) {
					$('#tabelaUsuarios > tbody').append('<tr id = "'+response[i].id+'"><td>'+response[i].id+'</td><td>'+response[i].nome+'</td><td><button type="button" class="btn btn-warning" onclick="editarUsuario('+response[i].id+')">Editar</button></td><td><button type="button" class="btn btn-danger" onclick="excluirUsuario('+response[i].id+')">Excluir</button></td></tr>');
				}
			}
		}).fail(function (xhr, status, errorThrown) {
			alert("Erro ao realizar a pesquisa: " + xhr.responseText);
		});	
	}
	
		
	}

// Edição	
function editarUsuario(id) {
	
	$.ajax({
			 method: "GET",
			 url: "buscaruserid",
			 data: "iduser= " + id,
			 success: function (response) {
				
				$("#id").val(response.id);
				$("#nome").val(response.nome);
				$("#idade").val(response.idade); 
				
				$('#modalPesquisaUsuario').modal('hide');
		
				
			}
		}).fail(function (xhr, status, errorThrown) {
			alert("Erro ao buscar usuário por ID: " + xhr.responseText);
		});	
	
	
}		

// Exclusão 
function excluirUsuario(id) {
		
	if(confirm("Deseja realmente excluir o item selecionado?")) {
		$.ajax({
			 method: "DELETE",
			 url: "delete",
			 data: "iduser= " + id,
			 success: function (response) {
				
				$('#' + id).remove();
				
				alert(response);
				
			}
		}).fail(function (xhr, status, errorThrown) {
			alert("Erro ao excluir usuário por ID: " + xhr.responseText);
		});	
	}
	
	
	
}
	
// Excluir pela tela	
function excluirDaTela(id) {
	
	var id = $('#id').val();
	
	excluirUsuario(id);
	
}	
	

