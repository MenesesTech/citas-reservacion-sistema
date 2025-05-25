import React from "react";

const Header = () => {
  return (
    <header className="py-4 shadow-sm bg-white border-bottom">
      <div className="container d-flex flex-wrap align-items-center justify-content-between">
        <a
          href="/"
          className="d-flex align-items-center text-primary text-decoration-none"
        >
          {/* Medical cross with heart icon */}
          <svg
            width="56"
            height="56"
            viewBox="0 0 56 56"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
            style={{ marginRight: 16 }}
          >
            <circle cx="28" cy="28" r="28" fill="#E3F6FC" />
            <rect x="23" y="12" width="10" height="32" rx="5" fill="#00BCD4" />
            <rect x="12" y="23" width="32" height="10" rx="5" fill="#00BCD4" />
            <path
              d="M28 38c-3-2.5-8-5.5-8-10a5 5 0 0 1 8-4 5 5 0 0 1 8 4c0 4.5-5 7.5-8 10z"
              fill="#FF6F91"
              stroke="#fff"
              strokeWidth="2"
            />
          </svg>
          <span
            className="fs-3 fw-bold align-middle"
            style={{ letterSpacing: 1 }}
          >
            Clínica Elber Galarga
          </span>
        </a>
      </div>
    </header>
  );
};

export default Header;
