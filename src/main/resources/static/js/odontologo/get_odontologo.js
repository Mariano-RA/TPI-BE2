$(document).ready(function () {
  (function () {
    $.ajax({
      type: "GET",
      url: "/odontologos",
      success: function (response) {
        $.each(response, (i, odontologo) => {
          let get_More_Info_Btn =
            "<button" +
            " id=" +
            '"' +
            "btn_id_" +
            odontologo.id +
            '"' +
            ' type="button" class="btn btn-outline-success btn_id">' +
            odontologo.id +
            "</button>";
          let delete_Btn =
            "<button" +
            " id=" +
            '"' +
            "btnDelete_id_" +
            odontologo.id +
            '"' +
            ' type="button" class="btn btn-outline-success btnDelete_id">' +
            " X </button>";
          let tr_id = "tr_" + odontologo.id;
          let odontologoRow =
            '<tr id="' +
            tr_id +
            '"' +
            ">" +
            "<td>" +
            get_More_Info_Btn +
            "</td>" +
            '<td class="td_nombre">' +
            odontologo.nombre.toUpperCase() +
            "</td>" +
            '<td class="td_apellido">' +
            odontologo.apellido +
            "</td>" +
            '<td class="td_matricula">' +
            odontologo.matricula +
            "</td>" +
            "<td>" +
            delete_Btn +
            "</td>" +
            "</tr>";
          $("#odontologoTable tbody").append(odontologoRow);
        });
      },
      error: function (e) {
        alert("ERROR: ", e);
        console.log("ERROR: ", e);
      },
    });
  })();

  (function () {
    let pathname = window.location.pathname;
    if (pathname == "/odontologos.html") {
      $(".nav .nav-item a:last").addClass("active");
    }
  })();
});
