import React from "react";

interface Option {
  value: string;
  label: string;
}

interface Props {
  label: string;
  value: string;
  onChange: (value: string) => void;
  options: Option[];
  disabled?: boolean;
}

export function SelectField({
  label,
  value,
  onChange,
  options,
  disabled = false,
}: Props) {
  return (
    <div className="mb-3">
      <label className="form-label">{label}</label>
      <select
        className="form-select"
        value={value}
        onChange={(e) => onChange(e.target.value)}
        disabled={disabled}
      >
        <option value="">Seleccione una opción</option>
        {options.map((opt) => (
          <option key={opt.value} value={opt.value}>
            {opt.label}
          </option>
        ))}
      </select>
    </div>
  );
}
