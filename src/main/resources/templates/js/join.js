const validatePassword = function () {
  let userId = document.getElementById("userId").value;
  fetch(`/member/${userId}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok " + response.statusText);
      }
      return response.json();
    })
    .then((data) => {
      console.log(data); // 서버로부터 받은 응답 처리
    })
    .catch((error) => {
      console.error(
        "There has been a problem with your fetch operation:",
        error
      );
    });
};

const validateUserId = function () {};

const validatePhone = function () {};
