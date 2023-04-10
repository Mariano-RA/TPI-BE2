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
      dataType: "json",
      async: false,
      cache: false,
      success: function (response) {
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong></strong> Se registro el turno </div>";
        $("#response").append(successAlert);
        $("#response").css({ display: "block" });

        resetUploadForm();
      },
      error: function (response) {
        console.log(response);
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
    $("#username").val(null);
    $("#password").val(null);
  }
});
