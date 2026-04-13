document.getElementById("loginForm").addEventListener("submit", async function(e) {
    e.preventDefault();

    const correo = document.getElementById("correo").value;
    const contraseña = document.getElementById("contraseña").value;
    const mensaje = document.getElementById("mensaje");

    const datos = {
        correo,
        contraseña
    };

    try {
        const respuesta = await fetch("http://localhost:8080/usuarios/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(datos)
        });

        if (respuesta.ok) {
            const usuario = await respuesta.json();

            localStorage.setItem("usuarioLogueado", JSON.stringify(usuario));

            window.location.href = "catalogo.html";
        } else {
            mensaje.innerText = "Correo o contraseña incorrectos";
        }

    } catch (error) {
        mensaje.innerText = "Error de conexión con el servidor";
    }
});