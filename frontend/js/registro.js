async function registrar() {

    const usuario = {
        nombre: document.getElementById("nombre").value,
        correo: document.getElementById("correo").value,
        contraseña: document.getElementById("password").value,
        telefono: document.getElementById("telefono").value
    };

    const res = await fetch("http://localhost:8080/usuarios/registro", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(usuario)
    });

    if (res.ok) {
        alert("Registrado");
        window.location.href = "login.html";
    }
}