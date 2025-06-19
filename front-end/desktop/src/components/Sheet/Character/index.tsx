import "./style.css";

export function SheetCharacter({register}: any){
    return(
        <div className="s-character">
            <div className="about-character">
                <div className="text-box">
                    <label>Traços de personalidade</label>
                    <input type="text" {...register('descricao.personalidade')}/>
                </div>
                <div className="text-box">
                    <label>Ideais</label>
                    <input type="text" {...register('descricao.ideal')}/>
                </div>
                <div className="text-box">
                    <label>Ligações</label>
                    <input type="text" {...register('descricao.ligacao')}/>
                </div>
                <div className="text-box">
                    <label>Defeitos</label>
                    <input type="text" {...register('descricao.defeito')}/>
                </div>
            </div>
            <div className="details-skills">
                <textarea placeholder="Details and Skills" {...register('descricao.proficiencia')}>

                </textarea>
            </div>
        </div>
    )
}