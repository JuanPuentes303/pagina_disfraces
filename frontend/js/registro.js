document.getElementById("registroForm").addEventListener("submit", async function(e) {
    e.preventDefault();

    const nombre = document.getElementById("nombre").value;
    const correo = document.getElementById("correo").value;
    const contraseña = document.getElementById("contraseña").value;
    const telefono = document.getElementById("telefono").value;
    const mensaje = document.getElementById("mensaje");

    const regexNombre = /^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$/;

    if (!regexNombre.test(nombre)) {
        mensaje.innerText = "El nombre no puede tener números";
        return;
    }

    const usuario = {
        nombre,
        correo,
        contraseña,
        telefono
    };

    try {
        const respuesta = await fetch("http://localhost:8080/usuarios/registro", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(usuario)
        });

        if (respuesta.ok) {
            mensaje.innerText = "Usuario registrado correctamente";
            document.getElementById("registroForm").reset();
        } else {
            mensaje.innerText = "Error al registrar";
        }

    } catch (error) {
        mensaje.innerText = "Error de conexión";
    }
});