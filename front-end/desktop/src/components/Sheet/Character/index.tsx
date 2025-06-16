import "./style.css";

export function SheetCharacter({register}: any){
    return(
        <div className="s-character">
            <div className="about-character">
                <div className="text-box">
                    <label>Traços de personalidade</label>
                    <input type="text" {...register('character.personality')}/>
                </div>
                <div className="text-box">
                    <label>Ideais</label>
                    <input type="text" {...register('character.ideals')}/>
                </div>
                <div className="text-box">
                    <label>Ligações</label>
                    <input type="text" {...register('character.connections')}/>
                </div>
                <div className="text-box">
                    <label>Defeitos</label>
                    <input type="text" {...register('character.bad')}/>
                </div>
            </div>
            <div className="details-skills">
                <textarea placeholder="Details and Skills" {...register('character.other')}>

                </textarea>
            </div>
        </div>
    )
}