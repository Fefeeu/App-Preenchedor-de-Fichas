import "./style.css";

export function SheetSkills(){
    return(
        <div className="s-skills">
            <div className="inspiration">
                <input type="checkbox"/><label>INSPIRAÇÃO</label>
            </div>
            <div className="proficiency">
                <input type="number"/><label>BÔNUS DE PROFICIÊNCIA</label>
            </div>
            <div className="saving-throw">
                <ul>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Força</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Destreza</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Constituição</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Inteligência</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Sabedoria</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Carisma</label>
                    </li>
                </ul>
                <label>TESTES DE RESISTÊNCIA</label>
            </div>
            <div className="skills">
                <ul>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Acrobacia (Des)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Arcanismo (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Atletismo (For)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Atuação (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Blefar (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Furtividade (Des)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>História (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Intimidação (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Intuição (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Investigação (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Lidar com Animais (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Medicina (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Natureza (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Percepção (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Persuasão (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Prestidigitação (Des)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Religião (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number"/><label>Sobrevivência (Sab)</label>
                    </li>
                </ul>
                <label>PERÍCIAS</label>
            </div>
        </div>
    );
}