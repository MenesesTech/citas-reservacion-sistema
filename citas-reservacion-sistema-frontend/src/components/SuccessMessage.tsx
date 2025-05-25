import { FaCheckCircle } from "react-icons/fa";

// components/SuccessMessage.tsx

const SuccessMessage = ({ onReset }: { onReset: () => void }) => {
  const handleLogin = () => {
    window.location.href = "/login";
  };

  return (
    <div
      className="d-flex flex-column align-items-center justify-content-center p-4 rounded shadow bg-white"
      style={{ maxWidth: 400, margin: "2rem auto" }}
    >
      <FaCheckCircle size={56} className="text-success mb-3" />
      <h4 className="mb-2 text-success fw-bold">¡Registro exitoso!</h4>
      <p className="mb-4 text-secondary text-center">
        El usuario fue registrado correctamente en el sistema.
        <br />
        ¡Bienvenido!
      </p>
      <button className="btn btn-success px-4 py-2" onClick={handleLogin}>
        Iniciar Sesión
      </button>
    </div>
  );
};

export default SuccessMessage;
