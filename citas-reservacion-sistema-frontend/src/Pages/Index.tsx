import About from "./About"
import Login from "./Login"
import { Routes, Route } from "react-router"

export default function Auth() {
    return (
        <Routes>
            <Route path="/" element={<Login/>}/>
            <Route path="/login" element={<Login/>}/>
            <Route path="/about" element={<About/>}/>
        </Routes>
)
}