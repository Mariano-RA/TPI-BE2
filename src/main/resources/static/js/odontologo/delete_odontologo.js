$(document).ready(function () {
  $(document).on("click", "table button.btnDelete_id", function () {
    let id_of_button = event.srcElement.id;
    let odontologoId = id_of_button.split("_")[2];
    let numeroFila = "tr_" + odontologoId;

    $.ajax({
      url: "/odontologos/" + odontologoId,
      type: "DELETE",
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
