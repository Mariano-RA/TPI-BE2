$(document).ready(function () {
  $("#update_paciente_form").submit(function (evt) {
    evt.preventDefault();
    try {
      let pacienteId = $("#paciente_id").val();

      let formData = {
        id: $("#paciente_id").val(),
        nombre: $("#nombre").val(),
        apellido: $("#apellido").val(),
        dni: $("#dni").val(),
        fechaIngreso: $("#fechaIngreso").val(),
        domicilio: {
          calle: $("#calle").val(),
          numero: $("#numero").val(),
          ciudad: $("#ciudad").val(),
          provincia: $("#provincia").val(),
        },
      };

      $.ajax({
        url: "/pacientes",
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(formData),
        dataType: "json",
        async: false,
        cache: false,
        success: function (response) {
          let paciente = response;

          let successAlert =
            '<div class="alert alert-success alert-dismissible">' +
            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
            "<strong> paciente actualizado </strong></div>";

          $("#tr_" + pacienteId + " td.td_nombre").text(
            paciente.nombre.toUpperCase()
          );
          $("#tr_" + pacienteId + " td.td_apellido").text(
            paciente.apellido.toUpperCase()
          );
          $("#tr_" + pacienteId + " td.td_dni").text(paciente.dni);
          $("#tr_" + pacienteId + " td.td_fechaIngreso").text(
            paciente.fechaIngreso
          );

          $("#response").empty();
          $("#response").append(successAlert);
          $("#response").css({ display: "block" });
        },

        error: function (response) {
          let errorAlert =
            '<div class="alert alert-danger alert-dismissible">' +
            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
            "<strong> Error </strong></div>";

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
    let pacienteId = id_of_button.split("_")[2];

    $.ajax({
      url: "/pacientes/" + pacienteId,
      type: "GET",
      success: function (response) {
        let paciente = response;
        $("#paciente_id").val(paciente.id);
        $("#nombre").val(paciente.nombre);
        $("#apellido").val(paciente.apellido);
        $("#dni").val(paciente.dni);
        $("#fechaIngreso").val(paciente.fechaIngreso);
        $("#calle").val(paciente.domicilio.calle);
        $("#numero").val(paciente.domicilio.numero);
        $("#ciudad").val(paciente.domicilio.ciudad);
        $("#provincia").val(paciente.domicilio.provincia);
        $("#div_paciente_updating").css({ display: "block" });
      },
      error: function (error) {
        console.log(error);
        alert("Error -> " + error);
      },
    });
  });
});
