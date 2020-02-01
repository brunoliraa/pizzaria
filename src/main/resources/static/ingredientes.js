$(document).ready(function() {
    $('#btn-salvar').on('click', function () {
        var url = 'ingrediente';
        var dadosIngrediente = $('#form-ingredientes').serialize();

        $.post(url, dadosIngrediente)
            .done(function (pagina) {
             alert("salvo com sucesso");
                $('#secao-ingredientes').html(pagina)

            })
            .fail(function () {
                alert('Erro ao salvar!');

            })
            .always(function(){
            $('#modal-ingredientes').modal('hide');
            });
    });
});