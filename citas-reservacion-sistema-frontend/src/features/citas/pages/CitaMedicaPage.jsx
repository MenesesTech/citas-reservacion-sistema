import { ButtonCita } from "../../components/Button-cita/button-cita";

export const CitaMedicaOptions = () => {
  return (
    <div className="container py-5">
      <h1 className="mb-3">
        Programa una cita virtual o presencial en nuestra red de clínicas y
        centros clínicos.
      </h1>
      <div className="mb-4 fs-5">
        Selecciona el tipo de atención de tu preferencia.
      </div>

      <div className="row g-3">
        {/* Cita presencial */}
        <div className="col-md-4">
          <div className="h-100">
            <ButtonCita
              title="Cita presencial"
              subtitle="Agenda una cita con un especialista"
              to="/crear-cita-medica/buscar-por"
            />
          </div>
        </div>

        {/* Cita virtual */}
        <div className="col-md-4">
          <div className="h-100">
            <ButtonCita
              title="Cita virtual"
              subtitle="Agenda una cita con un especialista"
              to="/crear-cita-medica/buscar-por"
            />
          </div>
        </div>

        {/* Chequeo médico */}
        <div className="col-md-4">
          <div className="h-100">
            <ButtonCita
              title="Chequeo médico"
              subtitle="Agenda una cita para tu chequeo"
              to="/crear-cita-medica/buscar-por"
            />
          </div>
        </div>
      </div>
    </div>
  );
};
