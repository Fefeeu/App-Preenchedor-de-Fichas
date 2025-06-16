import "./style.css";

export function SheetStatus({register}: any){
    return(
        <div className="s-status">
            <ul>
                <li className="first-status">
                    <label>FORÇA</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number" {...register('status.for')}/>
                </li>
                <li>
                    <label>DESTREZA</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number" {...register('status.dex')}/>
                </li>
                <li>
                    <label>CONSTITUIÇÃO</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number" {...register('status.con')}/>
                </li>
                <li>
                    <label>INTELIGÊNCIA</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number" {...register('status.int')}/>
                </li>
                <li>
                    <label>SABEDORIA</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number" {...register('status.wis')}/>
                </li>
                <li>
                    <label>CARISMA</label>
                    <div className="bonus">
                        <h2>+0</h2>
                    </div>
                    <input type="number" {...register('status.cha')}/>
                </li>
            </ul>
        </div>
    );
}