const catalogo = document.getElementById("catalogo");

async function cargar() {
    const res = await fetch("http://localhost:8080/disfraces/aleatorios");
    const data = await res.json();

    catalogo.innerHTML = "";

    data.forEach(d => {
        catalogo.innerHTML += `
            <div>
                <img src="${d.imagenUrl}" width="120">
                <h3>${d.nombre}</h3>
                <p>$${d.precio}</p>
                <button onclick="reservar(${d.id})">Reservar</button>
            </div>
        `;
    });
}

cargar();