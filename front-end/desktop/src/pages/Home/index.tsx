import "./styles.css";
import { Header } from "../../components/Header"
import { LandingSlider } from "../../components/Slider"

export function HomePage(){
    return(
        <>
            <Header></Header>
            <main>
                <div className="title">
                    <h1><i>Prepare a sua</i> <span className="red">MOCHILA</span>, <i>e comece<br/>uma</i> <span className="red">AVENTURA</span>!</h1>
                </div>
                <div className="slider">
                    <LandingSlider></LandingSlider>
                </div>
                <div className="cta">
                    <button className="btn-cta" onClick={() => window.location.href = "/criar-ficha"}>
                        CRIAR FICHA
                    </button>
                </div>
            </main>
        </>
    )
}