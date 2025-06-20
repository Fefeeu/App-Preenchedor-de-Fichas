import "./style.css";
import type { SheetSkillsProps } from "../../../interfaces/sheet";

export function SheetSkills({ register, defaultValues }: SheetSkillsProps) {
  return (
    <div className="s-skills">
      <div className="inspiration">
        <input
          type="checkbox"
          {...register("inspiracao")}
          defaultChecked={defaultValues?.inspiracao ?? false}
        />
        <label>INSPIRAÇÃO</label>
      </div>
      <div className="proficiency">
        <input
          type="number"
          {...register("bonusProficiencia")}
          defaultValue={defaultValues?.bonusProficiencia ?? 0}
        />
        <label>BÔNUS DE PROFICIÊNCIA</label>
      </div>
      <div className="saving-throw">
        <ul>
          {["forca", "destreza", "constituicao", "inteligencia", "sabedoria", "carisma"].map((key) => (
            <li key={key}>
              <input
                type="checkbox"
                {...register(`pericias.${key}`)}
                defaultChecked={defaultValues?.pericias?.[key as keyof typeof defaultValues.pericias] ?? false}
              />
              <label>{key.charAt(0).toUpperCase() + key.slice(1)}</label>
            </li>
          ))}
        </ul>
        <label>TESTES DE RESISTÊNCIA</label>
      </div>
      <div className="skills">
        <ul>
          {[
            "acrobacia", "arcanismo", "atletismo", "atuacao", "enganacao", "furtividade", "historia",
            "intimidacao", "intuicao", "investigacao", "lidarComAnimais", "medicina", "natureza",
            "percepcao", "persuasao", "prestidigitacao", "religiao", "sobrevivencia"
          ].map((key) => (
            <li key={key}>
              <input
                type="checkbox"
                {...register(`pericias.${key}`)}
                defaultChecked={defaultValues?.pericias?.[key as keyof typeof defaultValues.pericias] ?? false}
              />
              <label>{key.charAt(0).toUpperCase() + key.slice(1)}</label>
            </li>
          ))}
        </ul>
        <label>PERÍCIAS</label>
      </div>
    </div>
  );
}
