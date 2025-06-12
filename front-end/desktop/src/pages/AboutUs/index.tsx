import { Header } from "../../components/Header";
import Matheus from "../../assets/MatheusAlencar.jpeg";
import Felipe from "../../assets/FelipeFerreira.jpeg";
import Maria from "../../assets/MariaRita.png";
import "./style.css";

export function AboutUsPage(){
    return(
        <>
            <Header></Header>
            <main>
                <div className="title">
                    <h1><span className="red">Sozinho</span>, se vai <i>rápido</i>. <span className="red">Acompanhado</span>, se vai <i>longe</i>.</h1>
                </div>
                <div className="subtitle">
                    <h2>Time de desenvolvimento do <i>RPG Player Pack</i>.</h2>
                </div>
                <div className="devs">
                    <div className="dev">
                        <div className="dev-img">
                            <img src={Felipe} width={200}/>
                        </div>
                        <div className="dev-name">
                            <h4>Felipe Ferreira</h4>
                        </div>
                        <div className="dev-description">
                            <p>Back-end e Integração.</p>
                        </div>
                    </div>
                    <div className="dev">
                        <div className="dev-img">
                            <img src={Maria} width={200}/>
                        </div>
                        <div className="dev-name">
                           <h4>Maria Rita</h4>
                        </div>
                        <div className="dev-description">
                            <p>Back-end.</p>
                        </div>
                    </div>
                    <div className="dev">
                        <div className="dev-img">
                            <img src={Matheus} width={200}/>
                        </div>
                        <div className="dev-name">
                            <h4>Matheus de Alencar</h4>
                        </div>
                        <div className="dev-description">
                            <p>Front-end e Design.</p>
                        </div>
                    </div>
                </div>
            </main>
        </>
    )
}