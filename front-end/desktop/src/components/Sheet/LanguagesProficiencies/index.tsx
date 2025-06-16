import "./style.css";

export function SheetLanguagesProficiencies ({register}: any){
    return(
        <div className="s-languagesproficiencies">
            <div className="passive-perception">
                <input type="number" {...register('languagesproficiencies.perception')}/><label>PERCEPÇÃO PASSIVA (SABEDORIA)</label>
            </div>
            <div className="languages-proficiencies">
                <button>Adicionar idioma ou proficiência</button>
                <textarea {...register('languagesproficiencies.other')}></textarea>
            </div>
        </div>
    )
}