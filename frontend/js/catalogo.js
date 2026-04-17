const catalogo = document.getElementById("catalogo");
const relacionados = document.getElementById("relacionados");

async function cargarDisfraces() {
    try {
        const res = await fetch("http://localhost:8080/disfraces/aleatorios");
        const data = await res.json();

        mostrar(data);
    } catch (error) {
        console.error("Error al cargar disfraces");
    }
}

function mostrar(lista) {
    catalogo.innerHTML = "";

    lista.forEach(d => {
        catalogo.innerHTML += `
            <div>
                <img src="${d.imagenUrl}" width="120">
                <h3>${d.nombre}</h3>
                <p>${d.categoria}</p>
                <p>$${d.precio}</p>

                <button onclick="reservar(${d.id})">Reservar</button>

                <hr>
            </div>
        `;
    });
}

async function buscar() {
    const texto = document.getElementById("buscador").value;

    try {
        const res = await fetch(`http://localhost:8080/disfraces/buscar?texto=${texto}`);
        const data = await res.json();

        mostrar(data);
        mostrarRelacionados(texto);

    } catch (error) {
        console.error("Error en búsqueda");
    }
}

async function mostrarRelacionados(texto) {
    try {
        const res = await fetch(`http://localhost:8080/disfraces/buscar?texto=${texto}`);
        const data = await res.json();

        relacionados.innerHTML = "";

        data.slice(0, 3).forEach(d => {
            relacionados.innerHTML += `
                <div>
                    <h4>${d.nombre}</h4>
                </div>
            `;
        });

    } catch (error) {
        console.error("Error en relacionados");
    }
}

async function filtrarCategoria() {
    const categoria = document.getElementById("categoria").value;

    if (categoria === "") {
        cargarDisfraces();
        return;
    }

    try {
        const res = await fetch(`http://localhost:8080/disfraces/buscar?texto=${categoria}`);
        const data = await res.json();

        mostrar(data);

    } catch (error) {
        console.error("Error al filtrar");
    }
}

async function reservar(disfrazId) {

    const usuario = JSON.parse(localStorage.getItem("usuarioLogueado"));

    if (!usuario) {
        alert("Debes iniciar sesión");
        return;
    }

    const fechaInicio = prompt("Fecha inicio (YYYY-MM-DD):");
    const fechaFin = prompt("Fecha fin (YYYY-MM-DD):");

    if (!fechaInicio || !fechaFin) {
        alert("Debes ingresar las fechas");
        return;
    }

    const reserva = {
        usuarioId: usuario.id,
        disfrazId,
        fechaInicio,
        fechaFin
    };

    try {
        const res = await fetch("http://localhost:8080/reservas/crear", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(reserva)
        });

        if (res.ok) {
            alert("Reserva realizada correctamente");
        } else {
            alert("No disponible o error en fechas");
        }

    } catch (error) {
        alert("Error de conexión con el servidor");
    }
}

cargarDisfraces();