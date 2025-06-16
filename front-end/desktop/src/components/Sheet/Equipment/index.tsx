import "./style.css";

export function SheetEquipment({ register }: any){
    return (
        <div className="s-equipment">
            <div className="coins">
                <ul>
                    <li>
                        <label htmlFor="equipment-pc">PC (Cobre)</label>
                        <input 
                            id="equipment-pc"
                            type="number" 
                            {...register('equipment.pc')}
                        />
                    </li>
                    <li>
                        <label htmlFor="equipment-pp">PP (Prata)</label>
                        <input 
                            id="equipment-pp"
                            type="number" 
                            {...register('equipment.pp')}
                        />
                    </li>
                    <li>
                        <label htmlFor="equipment-pe">PE (Electro)</label>
                        <input 
                            id="equipment-pe"
                            type="number" 
                            {...register('equipment.pe')}
                        />
                    </li>
                    <li>
                        <label htmlFor="equipment-po">PO (Ouro)</label>
                        <input 
                            id="equipment-po"
                            type="number" 
                            {...register('equipment.po')}
                        />
                    </li>
                    <li>
                        <label htmlFor="equipment-pl">PL (Platina)</label>
                        <input 
                            id="equipment-pl"
                            type="number" 
                            {...register('equipment.pl')}
                        />
                    </li>
                </ul>
            </div>
            <button>Adicionar equipamento</button>
            <div className="equipment-list">
                <textarea {...register('equipment.other')}></textarea>
            </div>
            <label>EQUIPAMENTOS</label>
        </div>
    )
}