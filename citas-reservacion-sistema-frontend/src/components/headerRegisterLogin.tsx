const Header = () => {
  return (
    <header className="py-3 mb-4 border-bottom bg-light">
      <div className="container d-flex flex-wrap justify-content-center">
        <a
          href="/"
          className="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none"
        >
          {/* Puedes reemplazar este SVG por un ícono de Bootstrap real si lo usas con icon library */}
          <svg
            className="w-[48px] h-[48px] text-gray-800 dark:text-white"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            width="32"
            height="32"
            fill="currentColor"
            viewBox="0 0 24 24"
          >
            <path
              fillRule="evenodd"
              d="M18 5.05h1a2 2 0 0 1 2 2v2H3v-2a2 2 0 0 1 2-2h1v-1a1 1 0 1 1 2 0v1h3v-1a1 1 0 1 1 2 0v1h3v-1a1 1 0 1 1 2 0v1Zm-15 6v8a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-8H3ZM11 18a1 1 0 1 0 2 0v-1h1a1 1 0 1 0 0-2h-1v-1a1 1 0 1 0-2 0v1h-1a1 1 0 1 0 0 2h1v1Z"
              clipRule="evenodd"
            />
          </svg>
          <span className="fs-5 ms-3 align-middle">Clinica Santa</span>
        </a>
      </div>
    </header>
  );
};

export default Header;
