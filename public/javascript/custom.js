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

/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function dropdownFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}