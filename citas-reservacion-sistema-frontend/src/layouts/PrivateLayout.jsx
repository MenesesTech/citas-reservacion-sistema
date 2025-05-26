// src/layouts/PrivateLayout.jsx
import React, { useContext } from "react";
import { Navigate, Outlet } from "react-router-dom";
import { SidebarComponent } from "../features/components/sidebar/sidebar";
import { AuthContext } from "../context/AuthContext";

export const PrivateLayout = () => {
  const { usuario, loading } = useContext(AuthContext);

  if (loading) return null; // o un loader/spinner

  if (!usuario) {
    return <Navigate to="/login" replace />;
  }

  return (
    <div className="container-home-page">
      <SidebarComponent />
      <div className="main-content">
        <Outlet />
      </div>
    </div>
  );
};
