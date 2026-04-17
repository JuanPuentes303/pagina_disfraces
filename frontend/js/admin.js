document.getElementById("formDisfraz").addEventListener("submit", async e => {
    e.preventDefault();

    const file = document.getElementById("imagen").files[0];
    const formData = new FormData();
    formData.append("file", file);

    const resImg = await fetch("http://localhost:8080/archivos/subir", {
        method: "POST",
        body: formData
    });

    const imagenUrl = await resImg.text();

    const disfraz = {
        nombre: nombre.value,
        categoria: categoria.value,
        talla: talla.value,
        precio: precio.value,
        descripcion: descripcion.value,
        imagenUrl,
        cantidad: cantidad.value
    };

    await fetch("http://localhost:8080/disfraces/guardar", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(disfraz)
    });

    alert("Guardado");
});