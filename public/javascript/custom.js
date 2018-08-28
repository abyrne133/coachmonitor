function updateSlideValue(slideId, outputId){
    var el = document.getElementById(slideId);
    document.getElementById(outputId).innerHTML = el.value;
}

function del(urlToDelete){
    $.ajax({
        url: urlToDelete, type: 'DELETE', success: function(results){
            location.reload();
        }
    });
}