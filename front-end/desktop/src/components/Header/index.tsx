import "./style.css";
import { FaRegUserCircle } from "react-icons/fa";
import logo from "../../assets/logo.png";
import { useLocation } from "react-router-dom";

interface NavItem {
  path: string;
  label: string;
}

export function Header(){
    const location = useLocation();
    
    const navItems: NavItem[] = [
        { path: "/", label: "HOME" },
        { path: "/criar-ficha", label: "CRIAR FICHA" },
        { path: "/sobre-nos", label: "SOBRE NÓS" }
    ];

    return(
        <header>
            <div className="logo">
                <img src={logo} width={100} alt="Logo" />
            </div>

            <div className="nav-bar">
                <ul>
                {navItems.map((item) => (
                    <li key={item.path}>
                    <a
                        href={item.path}
                        className={location.pathname === item.path ? "active" : ""}
                    >
                        {item.label}
                    </a>
                    </li>
                ))}
                </ul>
            </div>

            <div className="user">
                <button className="user-button" onClick={() => window.location.href = "/user"}>
                    <FaRegUserCircle fontSize={40} />
                </button>
            </div>
            </header>
    )
}