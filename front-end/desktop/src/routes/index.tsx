import { Routes, Route } from "react-router-dom";
import { HomePage } from "../pages/Home";
import { PackPage } from "../pages/Pack";
import { LoginPage } from "../pages/Login";
import { SignupPage } from "../pages/Signup";
import { CreateSheetPage } from "../pages/CreateSheet";

export function AppRouter(){
    return (
        <Routes>
            <Route path="/" element={<HomePage/>}/>
            <Route path="/criar-ficha" element={<CreateSheetPage/>}/>
            <Route path="/pack" element={<PackPage/>}/>
            <Route path="/login" element={<LoginPage/>} />
            <Route path="/signup" element={<SignupPage/>} />
        </Routes>
    );
}