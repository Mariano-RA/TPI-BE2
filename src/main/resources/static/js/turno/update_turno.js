$(document).ready(function () {
  $("#update_turno_form").submit(function (evt) {
    evt.preventDefault();
    try {
      let turnoID = $("#turno_id").val();

      let formData = {
        id: $("#turno_id").val(),
        paciente: {
          id: $("#paciente_id").val(),
          nombre: $("#nombrePaciente").val(),
          apellido: $("#apellidoPaciente").val(),
          dni: $("#dniPaciente").val(),
          domicilio: {
            id: $("#odontologo_id").val(),
            calle: $("#callePaciente").val(),
            numero: $("#numeroPaciente").val(),
            ciudad: $("#ciudadPaciente").val(),
            provincia: $("#provinciaPaciente").val(),
          },
        },
        odontologo: {
          nombre: $("#nombreOdontologo").val(),
          apellido: $("#apellidoOdontologo").val(),
          matricula: $("#matriculaOdontologo").val(),
        },
      };

      $.ajax({
        url: "/turnos",
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(formData),
        dataType: "json",
        async: false,
        cache: false,
        success: function (response) {
          let turno = response;

          let successAlert =
            '<div class="alert alert-success alert-dismissible">' +
            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
            "<strong> Se actualizaron los datos del Turno</strong></div>";

          $("#tr_" + turnoID + " td.td_fechaTurno").text(turno.fechaTurno);
          $("#tr_" + turnoID + " td.td_paciente").text(
            turno.paciente.apellido.toUpperCase() + ", " + turno.paciente.nombre
          );
          $("#tr_" + turnoID + " td.td_odontologo").text(
            turno.odontologo.apellido.toUpperCase() +
              ", " +
              turno.odontologo.nombre
          );

          $("#response").empty();
          $("#response").append(successAlert);
          $("#response").css({ display: "block" });
        },

        error: function (response) {
          let errorAlert =
            '<div class="alert alert-danger alert-dismissible">' +
            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
            "<strong> Fallo la actualizacion </strong></div>";

          $("#response").empty();
          $("#response").append(errorAlert);
          $("#response").css({ display: "block" });
        },
      });
    } catch (error) {
      console.log(error);
      alert(error);
    }
  });

  $(document).on("click", "table button.btn_id", function () {
    let id_of_button = event.srcElement.id;
    let turnoId = id_of_button.split("_")[2];

    $.ajax({
      url: "/turnos/" + turnoId,
      type: "GET",
      success: function (response) {
        let turno = response;
        $("#odontologo_id").val(turno.odontologo.id);
        $("#nombreOdontologo").val(turno.odontologo.nombre);
        $("#apellidoOdontologo").val(turno.odontologo.apellido);
        $("#matriculaOdontologo").val(turno.odontologo.matricula);
        $("#paciente_id").val(turno.paciente.id);
        $("#nombrePaciente").val(turno.paciente.nombre);
        $("#apellidoPaciente").val(turno.paciente.apellido);
        $("#dniPaciente").val(turno.paciente.dni);
        $("#callePaciente").val(turno.paciente.calle);
        $("#numeroPaciente").val(turno.paciente.numero);
        $("#ciudadPaciente").val(turno.paciente.ciudad);
        $("#provinciaPaciente").val(turno.paciente.provincia);
        $("#div_turno_updating").css({ display: "block" });
      },
      error: function (error) {
        console.log(error);
        alert("Error -> " + error);
      },
    });
  });
});
