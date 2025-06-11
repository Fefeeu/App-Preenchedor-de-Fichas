import "./style.css";
import { SheetAttacksMagics } from "./AtacksMagics";
import { SheetCurrentStatus } from "./CurrentStatus";
import { SheetEquipment } from "./Equipment";
import { SheetHeader } from "./Header";
import { SheetSkills } from "./Skills";
import { SheetStatus } from "./Status";
import { SheetCharacter } from "./Character";
import { SheetLanguagesProficiencies } from "./LanguagesProficiencies";


export function Sheet(){
    return(
        <div className="full-sheet">
            <div className="header-sheet">
                <SheetHeader></SheetHeader>
            </div>
            <div className="body-sheet">
                <div className="big-column-1">
                    <div className="section section-1">
                        <div className="c-1">
                            <SheetStatus></SheetStatus>
                        </div>
                        <div className="c-2">
                            <SheetSkills></SheetSkills>
                        </div>
                    </div>
                    <div className="section section-2">
                        <SheetLanguagesProficiencies></SheetLanguagesProficiencies>
                    </div>
                </div>
                <div className="big-column-2">
                    <div className="c-3">
                        <SheetCurrentStatus></SheetCurrentStatus>
                        <SheetAttacksMagics></SheetAttacksMagics>
                        <SheetEquipment></SheetEquipment>
                    </div>
                </div>
                <div className="big-column-3">
                    <div className="c-4">
                        <SheetCharacter></SheetCharacter>
                    </div>
                </div>
            </div>
        </div>
    )
}