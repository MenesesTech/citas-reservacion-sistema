import "./sidebar.css";
import { useNavigate } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "../../../context/AuthContext";
export const SidebarComponent = () => {
  const { logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    logout(); // Limpia el estado del contexto y localStorage
    navigate("/login");
  };
  return (
    <>
      <div class="area"></div>
      <nav class="main-menu">
        <ul>
          <li className="mb-3">
            <a href="/home">
              <i class="ri-home-4-line ri"></i>
              <span class="nav-text">HOME</span>
            </a>
          </li>
          <li className="mb-3">
            <a href="/crear-cita-medica">
              <i class="ri-calendar-event-fill ri"></i>
              <span class="nav-text">SOLICITAR CITA</span>
            </a>
          </li>
          <li className="mb-3">
            <a href="">
              <i class="ri-list-check ri"></i>
              <span class="nav-text">MIS CITAS</span>
            </a>
          </li>
          <li className="mb-3">
            <a href="/Mi-cuenta">
              <i class="ri-user-line ri"></i>
              <span class="nav-text">MI CUENTA</span>
            </a>
          </li>
        </ul>

        <ul class="logout">
          <li>
            <a onClick={handleLogout}>
              <i class="ri-logout-box-r-line ri"></i>
              <span class="nav-text">Logout</span>
            </a>
          </li>
        </ul>
      </nav>
    </>
  );
};
