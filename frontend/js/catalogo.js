async function cargarDisfraces() {
    const respuesta = await fetch("http://localhost:8080/disfraces/aleatorios");
    const disfraces = await respuesta.json();

    const catalogo = document.getElementById("catalogo");

    disfraces.forEach(disfraz => {
        catalogo.innerHTML += `
            <div>
                <h3>${disfraz.nombre}</h3>
                <p>Categoría: ${disfraz.categoria}</p>
                <p>Precio: $${disfraz.precio}</p>
                <p>${disfraz.descripcion}</p>
                <hr>
            </div>
        `;
    });
}

cargarDisfraces();