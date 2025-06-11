import "./style.css";

export function SheetEquipment(){
    return (
        <div className="s-equipment">
            <div className="coins">
                <ul>
                    <li>PO</li>
                </ul>
            </div>
            <button>Adicionar equipamento</button>
            <div className="equipment-list">
                <textarea></textarea>
            </div>
            <label>EQUIPAMENTOS</label>
        </div>
    )
}