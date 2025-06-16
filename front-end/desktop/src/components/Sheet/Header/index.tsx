import "./style.css";

export function SheetHeader({ register }: any){
    return (
        <div className="s-header">
            <div className="name">
                <input type="text" {...register('header.characterName')}/>
                <label>NOME DO PERSONAGEM</label>
            </div>
            <div className="details">
                <div className="c-1">
                    <div className="input-group">
                        <select {...register('header.characterClass')}>
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
                    <select {...register('header.characterRace')}>
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
                    <select {...register('header.characterBackground')}>
                        <option value="">Antecedente</option>
                        <option value="acolito">Acólito</option>
                        <option value="artesao de guilda">Artesão de Guilda</option>
                        <option value="artista">Artista</option>
                        <option value="charlatao">Charlatão</option>
                        <option value="criminoso">Criminoso</option>
                        <option value="eremita">Eremita</option>
                        <option value="forasteiro">Forasteiro</option>
                        <option value="heroi do povo">Herói do Povo</option>
                        <option value="marinheiro">Marinheiro</option>
                        <option value="nobre">Nobre</option>
                        <option value="orfao">Órfão</option>
                        <option value="sabio">Sábio</option>
                        <option value="soldado">Soldado</option>
                    </select>
                    <select {...register('header.characterAlignment')}>
                        <option value="">Tendência</option>
                        <option value="leal e bom">Leal e Bom (LB)</option>
                        <option value="neutro e bom">Neutro e Bom (NB)</option>
                        <option value="caotico e bom">Caótico e Bom (CB)</option>
                        <option value="leal e neutro">Leal e Neutro (LN)</option>
                        <option value="neutro">Neutro (N)</option>
                        <option value="caotico e neutro">Caótico e Neutro (CN)</option>
                        <option value="leal e mau">Leal e Mau (LM)</option>
                        <option value="neutro e mau">Neutro e Mau (NM)</option>
                        <option value="caotico e mau">Caótico e Mau (CM)</option>
                    </select>
                </div>
                <div className="c-3">
                    <div className="input-group">
                        <label>XP:</label>
                        <input type="number" {...register('header.characterExperience')}/>
                    </div>
                    <div className="input-group">
                        <label>Nível:</label>
                        <input type="number" {...register('header.characterLevel')}/>
                    </div>
                </div>
            </div>
        </div>
    );
}