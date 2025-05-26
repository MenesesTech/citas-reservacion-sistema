export const CalendarCita = ({ scheduleData, selectedSlot, onSelectSlot }) => {
  return (
    <div className="container my-4">
      <h2 className="text-center mb-4">Selecciona un Horario</h2>

      <div
        className="d-flex flex-row flex-nowrap overflow-auto py-3"
        style={{ scrollbarWidth: "thin" }}
      >
        {scheduleData.length === 0 ? (
          <p>No hay horarios disponibles.</p>
        ) : (
          scheduleData.map((day, dayIndex) => (
            <div
              key={dayIndex}
              className="me-3"
              style={{ minWidth: "240px", flex: "0 0 auto" }}
            >
              <div className="card h-100 shadow-sm">
                <div className="card-header bg-light">
                  <h6 className="fw-bold mb-1">{day.dayName}</h6>
                  <small className="text-muted d-flex align-items-center">
                    <CalendarIcon />
                    {day.date}
                  </small>
                </div>
                <div className="card-body">
                  {day.timeSlots.length > 0 ? (
                    day.timeSlots.map((time, timeIndex) => (
                      <div className="form-check mb-2" key={timeIndex}>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="appointmentTime"
                          id={`time-${day.date}-${time.replace(":", "")}`}
                          value={`${day.date}-${time}`}
                          checked={selectedSlot === `${day.date}-${time}`}
                          onChange={() => onSelectSlot(day.date, time)}
                        />
                        <label
                          className="form-check-label w-100"
                          htmlFor={`time-${day.date}-${time.replace(":", "")}`}
                        >
                          {time}
                        </label>
                      </div>
                    ))
                  ) : (
                    <p className="text-muted text-center small mt-3">
                      No hay horarios disponibles.
                    </p>
                  )}
                </div>
              </div>
            </div>
          ))
        )}
      </div>

      {selectedSlot && (
        <div className="alert alert-success mt-4 text-center" role="alert">
          Horario seleccionado: {selectedSlot.split("-")[0]} a las{" "}
          {selectedSlot.split("-")[1]}
        </div>
      )}
    </div>
  );
};
