// ========================================
// CARGAR MUNICIPIOS
// ========================================

document.addEventListener("DOMContentLoaded", function () {

    const departamentoSelect = document.getElementById("departamento");
    const municipioSelect = document.getElementById("municipio");
    const municipioPlaceholder = document.body.dataset.municipioPlaceholder;

    if (!departamentoSelect || !municipioSelect) {
        return;
    }

    function cargarMunicipios(departamentoId, municipioId = "") {

        municipioSelect.innerHTML =
            '<option value="">' + municipioPlaceholder + '</option>';

        if (!departamentoId) {
            return;
        }

        fetch(`/municipios/${departamentoId}`)
            .then(response => response.json())
            .then(municipios => {

                municipios.forEach(municipio => {

                    const option = document.createElement("option");

                    option.value = municipio.id;
                    option.textContent = municipio.nombre;

                    municipioSelect.appendChild(option);
                });

                // Seleccionar municipio actual al editar
                if (municipioId) {
                    municipioSelect.value = municipioId;
                }
            })
            .catch(error => {
                console.error("Error al cargar municipios:", error);
            });
    }

    // Cambio manual de departamento
    departamentoSelect.addEventListener("change", function () {

        cargarMunicipios(this.value);
    });

    // Cargar datos existentes al editar
    const departamentoActual =
        departamentoSelect.dataset.departamento;

    const municipioActual =
        municipioSelect.dataset.municipio;

    if (departamentoActual) {

        departamentoSelect.value = departamentoActual;

        cargarMunicipios(
            departamentoActual,
            municipioActual
        );
    }
    // Restaurar datos al limpiar durante la edición
    const btnLimpiar = document.getElementById("btnLimpiar");

    if (btnLimpiar) {

        const abrirModal =
            document.body.dataset.abrirModal === "true";

        if (abrirModal) {

            btnLimpiar.addEventListener("click", function () {

                setTimeout(function () {

                    const departamentoOriginal =
                        departamentoSelect.dataset.departamento;

                    const municipioOriginal =
                        municipioSelect.dataset.municipio;

                    if (departamentoOriginal) {

                        departamentoSelect.value =
                            departamentoOriginal;

                        cargarMunicipios(
                            departamentoOriginal,
                            municipioOriginal
                        );
                    }

                }, 0);
            });
        }
    }
});

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

//Alerta para dar de baja y alta
    document.querySelectorAll('.form-cambiar-estado').forEach(form => {

    form.addEventListener('submit', function(event) {

        event.preventDefault();

        const boton = form.querySelector('button');
        const esActivo = boton.classList.contains('btn-danger');

        const nombre = form.dataset.nombre;
        const apellido = form.dataset.apellido;

        const mensajes = document.body.dataset;
        const accion = esActivo ? mensajes.confirmarBaja : mensajes.confirmarAlta;

        const confirmacion = alertify.confirm(
            '',
            '<div class="text-center">' +

            '<div style="font-size: 58px; color: #dc3545; margin-bottom: 14px;">' +
            '<i class="fa-solid fa-triangle-exclamation"></i>' +
            '</div>' +

            '<h3 style="font-weight: 600; font-size: 22px; margin: 5px 0 15px;">\n' +
            '    ATENCIÓN\n' +
            '</h3>' +

            '<p style="font-size: 16px; margin-bottom: 8px;">' +
            '¿Está seguro que desea <strong>' + accion + '</strong> a?' +
            '</p>' +

            '<p style="font-size: 19px; font-weight: 600; margin-top: 12px; margin-bottom: 5px;">' +
            '<i class="fa-solid fa-user" style="margin-right: 6px;"></i>' +
            nombre + ' ' + apellido +
            '</p>' +

            '</div>',

            function() {
                form.submit();
            },

            function() {
                alertify.error('Ha cancelado la operación').dismissOthers();
            }

        ).set('labels', {
            ok: 'Sí',
            cancel: 'No'
        }).set({
            transition: 'zoom'
        });

        confirmacion.setContent(
            '<div class="text-center">' +
            '<div style="font-size: 58px; color: #dc3545; margin-bottom: 14px;">' +
            '<i class="fa-solid fa-triangle-exclamation"></i>' +
            '</div>' +
            '<h3 style="font-weight: 600; font-size: 22px; margin: 5px 0 15px;">' +
            mensajes.confirmarTitulo +
            '</h3>' +
            '<p style="font-size: 16px; margin-bottom: 8px;">' +
            mensajes.confirmarMensaje + ' <strong>' + accion + '</strong> a:' +
            '</p>' +
            '<p style="font-size: 19px; font-weight: 600; margin-top: 12px; margin-bottom: 5px;">' +
            '<i class="fa-solid fa-user" style="margin-right: 6px;"></i>' +
            nombre + ' ' + apellido +
            '</p>' +
            '</div>'
        );
        confirmacion.set('labels', {
            ok: mensajes.confirmarSi,
            cancel: mensajes.confirmarNo
        });
        confirmacion.set('oncancel', function () {
            alertify.error(mensajes.confirmarCancelada).dismissOthers();
        });

    });

});
