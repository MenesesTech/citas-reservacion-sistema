import { Routes, Route } from "react-router";
import Dashboard from "./Dashboard.tsx";

export default function LoggedInFlow() {
    return (
            <Routes>
                <Route path="/" element={<Dashboard/>}/>
            </Routes>
    )
}