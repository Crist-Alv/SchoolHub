// ========================================
// CARGAR MUNICIPIOS
// ========================================
function cargarMunicipios(departamentoId, municipioSeleccionado = "") {
    const municipioSelect = document.getElementById("municipio");

    municipioSelect.innerHTML = '<option value="">Seleccione el Municipio...</option>';

    if (!departamentoId) return;

    fetch(`/municipios/${departamentoId}`)
        .then(response => response.json())
        .then(municipios => {

            municipios.forEach(municipio => {
                const option = document.createElement("option");
                option.value = municipio.id;
                option.textContent = municipio.nombre;
                option.selected = String(municipio.id) === String(municipioSeleccionado);

                municipioSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error("Error al cargar municipios:", error);
        });
}

// ========================================
// DATATABLE DOCENTES
// ========================================
document.addEventListener("DOMContentLoaded", function () {

    const tabla = document.getElementById("tablaDocentes");

    if (!tabla) {
        return;
    }

    const idioma = tabla.dataset.idioma;

    $('#tablaDocentes').DataTable({
        pageLength: 5,
        lengthMenu: [5, 10, 25, 50],
        language: {
            url: "https://cdn.datatables.net/plug-ins/1.13.6/i18n/" + idioma + ".json"
        }
    });

});

// ========================================
// ABRIR MODAL
// ========================================
document.addEventListener("DOMContentLoaded", function () {

    const abrirModal = document.body.dataset.abrirModal === "true";
    const modalElement = document.getElementById("modalD");

    if (abrirModal && modalElement) {
        const modal = new bootstrap.Modal(modalElement);
        modal.show();
    }
});

// ========================================
// CIERRE DEL MODAL
// ========================================
document.addEventListener("DOMContentLoaded", function () {

    const modalElement = document.getElementById("modalD");

    if (modalElement) {

        modalElement.addEventListener("hidden.bs.modal", function () {

            // Regresar URL a /docente sin recargar
            window.history.replaceState({}, document.title, "/docente");

            // Recargar página limpia para resetear modo edición
            window.location.href = "/docente";
        });

    }

});

//Carga en editar el departamento y municipio correspondiente
document.addEventListener("DOMContentLoaded", function () {
    const departamentoSelect = document.getElementById("departamento");
    const municipioSelect = document.getElementById("municipio");

    if (!departamentoSelect || !municipioSelect) return;

    departamentoSelect.addEventListener("change", function () {
        cargarMunicipios(this.value);
    });

    if (departamentoSelect.value) {
        cargarMunicipios(departamentoSelect.value, municipioSelect.dataset.municipioSeleccionado);
    }

    const formulario = departamentoSelect.closest("form");

    formulario?.addEventListener("reset", function () {
        setTimeout(() => {
            cargarMunicipios(
                departamentoSelect.value,
                municipioSelect.dataset.municipioSeleccionado
            );
        }, 0);
    });
});
