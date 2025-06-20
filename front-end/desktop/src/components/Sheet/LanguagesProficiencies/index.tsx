import "./style.css";
import type { SheetLanguagesProficienciesProps } from "../../../interfaces/sheet";

export function SheetLanguagesProficiencies({ register, defaultValues }: SheetLanguagesProficienciesProps) {
  return (
    <div className="s-languagesproficiencies">
      <div className="passive-perception">
        <input
          type="number"
          {...register("percepcaoPassiva")}
          defaultValue={defaultValues?.percepcaoPassiva ?? 10}
        />
        <label>PERCEPÇÃO PASSIVA (SABEDORIA)</label>
      </div>
      <div className="languages-proficiencies">
        <button type="button">Adicionar idioma ou proficiência</button>
        <textarea
          {...register("idiomas")}
          defaultValue={defaultValues?.idiomas ?? ""}
        />
      </div>
    </div>
  );
}
