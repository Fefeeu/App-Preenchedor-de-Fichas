import "./style.css";
import type { SheetHeaderProps } from "../../../interfaces/sheet";

export function SheetHeader({ register, defaultValues }: SheetHeaderProps) {
    return (
        <div className="s-header">
            <div className="name">
                <input 
                    type="text" 
                    {...register('nomePersonagem')} 
                    defaultValue={defaultValues?.nomePersonagem || ''}
                    required
                />
                <label>NOME DO PERSONAGEM</label>
            </div>
            <div className="details">
                <div className="c-1">
                    <div className="input-group">
                        <select 
                            {...register('idClasse')}
                            defaultValue={defaultValues?.idClasse || ''}
                            required
                        >
                            <option value="">Classe</option>
                            <option value="barbaro">Bárbaro</option>
                            <option value="bardo">Bardo</option>
                            <option value="bruxo">Bruxo</option>
                            <option value="clerigo">Clérigo</option>
                            <option value="druida">Druida</option>
                            <option value="feiticeiro">Feiticeiro</option>
                            <option value="guerreiro">Guerreiro</option>
                            <option value="ladino">Ladino</option>
                            <option value="mago">Mago</option>
                            <option value="monge">Monge</option>
                            <option value="paladino">Paladino</option>
                            <option value="patrulheiro">Patrulheiro</option>
                            <option value="artifice">Artífice</option>
                        </select>
                    </div>
                    <select 
                        {...register('idRaca')}
                        defaultValue={defaultValues?.idRaca || ''}
                        required
                    >
                        <option value="">Raça</option>
                        <option value="anao">Anão</option>
                        <option value="elfo">Elfo</option>
                        <option value="halfling">Halfling</option>
                        <option value="humano">Humano</option>
                        <option value="draconato">Draconato</option>
                        <option value="gnomo">Gnomo</option>
                        <option value="meio-elfo">Meio-Elfo</option>
                        <option value="meio-orc">Meio-Orc</option>
                        <option value="tiefling">Tiefling</option>
                    </select>
                </div>
                <div className="c-2">
                    <select 
                        {...register('antecedente')}
                        defaultValue={defaultValues?.antecedente || ''}
                        required
                    >
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
                    <select 
                        {...register('tendencia')}
                        defaultValue={defaultValues?.tendencia || ''}
                        required
                    >
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
                        <input 
                            type="number" 
                            {...register('xp')}
                            defaultValue={defaultValues?.xp || ''}
                        />
                    </div>
                    <div className="input-group">
                        <label>Nível:</label>
                        <input 
                            type="number" 
                            {...register('nivel')}
                            defaultValue={defaultValues?.nivel || ''}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
}