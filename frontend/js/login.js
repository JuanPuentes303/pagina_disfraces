async function login() {

    const correo = document.getElementById("correo").value;
    const contraseña = document.getElementById("password").value;

    const res = await fetch("http://localhost:8080/usuarios/login", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({correo, contraseña})
    });

    const data = await res.json();

    if (data) {
        localStorage.setItem("usuarioLogueado", JSON.stringify(data));
        window.location.href = "catalogo.html";
    } else {
        alert("Datos incorrectos");
    }
}