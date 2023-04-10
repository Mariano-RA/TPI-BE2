$(document).ready(function () {
  $("#update_turno_form").submit(function (evt) {
    evt.preventDefault();
    try {
      let turnoID = $("#turno_id").val();

      let formData = {
        id: $("#turno_id").val(),
        idPaciente: $("#idPaciente").val(),
        idOdontologo: $("#idOdontologo").val(),
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

          $("#tr_" + turnoID + " td.td_fechaTurno").text(
            new Date(
              turno.fechaTurno[0],
              turno.fechaTurno[1] - 1,
              turno.fechaTurno[2]
            ).toLocaleDateString()
          );
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

          setTimeout(() => {
            $("#response").css({ display: "none" });
            $("#div_turno_updating").css({ display: "none" });
          }, 2000);
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

  $(document).on("click", "#buscarOdontologo", function (evt) {
    evt.preventDefault();
    let idOdontologo = $("#idOdontologo").val();
    $.ajax({
      url: "/odontologos/" + idOdontologo,
      type: "GET",
      success: function (response) {
        let odontologo = response;
        $("#nombreOdontologo").val(odontologo.nombre);
        $("#apellidoOdontologo").val(odontologo.apellido);
        $("#matriculaOdontologo").val(odontologo.matricula);
      },
      error: function (error) {
        console.log(error);
        alert("Error -> " + error);
      },
    });
  });

  $(document).on("click", "#buscarPaciente", function (evt) {
    evt.preventDefault();
    let idPaciente = $("#idPaciente").val();
    $.ajax({
      url: "/pacientes/" + idPaciente,
      type: "GET",
      success: function (response) {
        let paciente = response;
        $("#nombrePaciente").val(paciente.nombre);
        $("#apellidoPaciente").val(paciente.apellido);
        $("#dniPaciente").val(paciente.dni);
        $("#callePaciente").val(paciente.domicilio.calle);
        $("#numeroPaciente").val(paciente.domicilio.numero);
        $("#ciudadPaciente").val(paciente.domicilio.ciudad);
        $("#provinciaPaciente").val(paciente.domicilio.provincia);
      },
      error: function (error) {
        console.log(error);
        alert("Error -> " + error);
      },
    });
  });

  $(document).on("click", "table button.btn_id", function () {
    let id_of_button = event.srcElement.id;
    let turnoId = id_of_button.split("_")[2];

    $.ajax({
      url: "/turnos/" + turnoId,
      type: "GET",
      success: function (response) {
        let turno = response;
        $("#turno_id").val(turno.id);
        $("#fechaTurno").val(turno.fechaTurno);
        $("#idOdontologo").val(turno.odontologo.id);
        $("#nombreOdontologo").val(turno.odontologo.nombre);
        $("#apellidoOdontologo").val(turno.odontologo.apellido);
        $("#matriculaOdontologo").val(turno.odontologo.matricula);
        $("#idPaciente").val(turno.paciente.id);
        $("#nombrePaciente").val(turno.paciente.nombre);
        $("#apellidoPaciente").val(turno.paciente.apellido);
        $("#dniPaciente").val(turno.paciente.dni);
        $("#callePaciente").val(turno.paciente.domicilio.calle);
        $("#numeroPaciente").val(turno.paciente.domicilio.numero);
        $("#ciudadPaciente").val(turno.paciente.domicilio.ciudad);
        $("#provinciaPaciente").val(turno.paciente.domicilio.provincia);
        $("#div_turno_updating").css({ display: "block" });
      },
      error: function (error) {
        console.log(error);
        alert("Error -> " + error);
      },
    });
  });
});
