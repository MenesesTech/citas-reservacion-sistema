import { SidebarComponent } from "../components/sidebar/sidebar";
import { CardHome } from "../components/card-home/card-home";
import "./home.css";
import { CardOptionSection } from "../components/card-option-home/CardOptionSection";
// src/features/home/home.jsx
import { useContext } from "react";
import { AuthContext } from "../../context/AuthContext";

const nombre_clinica = "Clinica Elver Galarga";
export const HomePage = () => {
  const { usuario } = useContext(AuthContext);
  return (
    <div className="container-home-page">
      <div>
        <SidebarComponent />
      </div>

      <div
        className="bg-white"
        style={{
          display: "block",
          textAlign: "left",
          paddingTop: "1rem",
          paddingLeft: "1rem",
        }}
      >
        <h1 className="pb-3 fs-1">¡Hola, {usuario?.nombre || "Usuario"}!</h1>
        <h3 className="fs-5">{nombre_clinica} te cuida</h3>
        <div class="">
          <div class="row gx-5 pb-2">
            <div className="col ">
              <CardHome
                messagetitle="Con Vida SANNA"
                messagesubtitle="Asegura tu salud y la de tu familia"
                imagesrc="https://cde.sanna.pe/clinica-digital/home/sliderpromocional/daf3b2f1-8a0a-487d-8209-4fe87db6f877.png"
                cardColor="#4E87BE"
              />
            </div>
            <div className="col">
              <CardHome
                messagetitle="Planes de vida SANNA"
                messagesubtitle="Asegura la salud de tu familia"
                imagesrc=""
                cardColor="#83C5A2"
              />
            </div>
          </div>
        </div>
        <div>
          <CardOptionSection
            title="¿En qué podemos ayudarte?"
            options={[
              {
                label: "Solicitar citas",
                iconClass: "ri-calendar-check-line ri2",
                iconColor: "white",
                bgClass: "bg-success",
                link: "/solicitar-citas",
              },
              {
                label: "Mis citas",
                iconClass: "ri-menu-line ri2",
                iconColor: "white",
                bgClass: "bg-warning",
                link: "/mis-citas",
              },
              {
                label: "Mis exámenes",
                iconClass: "ri-folder-2-line ri2",
                iconColor: "white",
                bgClass: "bg-info",
                link: "/mis-examenes",
              },
            ]}
          />
        </div>
      </div>
    </div>
  );
};
