import { Link } from "react-router-dom";
import "./card-options.css";

export const CardOptionSection = ({ title, options }) => {
  return (
    <div className="mt-5">
      <h5 className="mb-4 fw-semibold">{title}</h5>
      <div className="card-option-grid">
        {options.map((opt, idx) => (
          <Link
            to={opt.link}
            key={idx}
            className={`card-option ${opt.bgClass}`}
          >
            <i className={`${opt.iconClass} ${opt.iconColor}`}></i>
            <span className="label">{opt.label}</span>
          </Link>
        ))}
      </div>
    </div>
  );
};
