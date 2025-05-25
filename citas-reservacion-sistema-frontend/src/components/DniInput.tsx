interface Props {
  value: string;
  onChange: (value: string) => void;
  onValidate: () => void;
  loading: boolean;
}

export const DniInput = ({ value, onChange, onValidate, loading }: Props) => (
  <>
    <div className="mb-3">
      <label htmlFor="documentNumber" className="form-label">
        Número de DNI
      </label>
      <input
        type="text"
        className="form-control"
        id="documentNumber"
        value={value}
        onChange={(e) => onChange(e.target.value)}
        placeholder="Ingrese el número de DNI"
      />
    </div>

    <div className="d-grid mb-3">
      <button
        className="btn btn-primary btn-dni"
        onClick={onValidate}
        disabled={loading || value.length !== 8}
      >
        {loading ? "Validando..." : "Validar DNI"}
      </button>
    </div>
  </>
);
