window.onload = function () {
  const pwShowHide = document.querySelector(".pw_show_hide");
  const inputId = document.querySelector("input[type=text]");
  const inputPw = document.querySelector("input[type=password]");
  const idError = document.querySelector(".id-error");
  const pwError = document.querySelector(".pw-error");

  console.log(pwShowHide);
  console.log(idError);
  console.log(pwError);
  console.log(inputId, inputPw);

  let check = true;
  pwShowHide.addEventListener("click", () => {
    if (check) {
      pwShowHide.style.backgroundPosition = "-105px 0";
      check = false;
    } else {
      pwShowHide.style.backgroundPosition = "-126px 0";
      check = true;
    }
  });
};
