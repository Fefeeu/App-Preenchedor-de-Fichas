import "./style.css";
import type { SheetEquipmentProps } from "../../../interfaces/sheet";

export function SheetEquipment({ register, defaultValues }: SheetEquipmentProps) {
  return (
    <div className="s-equipment">
      <div className="coins">
        <ul>
          <li>
            <label htmlFor="equipment-pc">PC (Cobre)</label>
            <input
              id="equipment-pc"
              type="number"
              {...register("inventario.pc")}
              defaultValue={defaultValues?.inventario?.pc ?? 0}
            />
          </li>
          <li>
            <label htmlFor="equipment-pp">PP (Prata)</label>
            <input
              id="equipment-pp"
              type="number"
              {...register("inventario.pp")}
              defaultValue={defaultValues?.inventario?.pp ?? 0}
            />
          </li>
          <li>
            <label htmlFor="equipment-pe">PE (Electro)</label>
            <input
              id="equipment-pe"
              type="number"
              {...register("inventario.pe")}
              defaultValue={defaultValues?.inventario?.pe ?? 0}
            />
          </li>
          <li>
            <label htmlFor="equipment-po">PO (Ouro)</label>
            <input
              id="equipment-po"
              type="number"
              {...register("inventario.po")}
              defaultValue={defaultValues?.inventario?.po ?? 0}
            />
          </li>
          <li>
            <label htmlFor="equipment-pl">PL (Platina)</label>
            <input
              id="equipment-pl"
              type="number"
              {...register("inventario.pl")}
              defaultValue={defaultValues?.inventario?.pl ?? 0}
            />
          </li>
        </ul>
      </div>
      <button type="button">Adicionar equipamento</button>
      <div className="equipment-list">
        <textarea
          {...register("inventario.itens")}
        ></textarea>
      </div>
      <label>EQUIPAMENTOS</label>
    </div>
  );
}
