import "./style.css";

export function SheetCurrentStatus({register}: any){
    return(
        <div className="s-currentstatus">
            <div className="c-1">
                <div className="ca">
                    <input type="number" {...register('currentstatus.ca')}/><label>CA</label>
                </div>
                <div className="initiative">
                    <input type="number" {...register('currentstatus.init')}/><label>Iniciativa</label>
                </div>
                <div className="movement">
                    <input type="number" {...register('currentstatus.desloc')}/><label>Desloc.</label>
                </div>
            </div>
            <div className="c-2">
                <div className="input-group-pv">
                    <label>PV Totais</label><input type="number" className="max-pv" {...register('currentstatus.pvmax')}/>
                </div>
                <input type="number" className="current-pv" {...register('currentstatus.pv')}/>
                <label>Pontos de vida atuais</label>
            </div>
            <div className="c-3">
                <input type="number" className="temp-pv" {...register('currentstatus.pvtemp')}/>
                <label>Pontos de vida temporários</label>
            </div>
            <div className="c-4">
                <div className="life-dice">
                    <div className="input-group-dices">
                        <label>Total</label><input type="number" className="max-dice" {...register('currentstatus.maxdices')}/>
                    </div>
                    <div className="input-type-dices">
                        <input type="number" className="number-dice" {...register('currentstatus.dices')}/>
                        <select className="type-dice" {...register('currentstatus.typedice')}>
                            <option value={6}>d6</option>
                            <option value={8}>d8</option>
                            <option value={10}>d10</option>
                            <option value={12}>d12</option>
                        </select>
                    </div>
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