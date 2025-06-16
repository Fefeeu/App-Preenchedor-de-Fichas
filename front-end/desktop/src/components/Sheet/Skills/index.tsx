import "./style.css";

export function SheetSkills({register}: any){
    return(
        <div className="s-skills">
            <div className="inspiration">
                <input type="checkbox" {...register('skills.inspiration')}/><label>INSPIRAÇÃO</label>
            </div>
            <div className="proficiency">
                <input type="number" {...register('skills.proficiency')}/><label>BÔNUS DE PROFICIÊNCIA</label>
            </div>
            <div className="saving-throw">
                <ul>
                    <li>
                        <input type="checkbox"/><input type="number" {...register('skills.saveStr')}/><label>Força</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number" {...register('skills.saveDex')}/><label>Destreza</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number" {...register('skills.saveCon')}/><label>Constituição</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number" {...register('skills.saveInt')}/><label>Inteligência</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number" {...register('skills.saveWis')}/><label>Sabedoria</label>
                    </li>
                    <li>
                        <input type="checkbox"/><input type="number" {...register('skills.saveCha')}/><label>Carisma</label>
                    </li>
                </ul>
                <label>TESTES DE RESISTÊNCIA</label>
            </div>
            <div className="skills">
                <ul>
                    <li>
                        <input type="checkbox" {...register('skills.acrobacia.proficiente')}/>
                        <input type="number" {...register('skills.acrobacia.valor')}/>
                        <label>Acrobacia (Des)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.arcanismo.proficiente')}/>
                        <input type="number" {...register('skills.arcanismo.valor')}/>
                        <label>Arcanismo (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.atletismo.proficiente')}/>
                        <input type="number" {...register('skills.atletismo.valor')}/>
                        <label>Atletismo (For)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.atuacao.proficiente')}/>
                        <input type="number" {...register('skills.atuacao.valor')}/>
                        <label>Atuação (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.blefar.proficiente')}/>
                        <input type="number" {...register('skills.blefar.valor')}/>
                        <label>Blefar (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.furtividade.proficiente')}/>
                        <input type="number" {...register('skills.furtividade.valor')}/>
                        <label>Furtividade (Des)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.historia.proficiente')}/>
                        <input type="number" {...register('skills.historia.valor')}/>
                        <label>História (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.intimidacao.proficiente')}/>
                        <input type="number" {...register('skills.intimidacao.valor')}/>
                        <label>Intimidação (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.intuicao.proficiente')}/>
                        <input type="number" {...register('skills.intuicao.valor')}/>
                        <label>Intuição (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.investigacao.proficiente')}/>
                        <input type="number" {...register('skills.investigacao.valor')}/>
                        <label>Investigação (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.animais.proficiente')}/>
                        <input type="number" {...register('skills.animais.valor')}/>
                        <label>Lidar com Animais (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.medicina.proficiente')}/>
                        <input type="number" {...register('skills.medicina.valor')}/>
                        <label>Medicina (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.natureza.proficiente')}/>
                        <input type="number" {...register('skills.natureza.valor')}/>
                        <label>Natureza (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.percepcao.proficiente')}/>
                        <input type="number" {...register('skills.percepcao.valor')}/>
                        <label>Percepção (Sab)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.persuasao.proficiente')}/>
                        <input type="number" {...register('skills.persuasao.valor')}/>
                        <label>Persuasão (Car)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.prestidigitacao.proficiente')}/>
                        <input type="number" {...register('skills.prestidigitacao.valor')}/>
                        <label>Prestidigitação (Des)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.religiao.proficiente')}/>
                        <input type="number" {...register('skills.religiao.valor')}/>
                        <label>Religião (Int)</label>
                    </li>
                    <li>
                        <input type="checkbox" {...register('skills.sobrevivencia.proficiente')}/>
                        <input type="number" {...register('skills.sobrevivencia.valor')}/>
                        <label>Sobrevivência (Sab)</label>
                    </li>
                </ul>
                <label>PERÍCIAS</label>
            </div>
        </div>
    );
}