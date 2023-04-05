$(document).ready(function () {
  $("#add_new_turno").submit(function (evt) {
    evt.preventDefault();

    let formData = {
      paciente: {
        id: $("#idPaciente").val(),
        nombre: $("#nombrePaciente").val(),
        apellido: $("#apellidoPaciente").val(),
        dni: $("#dniPaciente").val(),
        domicilio: {
          calle: $("#callePaciente").val(),
          numero: $("#numeroPaciente").val(),
          ciudad: $("#ciudadPaciente").val(),
          provincia: $("#provinciaPaciente").val(),
        },
      },
      odontologo: {
        id: $("#idOdontologo").val(),
        nombre: $("#nombreOdontologo").val(),
        apellido: $("#apellidoOdontologo").val(),
        matricula: $("#matriculaOdontologo").val(),
      },
    };

    $.ajax({
      url: "/turnos",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(formData),
      dataType: "json",
      async: false,
      cache: false,
      success: function (response) {
        let turno = response;
        console.log(response);
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong></strong> Se registro el turno </div>";
        $("#response").append(successAlert);
        $("#response").css({ display: "block" });

        resetUploadForm();
      },
      error: function (response) {
        let errorAlert =
          '<div class="alert alert-danger alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong> Error intente nuevamente</strong> </div>";
        $("#response").append(errorAlert);
        $("#response").css({ display: "block" });

        resetUploadForm();
      },
    });
  });

  function resetUploadForm() {
    $("#nombreOdontologo").val(null);
    $("#apellidoOdontologo").val(null);
    $("#matriculaOdontologo").val(null);
    $("#nombrePaciente").val(null);
    $("#apellidoPaciente").val(null);
    $("#dniPaciente").val(null);
    $("#callePaciente").val(null);
    $("#numeroPaciente").val(null);
    $("#provinciaPaciente").val(null);
    $("#ciudadPaciente").val(null);
  }

  (function () {
    let pathname = window.location.pathname;
    if (pathname === "/") {
      $(".nav .nav-item a:first").addClass("active");
    } else if (pathname == "/turnos.html") {
      $(".nav .nav-item a:last").addClass("active");
    }
  })();
});
