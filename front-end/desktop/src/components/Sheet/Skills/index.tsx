import "./style.css";

export function SheetSkills({register}: any){
    return(
        <div className="s-skills">
            <div className="inspiration">
                <input type="checkbox" {...register('inspiracao')}/><label>INSPIRAÇÃO</label>
            </div>
            <div className="proficiency">
                <input type="number"/><label>BÔNUS DE PROFICIÊNCIA</label>
            </div>
            <div className="saving-throw">
                <ul>
                    <li>
                        <input type="checkbox" {...register('pericias.forca')}/><label>Força</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.destreza')}/><label>Destreza</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.constituicao')}/><label>Constituição</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.inteligencia')}/><label>Inteligência</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.sabedoria')}/><label>Sabedoria</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.carisma')}/><label>Carisma</label>
                    </li>
                </ul>
                <label>TESTES DE RESISTÊNCIA</label>
            </div>
            <div className="skills">
                <ul>
                    <li>
                        <input type="checkbox" {...register('pericias.acrobacia')}/>
                        <label>Acrobacia (Des)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.arcanismo')}/>
                        <label>Arcanismo (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.atletismo')}/>
                        <label>Atletismo (For)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.atuacao')}/>
                        <label>Atuação (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.enganacao')}/>
                        <label>Enganação (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.furtividade')}/>
                        <label>Furtividade (Des)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.historia')}/>
                        <label>História (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.intimidacao')}/>
                        <label>Intimidação (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.intuicao')}/>
                        <label>Intuição (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.investigacao')}/>
                        <label>Investigação (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.lidarComAnimais')}/>
                        <label>Lidar com Animais (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.medicina')}/>
                        <label>Medicina (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.natureza')}/>
                        <label>Natureza (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.percepcao')}/>
                        <label>Percepção (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.persuasao')}/>
                        <label>Persuasão (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.prestidigitacao')}/>
                        <label>Prestidigitação (Des)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.religiao')}/>
                        <label>Religião (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('pericias.sobrevivencia')}/>
                        <label>Sobrevivência (Sab)</label>
                    </li>
                </ul>
                <label>PERÍCIAS</label>
            </div>
        </div>
    );
}