import { useState, useCallback } from "react";
import { DniInput } from "../components/DniInput";
import AlertMessage from "../components/AlertMessage";
import { UserForm } from "../components/FormRegistroValidarDni";
import { useValidation } from "../hooks/useValidation";
import { useRegistration } from "../hooks/useRegistration";
import type { UserData } from "../types/UserData";
import StepIndicator from "../components/StepIndicator";
import SuccessMessage from "../components/SuccessMessage";
import Header from "../components/headerRegisterLogin";

const steps = ["Validar DNI", "Registrar Usuario", "Completado"];

const FormValidateDni = () => {
  const [documentNumber, setDocumentNumber] = useState("");
  const [userData, setUserData] = useState<UserData | null>(null);
  const [registered, setRegistered] = useState(false);

  const validation = useValidation();
  const registration = useRegistration();

  // Validar DNI con la API
  const handleValidate = useCallback(async () => {
    registration.clearMessages();
    if (documentNumber.length !== 8) {
      validation.setError("El DNI debe tener 8 dígitos");
      return;
    }
    try {
      const validatedData = await validation.validate(documentNumber);
      if (validatedData) {
        setUserData(validatedData);
      } else {
        validation.setError("DNI no encontrado o inválido");
      }
    } catch (error) {
      validation.setError("Error al validar DNI. Intente nuevamente.");
    }
  }, [documentNumber, validation, registration]);

  // Manejar cambios en el formulario del usuario (campos adicionales)
  const handleFieldChange = useCallback(
    (field: keyof UserData, value: string) => {
      if (!userData) return;
      setUserData({ ...userData, [field]: value });
    },
    [userData]
  );

  // Registrar usuario
  const handleRegister = useCallback(async () => {
    if (!userData) return;
    const success = await registration.register(userData);
    if (success) {
      setRegistered(true);
    }
  }, [userData, registration]);

  // Resetear formulario y estado
  const handleReset = () => {
    setUserData(null);
    setDocumentNumber("");
    setRegistered(false);
    registration.clearMessages();
    validation.clearError();
  };

  // Determinar paso actual del formulario
  const currentStep = registered ? 3 : userData ? 2 : 1;

  return (
    <>
      <Header />
      <div
        style={{
          minHeight: "100vh",
          background: "linear-gradient(135deg, #e0f7fa 0%, #ffffff 100%)",
          paddingTop: "60px",
          paddingBottom: "40px",
        }}
      >
        <div
          className="d-flex justify-content-center align-items-center"
          style={{ minHeight: "calc(80vh - 60px)" }}
        >
          <div
            className="card shadow-lg p-5 bg-white rounded-4 border-0"
            style={{
              maxWidth: "520px",
              width: "100%",
              borderTop: "8px solid #26c6da",
              boxShadow: "0 8px 32px 0 rgba(38,198,218,0.15)",
              background: "rgba(255,255,255,0.95)",
            }}
          >
            <div className="text-center mb-4">
              <h3
                style={{ color: "#00838f", fontWeight: 700, letterSpacing: 1 }}
              >
                Registro de Paciente
              </h3>
              <p className="text-muted" style={{ fontSize: 16 }}>
                Bienvenido, por favor complete el proceso para crear su cuenta.
              </p>
            </div>

            <StepIndicator currentStep={currentStep} steps={steps} />

            <div className="mt-4">
              {/* Paso 1: Validar DNI */}
              {currentStep === 1 && (
                <DniInput
                  value={documentNumber}
                  onChange={setDocumentNumber}
                  onValidate={handleValidate}
                  loading={validation.loading}
                />
              )}

              {/* Mostrar error validación DNI */}
              {validation.error && (
                <AlertMessage type="danger" message={validation.error} />
              )}

              {/* Paso 2: Formulario usuario */}
              {currentStep === 2 && userData && (
                <UserForm
                  userData={userData}
                  onFieldChange={handleFieldChange}
                  onRegister={handleRegister}
                  loading={registration.loading}
                />
              )}

              {/* Mostrar error registro */}
              {registration.error && (
                <AlertMessage type="danger" message={registration.error} />
              )}

              {/* Paso 3: Éxito */}
              {currentStep === 3 && (
                <>
                  <SuccessMessage onReset={handleReset} />
                  {registration.success && (
                    <AlertMessage
                      type="success"
                      message={registration.success}
                    />
                  )}
                </>
              )}
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default FormValidateDni;
