import "./style.css";

export function SheetStatus(){
    return(
        <div className="s-status">
            <ul>
                <li className="first-status">
                    <label>FORÇA</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number"/>
                </li>
                <li>
                    <label>DESTREZA</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number"/>
                </li>
                <li>
                    <label>CONSTITUIÇÃO</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number"/>
                </li>
                <li>
                    <label>INTELIGÊNCIA</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number"/>
                </li>
                <li>
                    <label>SABEDORIA</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number"/>
                </li>
                <li>
                    <label>CARISMA</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number"/>
                </li>
            </ul>
        </div>
    );
}