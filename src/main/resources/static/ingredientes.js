$(document).ready(function() {

    aplicatListenerBtnSalvar();
    aplicarListeners();


});
var limparModal = function(){
    $('#id').val('');
    $('#nome').val('');
    $('#categoria').val('');
};

var  aplicarListeners = function () {
    $('.btn-deletar').on('click', function () {
        var id = $(this).parents('tr').data('id');
        $.ajax({
            url: "ingrediente/" + id,
            type: 'DELETE',
            //headers: {'X-CSRF-TOKEN': csrf},
            success: function (result) {
                $('tr[data-id="' + id + '"]').remove();
            }
        });

    });


    $('.btn-editar').on('click', function(){
        $('#modal-ingredientes').on('hide.bs.modal', limparModal);
        var id = $(this).parents('tr').data('id');
        var url = 'ingrediente/'+id;

        $.get(url)
            .done(function(ingredientes){
                $('#id').val(ingredientes.id);
                $('#nome').val(ingredientes.nome);
                $('#categoria').val(ingredientes.categoria);

                $('#modal-ingredientes').modal('show');
                aplicarListeners();

            });
    });
}

    var aplicatListenerBtnSalvar = function(){
    $('#btn-salvar').on('click', function () {
        var url = 'ingrediente';
        var dadosIngrediente = $('#form-ingredientes').serialize();

        $.post(url, dadosIngrediente)
            .done(function (pagina) {
                //alert("salvo com sucesso");
                $('#secao-ingredientes').html(pagina)
                aplicarListeners();;



            })
            .fail(function () {
                alert('Erro ao salvar!');

            })
            .always(function(){
                $('#modal-ingredientes').modal('hide');
            });
    });


}