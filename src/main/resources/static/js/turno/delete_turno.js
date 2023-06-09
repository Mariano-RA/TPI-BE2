$(document).ready(function () {
  $(document).on("click", "table button.btnDelete_id", function () {
    let id_of_button = event.srcElement.id;
    let turnoId = id_of_button.split("_")[2];
    let numeroFila = "tr_" + turnoId;

    $.ajax({
      url: "/turnos/" + turnoId,
      type: "DELETE",
      headers: { Authorization: localStorage.getItem("UserLogged")},
      success: function () {
        document.getElementById(`${numeroFila}`).remove();
      },
      error: function (error) {
        console.log(error);
        alert("Error -> " + error);
      },
    });
  });
});
