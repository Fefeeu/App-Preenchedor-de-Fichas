import "./style.css";

export function SheetHeader(){
    return (
        <div className="s-header">
            <div className="name">
                <input type="text"/>
                <label>NOME DO PERSONAGEM</label>
            </div>
            <div className="details">
                <div className="c-1">
                    <div className="input-group">
                        <select>
                            <option value="">Classe</option>
                            <option value="Barbaro">Bárbaro</option>
                            <option value="Bardo">Bardo</option>
                            <option value="Bruxo">Bruxo</option>
                            <option value="Clerigo">Clérigo</option>
                            <option value="Druida">Druida</option>
                            <option value="Feiticeiro">Feiticeiro</option>
                            <option value="Guerreiro">Guerreiro</option>
                            <option value="Ladino">Ladino</option>
                            <option value="Mago">Mago</option>
                            <option value="Monge">Monge</option>
                            <option value="Paladino">Paladino</option>
                            <option value="Patrulheiro">Patrulheiro</option>
                            <option value="Artifice">Artífice</option>
                        </select>
                    </div>
                    <select>
                        <option value="">Raça</option>
                        <option value="Anao">Anão</option>
                        <option value="Elfo">Elfo</option>
                        <option value="Halfling">Halfling</option>
                        <option value="Humano">Humano</option>
                        <option value="Draconato">Draconato</option>
                        <option value="Gnomo">Gnomo</option>
                        <option value="MeioElfo">Meio-Elfo</option>
                        <option value="MeioOrc">Meio-Orc</option>
                        <option value="Tiefling">Tiefling</option>
                    </select>
                </div>
                <div className="c-2">
                    <select>
                        <option>Antecedente</option>
                    </select>
                    <select>
                        <option>Tendência</option>
                    </select>
                </div>
                <div className="c-3">
                    <div className="input-group">
                        <label>XP:</label>
                        <input type="number"/>
                    </div>
                    <div className="input-group">
                        <label>Nível:</label>
                        <input type="number"/>
                    </div>
                </div>
            </div>
        </div>
    );
}