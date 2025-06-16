import "./style.css";
import { useForm } from 'react-hook-form';
import { SheetAttacksMagics } from "./AtacksMagics";
import { SheetCurrentStatus } from "./CurrentStatus";
import { SheetEquipment } from "./Equipment";
import { SheetHeader } from "./Header";
import { SheetSkills } from "./Skills";
import { SheetStatus } from "./Status";
import { SheetCharacter } from "./Character";
import { SheetLanguagesProficiencies } from "./LanguagesProficiencies";


export function Sheet(){
    const { handleSubmit, register } = useForm();
  
    const onSubmit = (data: any) => {
        console.log("Dados do formulário:", data);
    };

    return(
        <form onSubmit={handleSubmit(onSubmit)} className="sheet-form">
            <div className="full-sheet">
                <div className="header-sheet">
                    <SheetHeader register={register}></SheetHeader>
                </div>
                <div className="body-sheet">
                    <div className="big-column-1">
                        <div className="section section-1">
                            <div className="c-1">
                                <SheetStatus register={register}></SheetStatus>
                            </div>
                            <div className="c-2">
                                <SheetSkills register={register}></SheetSkills>
                            </div>
                        </div>
                        <div className="section section-2">
                            <SheetLanguagesProficiencies register={register}></SheetLanguagesProficiencies>
                        </div>
                    </div>
                    <div className="big-column-2">
                        <div className="c-3">
                            <SheetCurrentStatus register={register}></SheetCurrentStatus>
                            <SheetAttacksMagics register={register}></SheetAttacksMagics>
                            <SheetEquipment register={register}></SheetEquipment>
                        </div>
                    </div>
                    <div className="big-column-3">
                        <div className="c-4">
                            <SheetCharacter register={register}></SheetCharacter>
                        </div>
                    </div>
                </div>
            </div>
            <button type="submit" className="save-button">Save</button>
        </form>
    )
}