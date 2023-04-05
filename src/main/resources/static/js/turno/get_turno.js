$(document).ready(function () {
  (function () {
    $.ajax({
      type: "GET",
      url: "/turnos",
      success: function (response) {
        $.each(response, (i, turno) => {
          let get_More_Info_Btn =
            "<button" +
            " id=" +
            '"' +
            "btn_id_" +
            turno.id +
            '"' +
            ' type="button" class="btn btn-outline-success btn_id">' +
            turno.id +
            "</button>";
          let delete_Btn =
            "<button" +
            " id=" +
            '"' +
            "btnDelete_id_" +
            turno.id +
            '"' +
            ' type="button" class="btn btn-outline-success btnDelete_id">' +
            " X </button>";
          let tr_id = "tr_" + turno.id;
          let turnoRow =
            '<tr id="' +
            tr_id +
            '"' +
            ">" +
            "<td>" +
            get_More_Info_Btn +
            "</td>" +
            '<td class="td_fechaTurno">' +
            new Date(
              turno.fechaTurno[0],
              turno.fechaTurno[1] - 1,
              turno.fechaTurno[2]
            ).toLocaleDateString() +
            "</td>" +
            '<td class="td_paciente">' +
            turno.paciente.nombre +
            " " +
            turno.paciente.apellido +
            "</td>" +
            '<td class="td_odontologo">' +
            turno.odontologo.nombre +
            " " +
            turno.odontologo.apellido +
            "</td>" +
            "<td>" +
            delete_Btn +
            "</td>" +
            "</tr>";
          $("#turnoTable tbody").append(turnoRow);
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
    if (pathname == "./turnos.html") {
      $(".nav .nav-item a:last").addClass("active");
    }
  })();
});
