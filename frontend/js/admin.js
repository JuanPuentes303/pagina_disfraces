document.getElementById("formDisfraz").addEventListener("submit", async function(e) {
    e.preventDefault();

    const archivo = document.getElementById("imagen").files[0];
    const mensaje = document.getElementById("mensaje");

    if (!archivo) {
        mensaje.innerText = "Debes seleccionar una imagen";
        return;
    }

    // 📤 SUBIR IMAGEN
    const formData = new FormData();
    formData.append("file", archivo);

    let imagenUrl = "";

    try {
        const resImagen = await fetch("http://localhost:8080/archivos/subir", {
            method: "POST",
            body: formData
        });

        imagenUrl = await resImagen.text();

    } catch {
        mensaje.innerText = "Error subiendo imagen";
        return;
    }

    const disfraz = {
        nombre: document.getElementById("nombre").value,
        categoria: document.getElementById("categoria").value,
        talla: document.getElementById("talla").value,
        precio: parseFloat(document.getElementById("precio").value),
        descripcion: document.getElementById("descripcion").value,
        imagenUrl: imagenUrl,
        cantidad: parseInt(document.getElementById("cantidad").value),
        disponible: true
    };

    try {
        const res = await fetch("http://localhost:8080/disfraces/guardar", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(disfraz)
        });

        if (res.ok) {
            mensaje.innerText = "Disfraz guardado correctamente";
            document.getElementById("formDisfraz").reset();
        } else {
            mensaje.innerText = "Error al guardar disfraz";
        }

    } catch {
        mensaje.innerText = "Error de conexión";
    }
});