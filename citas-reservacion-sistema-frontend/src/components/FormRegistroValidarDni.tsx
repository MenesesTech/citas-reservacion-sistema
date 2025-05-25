import type { UserData } from "../types/UserData";
import InputField from "./InputField";
import ReadOnlyField from "./ReadOnlyField";
import { SelectField } from "./SelectField";

interface Props {
  userData: UserData;
  onFieldChange: (field: keyof UserData, value: string) => void;
  onRegister: () => void;
  loading: boolean;
}

export function UserForm({
  userData,
  onFieldChange,
  onRegister,
  loading,
}: Props) {
  const genderOptions = [
    { value: "M", label: "Masculino" },
    { value: "F", label: "Femenino" },
  ];

  return (
    <>
      {/* Campos del DNI (solo lectura) */}
      <ReadOnlyField label="DNI" value={userData.dni} />
      <ReadOnlyField label="Nombre(s)" value={userData.nombre} />
      <ReadOnlyField
        label="Apellido Paterno"
        value={userData.apellidoPaterno}
      />
      <ReadOnlyField
        label="Apellido Materno"
        value={userData.apellidoMaterno}
      />

      {/* Campos adicionales */}
      <InputField
        label="Fecha de Nacimiento"
        type="date"
        value={userData.fechaNacimiento}
        onChange={(value) => onFieldChange("fechaNacimiento", value)}
      />

      <InputField
        label="Teléfono"
        type="tel"
        value={userData.telefono}
        onChange={(value) => onFieldChange("telefono", value)}
        placeholder="Ingrese su teléfono"
      />

      <InputField
        label="Email"
        type="email"
        value={userData.email}
        onChange={(value) => onFieldChange("email", value)}
        placeholder="Ingrese su correo electrónico"
      />

      <SelectField
        label="Género"
        value={userData.genero}
        onChange={(value) => onFieldChange("genero", value)}
        options={genderOptions}
      />

      <InputField
        label="Departamento"
        value={userData.departamento}
        onChange={(value) => onFieldChange("departamento", value)}
        placeholder="Ingrese su departamento"
      />

      <InputField
        label="Provincia"
        value={userData.provincia}
        onChange={(value) => onFieldChange("provincia", value)}
        placeholder="Ingrese su provincia"
      />

      <InputField
        label="Distrito"
        value={userData.distrito}
        onChange={(value) => onFieldChange("distrito", value)}
        placeholder="Ingrese su distrito"
      />

      <InputField
        label="Dirección"
        value={userData.direccion}
        onChange={(value) => onFieldChange("direccion", value)}
        placeholder="Ingrese su dirección"
      />

      <InputField
        label="Contraseña"
        type="password"
        value={userData.password}
        onChange={(value) => onFieldChange("password", value)}
        placeholder="Ingrese una contraseña segura"
      />

      <div className="d-grid mb-3">
        <button
          className="btn btn-success"
          onClick={onRegister}
          disabled={loading}
        >
          {loading ? "Registrando..." : "Registrar Usuario"}
        </button>
      </div>
    </>
  );
}
