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
                <input type="number"/>/<input type="number"/><label>Pontos de vida atuais</label>
            </div>
            <div className="c-3">
                <input type="number"/><label>PV temp</label>
            </div>
            <div className="c-4">
                <div className="life-dice">
                    <input type="number"/>/
                    <select>
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