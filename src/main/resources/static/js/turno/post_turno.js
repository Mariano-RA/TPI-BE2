$(document).ready(function () {
  $("#add_new_turno").submit(function (evt) {
    evt.preventDefault();

    let formData = {
      idPaciente: $("#idPaciente").val(),
      idOdontologo: $("#idOdontologo").val(),
    };

    $.ajax({
      url: "/turnos",
      type: "POST",
      headers: { Authorization: localStorage.getItem("UserLogged")},
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

  function resetUploadForm() {
    $("#idOdontologo").val(null);
    $("#nombreOdontologo").val(null);
    $("#apellidoOdontologo").val(null);
    $("#matriculaOdontologo").val(null);
    $("#idPaciente").val(null);
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
