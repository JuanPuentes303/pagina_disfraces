const catalogo = document.getElementById("catalogo");
const relacionados = document.getElementById("relacionados");

async function cargarDisfraces() {
    const res = await fetch("http://localhost:8080/disfraces/aleatorios");
    const data = await res.json();

    mostrar(data);
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
                <hr>
            </div>
        `;
    });
}

async function buscar() {
    const texto = document.getElementById("buscador").value;

    const res = await fetch(`http://localhost:8080/disfraces/buscar?texto=${texto}`);
    const data = await res.json();

    mostrar(data);

    mostrarRelacionados(texto);
}

async function mostrarRelacionados(texto) {
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

cargarDisfraces();