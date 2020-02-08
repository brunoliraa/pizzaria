$(document).ready(function(){

    aplicarListeners();

    aplicatListenerBtnSalvar();

});

var limparModal = function(){
    $('#id').val('');
    $('#nome').val('');
    $('#preco').val('');
    $('#categoria').val('');
    $('#ingredientes option').attr('selected', false);
};

var aplicatListenerBtnSalvar = function(){
    $('#btn-salvar').on('click', function(){
        var url = 'pizza';
        var dadosPizza = $('#form-pizzas').serialize();
        $.post(url, dadosPizza)
            .done(function(pagina){
                $('#secao-pizzas').html(pagina)
                aplicarListeners();


            })
            .fail(function(){
                alert('Erro ao salvar!');

            })
            .always(function(){
                $('#modal-pizzas').modal('hide');
            });
    });
}

var aplicarListeners = function(){

    $('#modal-pizzas').on('hide.bs.modal', limparModal);

    $('.btn-deletar').on('click', function(){
        var pizzaId = $(this).parents('tr').data('id');
        var csrf = $('#csrf').val();

        $.ajax({
            url : 'pizza/'+pizzaId,
            type: 'DELETE',
            headers: {'X-CSRF-TOKEN': csrf},
            success: function() {
                $('tr[data-id="'+pizzaId+'"]').remove();
            }
        });

    });

    $('.btn-editar').on('click', function(){
        var pizzaId = $(this).parents('tr').data('id');
        var url = 'pizza/'+pizzaId;
        $.get(url)
            .success(function(pizza){
                $('#id').val(pizza.id);
                $('#nome').val(pizza.nome);
                $('#preco').val(pizza.preco);
                $('#categoria').val(pizza.categoria);

                pizza.ingredientes.forEach(function(ingrediente){
                    var id = ingrediente.id;
                    $('#ingredientes option[value='+id+']').attr('selected', true);
                });

                $('#modal-pizzas').modal('show');
            });;
    });

};