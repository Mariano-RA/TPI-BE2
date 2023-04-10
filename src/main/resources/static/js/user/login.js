$(document).ready(function () {
  $("#login_user_form").submit(function (evt) {
    evt.preventDefault();

    let formData = {
      username: $("#username").val(),
      password: $("#password").val(),
    };

    $.ajax({
      url: "/auth/login",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(formData),
      async: false,
      cache: false,
      success: function (response) {
        localStorage.setItem("UserLogged", response);
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong></strong> Ingresaste correctamente </div>";
        $("#response").append(successAlert);
        $("#response").css({ display: "block" });
        setTimeout(() => {
          window.location.replace("../pages/inicio.html");
        }, 1000);
        resetUploadForm();
      },
      error: function (response) {
        console.log(response.headers);
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

  $(document).on("click", "#registrarUser", function (evt) {
    evt.preventDefault();
    $("#inputsLogin").css({ display: "None" });
    $("#inputsRegister").css({ display: "Block" });
    $("#registrarUser").css({ display: "None" });
    $("#guardarDatos").css({ display: "Block" });
  });

  $(document).on("click", "#guardarDatos", function (evt) {
    evt.preventDefault();

    let valorSeleccionado = document.getElementById("rolUser");

    let formData = {
      username: $("#userRegister").val(),
      password: $("#passRegister").val(),
      rol: valorSeleccionado.value,
    };

    $.ajax({
      url: "/auth/register",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(formData),
      async: false,
      cache: false,
      success: function (response) {
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong></strong> Registrado correctamente </div>";
        $("#responseRegister").append(successAlert);
        $("#responseRegister").css({ display: "block" });
        setTimeout(() => {
          $("#inputsLogin").css({ display: "Block" });
          $("#inputsRegister").css({ display: "None" });
          $("#responseRegister").css({ display: "None" });
          $("#registrarUser").css({ display: "Block" });
          $("#guardarDatos").css({ display: "None" });
        }, 1000);
        resetUploadForm();
      },
      error: function (error) {
        console.log(error);
        alert("Error -> " + error);
      },
    });
  });

  function resetUploadForm() {
    $("#username").val(null);
    $("#password").val(null);
  }
});
