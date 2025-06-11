import "./style.css";

export function SheetCharacter(){
    return(
        <div className="s-character">
            <div className="about-character">
                <div className="text-box">
                    <label>Traços de personalidade</label>
                    <input type="text"/>
                </div>
                <div className="text-box">
                    <label>Ideais</label>
                    <input type="text"/>
                </div>
                <div className="text-box">
                    <label>Ligações</label>
                    <input type="text"/>
                </div>
                <div className="text-box">
                    <label>Defeitos</label>
                    <input type="text"/>
                </div>
            </div>
            <div className="details-skills">
                <textarea placeholder="Details and Skills">

                </textarea>
            </div>
        </div>
    )
}