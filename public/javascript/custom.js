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

function getAthleteEntries(urlToGet){
    $.ajax({
        url: urlToGet, type: 'GET', success: function(result){
            $("#journalEntries").html(result);
            colourCells();
        }
    });
}


/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function toggleDropdown() {
    document.getElementById("myDropdown").classList.toggle("show");
}

function searchTable() {
  // Declare variables
  var input, filter, table, tr, td, i;
  input = document.getElementById("searchBox");
  filter = input.value.toUpperCase();
  table = document.getElementById("athlete-table");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function searchTables() {
  // Declare variables
  var input, filter, table, data, i;
  input = document.getElementById("searchBoxMobile");
  filter = input.value.toUpperCase();
  tables = document.getElementsByClassName("athlete-tables");
  for(j = 0; j <tables.length; j++){
    data = tables[j].innerHTML;
       if (data) {
          if (data.toUpperCase().indexOf(filter) > -1) {
            tables[j].style.display = "";
          } else {
            tables[j].style.display = "none";
          }
       }
   }

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
$(document).ready(function(){
  colourCells();
});

function colourCells(){
    var num;
    $('td.colourCell').each(function(){
        num = parseInt($(this).text());
        if (num < '3') {
            $(this).css('background-color','rgb(255, 77, 77)');
        } else if (num < '6') {
             $(this).css('background-color','rgb(255, 184, 77)');
        } else if (num < '9') {
             $(this).css('background-color','rgb(255, 255, 77)');
        } else {
             $(this).css('background-color','rgb(166, 255, 77)');
        }
    });
}