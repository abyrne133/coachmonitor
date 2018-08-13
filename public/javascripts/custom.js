function updateSlideValue(slideId, outputId){
    var el = document.getElementById(slideId);
    document.getElementById(outputId).innerHTML = el.value;
}