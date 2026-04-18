const catalogo = document.getElementById("catalogo");
const relacionados = document.getElementById("relacionados");

const usuarioData = JSON.parse(localStorage.getItem("usuarioLogueado"));
document.getElementById("usuario").innerText = "Bienvenido: " + usuarioData.nombre;

async function cargarDisfraces() {
    const res = await fetch("http://localhost:8080/disfraces/aleatorios");
    const data = await res.json();

    mostrar(data);
}

function mostrar(lista) {
    catalogo.innerHTML = "";

    lista.forEach(d => {
        catalogo.innerHTML += `
            <div class="col-md-4 mb-4">
                <div class="card h-100 text-center">

                    <img src="${d.imagenUrl}" class="card-img-top" style="height:200px; object-fit:cover;">

                    <div class="card-body">
                        <h5>${d.nombre}</h5>
                        <p>${d.categoria}</p>
                        <p>$${d.precio}</p>

                        <button class="btn btn-primary" onclick="reservar(${d.id})">
                            Reservar
                        </button>
                    </div>

                </div>
            </div>
        `;
    });
}

async function buscar() {
    const texto = document.getElementById("buscador").value;

    const res = await fetch(`http://localhost:8080/disfraces/buscar?texto=${texto}`);
    const data = await res.json();

    mostrar(data);
    mostrarRelacionados(data);
}

function mostrarRelacionados(lista) {
    relacionados.innerHTML = "";

    lista.slice(0, 3).forEach(d => {
        relacionados.innerHTML += `
            <div class="col-md-4">
                <div class="card p-2 text-center">
                    <h6>${d.nombre}</h6>
                </div>
            </div>
        `;
    });
}

async function filtrarCategoria() {
    const categoria = document.getElementById("categoria").value;

    if (categoria === "") {
        cargarDisfraces();
        return;
    }

    const res = await fetch(`http://localhost:8080/disfraces/buscar?texto=${categoria}`);
    const data = await res.json();

    mostrar(data);
}

async function reservar(disfrazId) {

    const usuario = JSON.parse(localStorage.getItem("usuarioLogueado"));

    const fechaInicio = prompt("Fecha inicio (YYYY-MM-DD):");
    const fechaFin = prompt("Fecha fin (YYYY-MM-DD):");

    if (!fechaInicio || !fechaFin) {
        alert("Debes ingresar ambas fechas");
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
            alert("Reserva realizada");
        } else {
            alert("No disponible o error");
        }

    } catch {
        alert("Error de conexión");
    }
}

function logout() {
    localStorage.removeItem("usuarioLogueado");
    window.location.href = "login.html";
}

cargarDisfraces();