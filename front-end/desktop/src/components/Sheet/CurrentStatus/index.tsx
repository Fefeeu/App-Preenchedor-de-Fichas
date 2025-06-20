import "./style.css";
import type { SheetCurrentStatusProps } from "../../../interfaces/sheet";

export function SheetCurrentStatus({ register, defaultValues }: SheetCurrentStatusProps) {
  return (
    <div className="s-currentstatus">
      <div className="c-1">
        <div className="ca">
          <input type="number" defaultValue={10} /><label>CA</label>
        </div>
        <div className="initiative">
          <input type="number" defaultValue={0} /><label>Iniciativa</label>
        </div>
        <div className="movement">
          <input
            type="number"
            {...register("deslocamento")}
            defaultValue={defaultValues?.deslocamento ?? 9}
          />
          <label>Desloc.</label>
        </div>
      </div>
      <div className="c-2">
        <div className="input-group-pv">
          <label>PV Totais</label>
          <input
            type="number"
            className="max-pv"
            {...register("pontosVidaBase")}
            defaultValue={defaultValues?.pontosVidaBase ?? 10}
          />
        </div>
        <input type="number" className="current-pv" defaultValue={defaultValues?.pontosVidaBase ?? 10} />
        <label>Pontos de vida atuais</label>
      </div>
      <div className="c-3">
        <input
          type="number"
          className="temp-pv"
          {...register("vidaTemporaria")}
          defaultValue={defaultValues?.vidaTemporaria ?? 0}
        />
        <label>Pontos de vida temporários</label>
      </div>
      <div className="c-4">
        <div className="life-dice">
          <div className="input-group-dices">
            <label>Total</label>
            <input type="number" className="max-dice" defaultValue={1} />
          </div>
          <div className="input-type-dices">
            <input type="number" className="number-dice" defaultValue={1} />
            <select
              className="type-dice"
              {...register("dadoDeVida")}
              defaultValue={defaultValues?.dadoDeVida?.toString() ?? "8"}
            >
              <option value={6}>d6</option>
              <option value={8}>d8</option>
              <option value={10}>d10</option>
              <option value={12}>d12</option>
            </select>
          </div>
        </div>
        <div className="saving-death">
          <label>Sucessos</label>
          <input type="checkbox" />
          <input type="checkbox" />
          <input type="checkbox" />
          <br />
          <label>Fracassos</label>
          <input type="checkbox" />
          <input type="checkbox" />
          <input type="checkbox" />
        </div>
      </div>
    </div>
  );
}
