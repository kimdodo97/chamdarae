const validatePassword = function () {
  let userId = document.getElementById("userId").value;
  let duplicateSucess = document.getElementById("userIdSuccess");
  let duplicateFail = document.getElementById("userIdFail");
  fetch(`/member-login-id/${userId}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok " + response.statusText);
        }
        return response.json();
      })
      .then((data) => {
        if (data === false) {
          console.log("sucess");
          duplicateSucess.style.display = "block";
          duplicateFail.style.display = "none";
        } // 서버로부터 받은 응답 처리
        else {
          console.log("fail");
          duplicateSucess.style.display = "none";
          duplicateFail.style.display = "block";
        }
      })
      .catch((error) => {
        console.log("There has been a problem with your fetch operation:", error);
      });
};

const validateUserId = function () {};

const validatePhone = function () {};
