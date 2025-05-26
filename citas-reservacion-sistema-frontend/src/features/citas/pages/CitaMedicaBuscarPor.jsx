import { ButtonCita } from "../../components/Button-cita/button-cita";

export const CitaMedicaBuscarPor = () => {
  return (
    <div className="container py-5">
      <h1 className="mb-3">¿Cómo deseas agendar?</h1>
      <div className="mb-4 fs-5">
        Elige una opción para buscar disponibilidad.
      </div>

      <div className="row g-3 justify-content-center">
        {/* Buscar por especialidad */}
        <div className="col-md-6 col-lg-4">
          <div className="h-100">
            <ButtonCita
              title="Búsqueda por especialidad"
              subtitle="Si deseas ver la lista de especialistas"
              to="/crear-cita-medica/buscar-por/especialidad/paso-1"
            />
          </div>
        </div>

        {/* Buscar por médico */}
        <div className="col-md-6 col-lg-4">
          <div className="h-100">
            <ButtonCita
              title="Búsqueda por médico"
              subtitle="Si conoces el nombre de tu doctor"
              to="/crear-cita-medica/medico"
            />
          </div>
        </div>
      </div>
    </div>
  );
};
