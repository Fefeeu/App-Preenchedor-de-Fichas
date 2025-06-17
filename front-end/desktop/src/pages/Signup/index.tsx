import "./style.css";
import { Header } from "../../components/Header";

export function SignupPage(){
    return(
        <div className="signup-page">
            <Header></Header>
            <div className="signup-header">
                <h2><span className="red">Crie sua conta</span></h2>
            </div>
            <div className="signup-form">
                <form className="form">
                    <div className="input-group">
                        <label htmlFor="username">Nome de Aventureiro:</label>
                        <input type="text" id="username" name="username" minLength={3} maxLength={32} placeholder="Digite seu nome de aventureiro" required />
                    </div>

                    <div className="input-group">
                        <label htmlFor="email">E-mail:</label>
                        <input type="email" id="email" name="email" minLength={6} maxLength={64} placeholder="Digite seu melhor e-mail" required />
                    </div>

                    <div className="input-group">
                        <label htmlFor="password">Senha:</label>
                        <input type="password" id="password" name="password" minLength={8} maxLength={32} placeholder="Digite sua senha secreta" required />
                    </div>

                    <button type="submit" className="register">Registrar</button>
                </form>
            </div>
        </div>
    )
}