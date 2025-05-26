import React, { useEffect, useState } from "react";

export default function MiCuenta() {
  const [usuario, setUsuario] = useState(null);
  const userId = localStorage.getItem("usuarioId");

  useEffect(() => {
    if (userId) {
      fetch(`http://localhost:8080/api/users/${userId}`)
        .then((res) => res.json())
        .then((data) => setUsuario(data))
        .catch((err) =>
          console.error("Error al cargar datos del usuario:", err)
        );
    }
  }, [userId]);

  if (!userId) {
    return (
      <div className="container mt-4">
        <div className="alert alert-danger text-center" role="alert">
          No se encontró el ID de usuario en el localStorage.
        </div>
      </div>
    );
  }

  if (!usuario) {
    return (
      <div className="container mt-4 text-center">
        <div className="spinner-border text-primary" role="status">
          <span className="visually-hidden">Cargando...</span>
        </div>
        <p className="mt-2">Cargando datos del usuario...</p>
      </div>
    );
  }

  return (
    <div className="container py-5">
      <div className="row">
        <div className="col-md-8 offset-md-2">
          <h2 className="text-primary mb-4 text-center">
            Mi Cuenta - Datos del Paciente
          </h2>
          <div className="card shadow-lg p-3 mb-5 bg-body rounded">
            <div className="card-body">
              <form className="row g-3">
                {/* Campos de solo lectura con bordes */}
                <div className="col-md-6">
                  <label htmlFor="nombre" className="form-label">
                    Nombre
                  </label>
                  <input
                    type="text"
                    className="form-control" // CAMBIO AQUÍ: de form-control-plaintext a form-control
                    id="nombre"
                    value={usuario.nombre}
                    readOnly
                  />
                </div>
                <div className="col-md-6">
                  <label htmlFor="apellidoPaterno" className="form-label">
                    Apellido Paterno
                  </label>
                  <input
                    type="text"
                    className="form-control" // CAMBIO AQUÍ
                    id="apellidoPaterno"
                    value={usuario.apellidoPaterno}
                    readOnly
                  />
                </div>
                <div className="col-md-6">
                  <label htmlFor="apellidoMaterno" className="form-label">
                    Apellido Materno
                  </label>
                  <input
                    type="text"
                    className="form-control" // CAMBIO AQUÍ
                    id="apellidoMaterno"
                    value={usuario.apellidoMaterno}
                    readOnly
                  />
                </div>
                <div className="col-md-6">
                  <label htmlFor="fechaNacimiento" className="form-label">
                    Fecha de Nacimiento
                  </label>
                  <input
                    type="text"
                    className="form-control" // CAMBIO AQUÍ
                    id="fechaNacimiento"
                    value={usuario.fechaNacimiento}
                    readOnly
                  />
                </div>

                {/* Campos editables (ya tienen bordes con form-control) */}
                <div className="col-md-6">
                  <label htmlFor="telefono" className="form-label">
                    Teléfono
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="telefono"
                    defaultValue={usuario.telefono}
                  />
                </div>
                <div className="col-md-6">
                  <label htmlFor="email" className="form-label">
                    Email
                  </label>
                  <input
                    type="email"
                    className="form-control"
                    id="email"
                    defaultValue={usuario.email}
                  />
                </div>
                <div className="col-md-6">
                  <label htmlFor="genero" className="form-label">
                    Género
                  </label>
                  <select
                    className="form-select"
                    id="genero"
                    defaultValue={usuario.genero}
                  >
                    <option value="M">Masculino</option>
                    <option value="F">Femenino</option>
                    <option value="O">Otro</option>
                  </select>
                </div>
                <div className="col-md-6">
                  <label htmlFor="departamento" className="form-label">
                    Departamento
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="departamento"
                    defaultValue={usuario.departamento}
                  />
                </div>
                <div className="col-md-6">
                  <label htmlFor="provincia" className="form-label">
                    Provincia
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="provincia"
                    defaultValue={usuario.provincia}
                  />
                </div>
                <div className="col-md-6">
                  <label htmlFor="distrito" className="form-label">
                    Distrito
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="distrito"
                    defaultValue={usuario.distrito}
                  />
                </div>
                <div className="col-md-12">
                  <label htmlFor="direccion" className="form-label">
                    Dirección
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="direccion"
                    defaultValue={usuario.direccion}
                  />
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
