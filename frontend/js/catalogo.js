async function cargarDisfraces() {
    const respuesta = await fetch("http://localhost:8080/disfraces/aleatorios");
    const disfraces = await respuesta.json();

    const catalogo = document.getElementById("catalogo");

    disfraces.forEach(disfraz => {
        catalogo.innerHTML += `
            <div>
                <img src="${disfraz.imagenUrl}" width="150">
                <h3>${disfraz.nombre}</h3>
                <p>${disfraz.categoria}</p>
                <p>$${disfraz.precio}</p>
                <hr>
            </div>
        `;
    });
}

cargarDisfraces();