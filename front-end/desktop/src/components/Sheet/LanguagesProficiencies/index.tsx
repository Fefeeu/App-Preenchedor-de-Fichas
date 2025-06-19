import "./style.css";

export function SheetLanguagesProficiencies ({register}: any){
    return(
        <div className="s-languagesproficiencies">
            <div className="passive-perception">
                <input type="number"/><label>PERCEPÇÃO PASSIVA (SABEDORIA)</label>
            </div>
            <div className="languages-proficiencies">
                <button>Adicionar idioma ou proficiência</button>
                <textarea {...register('idiomas')}></textarea>
            </div>
        </div>
    )
}