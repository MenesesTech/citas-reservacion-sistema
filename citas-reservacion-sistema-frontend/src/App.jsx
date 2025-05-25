import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import RegisterForm from "./pages/RegistroUsuarioPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/Register" element={<RegisterForm />}></Route>
      </Routes>
    </Router>
  );
}

export default App;
