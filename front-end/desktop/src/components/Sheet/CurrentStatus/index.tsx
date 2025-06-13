import "./style.css";

export function SheetCurrentStatus(){
    return(
        <div className="s-currentstatus">
            <div className="c-1">
                <div className="ca">
                    <input type="number"/><label>CA</label>
                </div>
                <div className="initiative">
                    <input type="number"/><label>Iniciativa</label>
                </div>
                <div className="movement">
                    <input type="number"/><label>Desloc.</label>
                </div>
            </div>
            <div className="c-2">
                <div className="input-group-pv">
                    <label>PV Totais</label><input type="number" className="max-pv"/>
                </div>
                <input type="number" className="current-pv"/>
                <label>Pontos de vida atuais</label>
            </div>
            <div className="c-3">
                <input type="number" className="temp-pv"/>
                <label>Pontos de vida temporários</label>
            </div>
            <div className="c-4">
                <div className="life-dice">
                    <div className="input-group-dices">
                        <label>Total</label><input type="number" className="max-dice"/>
                    </div>
                    <input type="number" className="number-dice"/>
                    <select className="type-dice">
                        <option>d4</option><label>Dado de vida</label>
                    </select>
                </div>
                <div className="saving-death">
                    <label>Sucessos</label>
                    <input type="checkbox"/>
                    <input type="checkbox"/>
                    <input type="checkbox"/>
                    <br/>
                    <label>Fracassos</label>
                    <input type="checkbox"/>
                    <input type="checkbox"/>
                    <input type="checkbox"/>
                </div>
            </div>
        </div>
    )
}