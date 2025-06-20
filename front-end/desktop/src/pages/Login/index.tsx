import { Header } from "../../components/Header";
import "./style.css";

export function LoginPage(){
    return(
        <div className="login-page">
            <Header></Header>
            <div className="login-header">
                <h2><span className="red">Acesse sua conta</span></h2>
            </div>
            <div className="login-form">
                <form className="form">
                    <div className="input-group">
                        <label htmlFor="email">E-mail:</label>
                        <input type="email" id="email" name="email" placeholder="Digite seu e-mail" required />
                    </div>

                    <div className="input-group">
                        <label htmlFor="password" >Senha:</label>
                        <input type="password" id="password" name="password" placeholder="Digite sua senha" required />
                    </div>

                    <button type="submit" className="login">Entrar</button>
                </form>
            </div>
        </div>
    )
}