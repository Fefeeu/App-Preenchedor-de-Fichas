import "./style.css";

export function SheetLanguagesProficiencies (){
    return(
        <div className="s-languagesproficiencies">
            <div className="passive-perception">
                <input type="number"/><label>PERCEPÇÃO PASSIVA (SABEDORIA)</label>
            </div>
            <div className="languages-proficiencies">
                <button>Adicionar idioma ou proficiência</button>
                <textarea></textarea>
            </div>
        </div>
    )
}