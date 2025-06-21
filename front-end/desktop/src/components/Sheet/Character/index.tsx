import "./style.css";
import type { SheetCharacterProps } from "../../../interfaces/sheet";

export function SheetCharacter({ register, defaultValues }: SheetCharacterProps) {
  return (
    <div className="s-character">
      <div className="about-character">
        <div className="text-box">
          <label>Traços de personalidade</label>
          <input
            type="text"
            {...register("descricao.personalidade")}
            defaultValue={defaultValues?.descricao?.personalidade ?? ""}
          />
        </div>
        <div className="text-box">
          <label>Ideais</label>
          <input
            type="text"
            {...register("descricao.ideal")}
            defaultValue={defaultValues?.descricao?.ideal ?? ""}
          />
        </div>
        <div className="text-box">
          <label>Ligações</label>
          <input
            type="text"
            {...register("descricao.ligacao")}
            defaultValue={defaultValues?.descricao?.ligacao ?? ""}
          />
        </div>
        <div className="text-box">
          <label>Defeitos</label>
          <input
            type="text"
            {...register("descricao.defeito")}
            defaultValue={defaultValues?.descricao?.defeito ?? ""}
          />
        </div>
      </div>
      <div className="details-skills">
        <textarea
          placeholder="Idiomas, habilidades, proficiências..."
          {...register("descricao.proficiencia")}
          defaultValue={defaultValues?.descricao?.proficiencia ?? ""}
        />
      </div>
    </div>
  );
}
