// components/RegistroUsuario.jsx
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  validarDni,
  registrarUsuario,
  validarEmail,
  validarTelefono,
  validarDNI,
  validarPassword,
} from "./services/UserService";
import "./RegistroUsuarioPage.css";

export const RegistroUsuario = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    dni: "",
    password: "",
    confirmPassword: "",
    nombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    fechaNacimiento: "",
    telefono: "",
    email: "",
    genero: "",
    departamento: "",
    provincia: "",
    distrito: "",
    direccion: "",
  });

  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const [dniValidado, setDniValidado] = useState(false);
  const [validandoDni, setValidandoDni] = useState(false);
  const [registroExitoso, setRegistroExitoso] = useState(false);

  // Listas para selects
  const departamentos = [
    "Amazonas",
    "Áncash",
    "Apurímac",
    "Arequipa",
    "Ayacucho",
    "Cajamarca",
    "Callao",
    "Cusco",
    "Huancavelica",
    "Huánuco",
    "Ica",
    "Junín",
    "La Libertad",
    "Lambayeque",
    "Lima",
    "Loreto",
    "Madre de Dios",
    "Moquegua",
    "Pasco",
    "Piura",
    "Puno",
    "San Martín",
    "Tacna",
    "Tumbes",
    "Ucayali",
  ];

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    // Limpiar error del campo cuando el usuario empiece a escribir
    if (errors[name]) {
      setErrors((prev) => ({
        ...prev,
        [name]: "",
      }));
    }
  };

  const handleValidarDni = async () => {
    if (!validarDNI(formData.dni)) {
      setErrors((prev) => ({
        ...prev,
        dni: "El DNI debe tener 8 dígitos",
      }));
      return;
    }

    try {
      setValidandoDni(true);
      const response = await validarDni(formData.dni);

      if (response.success) {
        setFormData((prev) => ({
          ...prev,
          nombre: response.nombres,
          apellidoPaterno: response.apellidoPaterno,
          apellidoMaterno: response.apellidoMaterno,
        }));
        setDniValidado(true);
        setErrors((prev) => ({
          ...prev,
          dni: "",
        }));
      } else {
        setErrors((prev) => ({
          ...prev,
          dni: "No se encontraron datos para este DNI",
        }));
      }
    } catch (error) {
      setErrors((prev) => ({
        ...prev,
        dni: "Error al validar DNI. Intente nuevamente.",
      }));
    } finally {
      setValidandoDni(false);
    }
  };

  const validateForm = () => {
    const newErrors = {};

    // Validar DNI
    if (!formData.dni) {
      newErrors.dni = "El DNI es obligatorio";
    } else if (!validarDNI(formData.dni)) {
      newErrors.dni = "El DNI debe tener 8 dígitos";
    } else if (!dniValidado) {
      newErrors.dni = "Debe validar el DNI primero";
    }

    // Validar contraseña
    if (!formData.password) {
      newErrors.password = "La contraseña es obligatoria";
    } else if (!validarPassword(formData.password)) {
      newErrors.password = "La contraseña debe tener al menos 6 caracteres";
    }

    // Validar confirmación de contraseña
    if (!formData.confirmPassword) {
      newErrors.confirmPassword = "Debe confirmar la contraseña";
    } else if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = "Las contraseñas no coinciden";
    }

    // Validar campos obligatorios
    if (!formData.nombre) newErrors.nombre = "El nombre es obligatorio";
    if (!formData.apellidoPaterno)
      newErrors.apellidoPaterno = "El apellido paterno es obligatorio";
    if (!formData.apellidoMaterno)
      newErrors.apellidoMaterno = "El apellido materno es obligatorio";
    if (!formData.fechaNacimiento)
      newErrors.fechaNacimiento = "La fecha de nacimiento es obligatoria";
    if (!formData.genero) newErrors.genero = "El género es obligatorio";

    // Validar teléfono
    if (!formData.telefono) {
      newErrors.telefono = "El teléfono es obligatorio";
    } else if (!validarTelefono(formData.telefono)) {
      newErrors.telefono = "El teléfono debe tener 9 dígitos";
    }

    // Validar email
    if (!formData.email) {
      newErrors.email = "El email es obligatorio";
    } else if (!validarEmail(formData.email)) {
      newErrors.email = "Email inválido";
    }

    // Validar dirección
    if (!formData.departamento)
      newErrors.departamento = "El departamento es obligatorio";
    if (!formData.provincia)
      newErrors.provincia = "La provincia es obligatoria";
    if (!formData.distrito) newErrors.distrito = "El distrito es obligatorio";
    if (!formData.direccion)
      newErrors.direccion = "La dirección es obligatoria";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    try {
      setLoading(true);

      // Preparar datos para envío (sin confirmPassword)
      const { confirmPassword, ...dataToSend } = formData;

      const result = await registrarUsuario(dataToSend);
      setRegistroExitoso(true);
    } catch (error) {
      setErrors((prev) => ({
        ...prev,
        general: error.message,
      }));
    } finally {
      setLoading(false);
    }
  };

  const handleIniciarSesion = () => {
    navigate("/login");
  };

  if (registroExitoso) {
    return (
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-8">
            <div className="card shadow border-success">
              <div className="card-header bg-success text-white text-center">
                <h3 className="card-title mb-0">
                  <i className="fas fa-check-circle me-2"></i>
                  ¡Registro Exitoso!
                </h3>
              </div>
              <div className="card-body text-center py-5">
                <div className="mb-4">
                  <i className="fas fa-user-check fa-4x text-success"></i>
                </div>
                <h4 className="text-success mb-4">
                  Tu cuenta ha sido creada exitosamente
                </h4>
                <p className="text-muted mb-4">
                  Ya puedes iniciar sesión con tu DNI y contraseña para agendar
                  tus citas médicas.
                </p>
                <button
                  className="btn btn-primary btn-lg"
                  onClick={handleIniciarSesion}
                >
                  <i className="fas fa-sign-in-alt me-2"></i>
                  Iniciar Sesión
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="container mt-4">
      <div className="row justify-content-center">
        <div className="col-lg-10">
          <div className="card shadow">
            <div className="card-header card-primary-title">
              <h4 className="card-title mb-0">
                <i className="fas fa-user-plus me-2"></i>
                Registro de Paciente - Clínica Médica
              </h4>
            </div>
            <div className="card-body">
              {errors.general && (
                <div className="alert alert-danger" role="alert">
                  <i className="fas fa-exclamation-triangle me-2"></i>
                  {errors.general}
                </div>
              )}

              <form onSubmit={handleSubmit}>
                {/* Sección DNI */}
                <div className="row mb-4">
                  <div className="col-12">
                    <div className="card border-info">
                      <div className="card-header bg-info text-white">
                        <h5 className="mb-0">
                          <i className="fas fa-id-card me-2"></i>
                          Validación de Identidad
                        </h5>
                      </div>
                      <div className="card-body">
                        <div className="row">
                          <div className="col-md-6">
                            <label htmlFor="dni" className="form-label">
                              DNI <span className="text-danger">*</span>
                            </label>
                            <div className="input-group">
                              <input
                                type="text"
                                className={`form-control ${
                                  errors.dni
                                    ? "is-invalid"
                                    : dniValidado
                                    ? "is-valid"
                                    : ""
                                }`}
                                id="dni"
                                name="dni"
                                value={formData.dni}
                                onChange={handleInputChange}
                                placeholder="12345678"
                                maxLength="8"
                                disabled={dniValidado}
                              />
                              <button
                                type="button"
                                className="btn btn-outline-primary"
                                onClick={handleValidarDni}
                                disabled={
                                  validandoDni || dniValidado || !formData.dni
                                }
                              >
                                {validandoDni ? (
                                  <>
                                    <span className="spinner-border spinner-border-sm me-2"></span>
                                    Validando...
                                  </>
                                ) : dniValidado ? (
                                  <>
                                    <i className="fas fa-check me-2"></i>
                                    Validado
                                  </>
                                ) : (
                                  <>
                                    <i className="fas fa-search me-2"></i>
                                    Validar
                                  </>
                                )}
                              </button>
                            </div>
                            {errors.dni && (
                              <div className="invalid-feedback d-block">
                                {errors.dni}
                              </div>
                            )}
                            {dniValidado && (
                              <div className="text-success mt-2">
                                <i className="fas fa-check-circle me-2"></i>
                                DNI validado correctamente
                              </div>
                            )}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                {/* Datos Personales */}
                <div className="row mb-4">
                  <div className="col-12">
                    <h5 className="text-primary mb-3">
                      <i className="fas fa-user me-2"></i>
                      Datos Personales
                    </h5>
                  </div>

                  <div className="col-md-4 mb-3">
                    <label htmlFor="nombre" className="form-label">
                      Nombres <span className="text-danger">*</span>
                    </label>
                    <input
                      type="text"
                      className={`form-control ${
                        errors.nombre ? "is-invalid" : ""
                      }`}
                      id="nombre"
                      name="nombre"
                      value={formData.nombre}
                      onChange={handleInputChange}
                      readOnly={dniValidado}
                    />
                    {errors.nombre && (
                      <div className="invalid-feedback">{errors.nombre}</div>
                    )}
                  </div>

                  <div className="col-md-4 mb-3">
                    <label htmlFor="apellidoPaterno" className="form-label">
                      Apellido Paterno <span className="text-danger">*</span>
                    </label>
                    <input
                      type="text"
                      className={`form-control ${
                        errors.apellidoPaterno ? "is-invalid" : ""
                      }`}
                      id="apellidoPaterno"
                      name="apellidoPaterno"
                      value={formData.apellidoPaterno}
                      onChange={handleInputChange}
                      readOnly={dniValidado}
                    />
                    {errors.apellidoPaterno && (
                      <div className="invalid-feedback">
                        {errors.apellidoPaterno}
                      </div>
                    )}
                  </div>

                  <div className="col-md-4 mb-3">
                    <label htmlFor="apellidoMaterno" className="form-label">
                      Apellido Materno <span className="text-danger">*</span>
                    </label>
                    <input
                      type="text"
                      className={`form-control ${
                        errors.apellidoMaterno ? "is-invalid" : ""
                      }`}
                      id="apellidoMaterno"
                      name="apellidoMaterno"
                      value={formData.apellidoMaterno}
                      onChange={handleInputChange}
                      readOnly={dniValidado}
                    />
                    {errors.apellidoMaterno && (
                      <div className="invalid-feedback">
                        {errors.apellidoMaterno}
                      </div>
                    )}
                  </div>

                  <div className="col-md-6 mb-3">
                    <label htmlFor="fechaNacimiento" className="form-label">
                      Fecha de Nacimiento <span className="text-danger">*</span>
                    </label>
                    <input
                      type="date"
                      className={`form-control ${
                        errors.fechaNacimiento ? "is-invalid" : ""
                      }`}
                      id="fechaNacimiento"
                      name="fechaNacimiento"
                      value={formData.fechaNacimiento}
                      onChange={handleInputChange}
                      max={new Date().toISOString().split("T")[0]}
                    />
                    {errors.fechaNacimiento && (
                      <div className="invalid-feedback">
                        {errors.fechaNacimiento}
                      </div>
                    )}
                  </div>

                  <div className="col-md-6 mb-3">
                    <label htmlFor="genero" className="form-label">
                      Género <span className="text-danger">*</span>
                    </label>
                    <select
                      className={`form-select ${
                        errors.genero ? "is-invalid" : ""
                      }`}
                      id="genero"
                      name="genero"
                      value={formData.genero}
                      onChange={handleInputChange}
                    >
                      <option value="">Seleccionar género</option>
                      <option value="Masculino">Masculino</option>
                      <option value="Femenino">Femenino</option>
                    </select>
                    {errors.genero && (
                      <div className="invalid-feedback">{errors.genero}</div>
                    )}
                  </div>
                </div>

                {/* Datos de Contacto */}
                <div className="row mb-4">
                  <div className="col-12">
                    <h5 className="text-primary mb-3">
                      <i className="fas fa-address-book me-2"></i>
                      Datos de Contacto
                    </h5>
                  </div>

                  <div className="col-md-6 mb-3">
                    <label htmlFor="telefono" className="form-label">
                      Teléfono <span className="text-danger">*</span>
                    </label>
                    <input
                      type="tel"
                      className={`form-control ${
                        errors.telefono ? "is-invalid" : ""
                      }`}
                      id="telefono"
                      name="telefono"
                      value={formData.telefono}
                      onChange={handleInputChange}
                      placeholder="987654321"
                      maxLength="9"
                    />
                    {errors.telefono && (
                      <div className="invalid-feedback">{errors.telefono}</div>
                    )}
                  </div>

                  <div className="col-md-6 mb-3">
                    <label htmlFor="email" className="form-label">
                      Correo Electrónico <span className="text-danger">*</span>
                    </label>
                    <input
                      type="email"
                      className={`form-control ${
                        errors.email ? "is-invalid" : ""
                      }`}
                      id="email"
                      name="email"
                      value={formData.email}
                      onChange={handleInputChange}
                      placeholder="ejemplo@correo.com"
                    />
                    {errors.email && (
                      <div className="invalid-feedback">{errors.email}</div>
                    )}
                  </div>
                </div>

                {/* Datos de Dirección */}
                <div className="row mb-4">
                  <div className="col-12">
                    <h5 className="text-primary mb-3">
                      <i className="fas fa-map-marker-alt me-2"></i>
                      Dirección
                    </h5>
                  </div>

                  <div className="col-md-4 mb-3">
                    <label htmlFor="departamento" className="form-label">
                      Departamento <span className="text-danger">*</span>
                    </label>
                    <select
                      className={`form-select ${
                        errors.departamento ? "is-invalid" : ""
                      }`}
                      id="departamento"
                      name="departamento"
                      value={formData.departamento}
                      onChange={handleInputChange}
                    >
                      <option value="">Seleccionar departamento</option>
                      {departamentos.map((dept) => (
                        <option key={dept} value={dept}>
                          {dept}
                        </option>
                      ))}
                    </select>
                    {errors.departamento && (
                      <div className="invalid-feedback">
                        {errors.departamento}
                      </div>
                    )}
                  </div>

                  <div className="col-md-4 mb-3">
                    <label htmlFor="provincia" className="form-label">
                      Provincia <span className="text-danger">*</span>
                    </label>
                    <input
                      type="text"
                      className={`form-control ${
                        errors.provincia ? "is-invalid" : ""
                      }`}
                      id="provincia"
                      name="provincia"
                      value={formData.provincia}
                      onChange={handleInputChange}
                      placeholder="Ingrese la provincia"
                    />
                    {errors.provincia && (
                      <div className="invalid-feedback">{errors.provincia}</div>
                    )}
                  </div>

                  <div className="col-md-4 mb-3">
                    <label htmlFor="distrito" className="form-label">
                      Distrito <span className="text-danger">*</span>
                    </label>
                    <input
                      type="text"
                      className={`form-control ${
                        errors.distrito ? "is-invalid" : ""
                      }`}
                      id="distrito"
                      name="distrito"
                      value={formData.distrito}
                      onChange={handleInputChange}
                      placeholder="Ingrese el distrito"
                    />
                    {errors.distrito && (
                      <div className="invalid-feedback">{errors.distrito}</div>
                    )}
                  </div>

                  <div className="col-12 mb-3">
                    <label htmlFor="direccion" className="form-label">
                      Dirección Completa <span className="text-danger">*</span>
                    </label>
                    <textarea
                      className={`form-control ${
                        errors.direccion ? "is-invalid" : ""
                      }`}
                      id="direccion"
                      name="direccion"
                      value={formData.direccion}
                      onChange={handleInputChange}
                      rows="3"
                      placeholder="Ingrese su dirección completa"
                    ></textarea>
                    {errors.direccion && (
                      <div className="invalid-feedback">{errors.direccion}</div>
                    )}
                  </div>
                </div>

                {/* Datos de Seguridad */}
                <div className="row mb-4">
                  <div className="col-12">
                    <h5 className="text-primary mb-3">
                      <i className="fas fa-lock me-2"></i>
                      Datos de Seguridad
                    </h5>
                  </div>

                  <div className="col-md-6 mb-3">
                    <label htmlFor="password" className="form-label">
                      Contraseña <span className="text-danger">*</span>
                    </label>
                    <input
                      type="password"
                      className={`form-control ${
                        errors.password ? "is-invalid" : ""
                      }`}
                      id="password"
                      name="password"
                      value={formData.password}
                      onChange={handleInputChange}
                      placeholder="Mínimo 6 caracteres"
                    />
                    {errors.password && (
                      <div className="invalid-feedback">{errors.password}</div>
                    )}
                  </div>

                  <div className="col-md-6 mb-3">
                    <label htmlFor="confirmPassword" className="form-label">
                      Confirmar Contraseña{" "}
                      <span className="text-danger">*</span>
                    </label>
                    <input
                      type="password"
                      className={`form-control ${
                        errors.confirmPassword ? "is-invalid" : ""
                      }`}
                      id="confirmPassword"
                      name="confirmPassword"
                      value={formData.confirmPassword}
                      onChange={handleInputChange}
                      placeholder="Repita la contraseña"
                    />
                    {errors.confirmPassword && (
                      <div className="invalid-feedback">
                        {errors.confirmPassword}
                      </div>
                    )}
                  </div>
                </div>

                {/* Botones */}
                <div className="row">
                  <div className="col-12">
                    <div className="d-flex justify-content-between">
                      <button
                        type="button"
                        className="btn btn-outline-secondary btn-lg"
                        onClick={() => navigate("/login")}
                      >
                        <i className="fas fa-arrow-left me-2"></i>
                        Ya tengo cuenta
                      </button>

                      <button
                        type="submit"
                        className="btn btn-primary btn-lg"
                        disabled={loading || !dniValidado}
                      >
                        {loading ? (
                          <>
                            <span className="spinner-border spinner-border-sm me-2"></span>
                            Registrando...
                          </>
                        ) : (
                          <>
                            <i className="fas fa-user-plus me-2"></i>
                            Registrar Cuenta
                          </>
                        )}
                      </button>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
