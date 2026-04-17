document.getElementById("formDisfraz").addEventListener("submit", async function(e) {
    e.preventDefault();

    const disfraz = {
        nombre: document.getElementById("nombre").value,
        categoria: document.getElementById("categoria").value,
        talla: document.getElementById("talla").value,
        precio: parseFloat(document.getElementById("precio").value),
        descripcion: document.getElementById("descripcion").value,
        imagenUrl: document.getElementById("imagenUrl").value,
        cantidad: parseInt(document.getElementById("cantidad").value),
        disponible: true
    };

    const mensaje = document.getElementById("mensaje");

    try {
        const respuesta = await fetch("http://localhost:8080/disfraces/guardar", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(disfraz)
        });

        if (respuesta.ok) {
            mensaje.innerText = "Disfraz guardado correctamente";
            document.getElementById("formDisfraz").reset();
        } else {
            mensaje.innerText = "Error al guardar";
        }

    } catch (error) {
        mensaje.innerText = "Error de conexión";
    }
});