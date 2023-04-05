$(document).ready(function () {
  (function () {
    $.ajax({
      type: "GET",
      url: "/pacientes",
      success: function (response) {
        $.each(response, (i, paciente) => {
          let get_More_Info_Btn =
            "<button" +
            " id=" +
            '"' +
            "btn_id_" +
            paciente.id +
            '"' +
            ' type="button" class="btn btn-outline-success btn_id">' +
            paciente.id +
            "</button>";
          let delete_Btn =
            "<button" +
            " id=" +
            '"' +
            "btnDelete_id_" +
            paciente.id +
            '"' +
            ' type="button" class="btn btn-outline-success btnDelete_id">' +
            " X </button>";
          let tr_id = "tr_" + paciente.id;
          let pacienteRow =
            '<tr id="' +
            tr_id +
            '"' +
            ">" +
            "<td>" +
            get_More_Info_Btn +
            "</td>" +
            '<td class="td_nombre">' +
            paciente.nombre.toUpperCase() +
            "</td>" +
            '<td class="td_apellido">' +
            paciente.apellido +
            "</td>" +
            '<td class="td_dni">' +
            paciente.dni +
            "</td>" +
            '<td class="td_fechaIngreso">' +
            new Date(
              paciente.fechaRegistro[0],
              paciente.fechaRegistro[1] - 1,
              paciente.fechaRegistro[2]
            ).toLocaleDateString() +
            "</td>" +
            '<td class="td_domicilio">' +
            paciente.domicilio.calle +
            " " +
            paciente.domicilio.numero +
            ", " +
            paciente.domicilio.ciudad +
            ", " +
            paciente.domicilio.provincia +
            "</td>" +
            "<td>" +
            delete_Btn +
            "</td>" +
            "</tr>";
          $("#pacienteTable tbody").append(pacienteRow);
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
    if (pathname == "./pacientes.html") {
      $(".nav .nav-item a:last").addClass("active");
    }
  })();
});
