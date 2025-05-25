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

  const handleValidate = useCallback(async () => {
    registration.clearMessages();
    const validatedData = await validation.validate(documentNumber);
    if (validatedData) {
      setUserData(validatedData);
    }
  }, [documentNumber, validation, registration]);

  const handleFieldChange = useCallback(
    (field: keyof UserData, value: string) => {
      if (!userData) return;
      setUserData({ ...userData, [field]: value });
    },
    [userData]
  );

  const handleRegister = useCallback(async () => {
    if (!userData) return;
    const success = await registration.register(userData);
    if (success) {
      setRegistered(true);
    }
  }, [userData, registration]);

  const handleReset = () => {
    setUserData(null);
    setDocumentNumber("");
    setRegistered(false);
    registration.clearMessages();
  };

  const currentStep = registered ? 3 : userData ? 2 : 1;

  return (
    <>
      <Header />
      <div className="d-flex justify-content-center">
        <div
          className="card shadow p-4 mb-3 bg-body rounded border-0"
          style={{ maxWidth: "600px", width: "100%" }}
        >
          <div className="card-title text-center mb-4">
            <h3>CREAR UNA CUENTA</h3>
          </div>

          <StepIndicator currentStep={currentStep} steps={steps} />

          {currentStep === 1 && (
            <DniInput
              value={documentNumber}
              onChange={setDocumentNumber}
              onValidate={handleValidate}
              loading={validation.loading}
            />
          )}

          {validation.error && (
            <AlertMessage type="danger" message={validation.error} />
          )}

          {currentStep === 2 && userData && (
            <UserForm
              userData={userData}
              onFieldChange={handleFieldChange}
              onRegister={handleRegister}
              loading={registration.loading}
            />
          )}

          {registration.error && (
            <AlertMessage type="danger" message={registration.error} />
          )}

          {currentStep === 3 && (
            <>
              <SuccessMessage onReset={handleReset} />
              {registration.success && (
                <AlertMessage type="success" message={registration.success} />
              )}
            </>
          )}
        </div>
      </div>
    </>
  );
};

export default FormValidateDni;
