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
import api from "../../services/api";
import { useEffect } from "react";
import {useParams,useNavigate} from "react-router-dom";
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer,toast } from 'react-toastify';
import type { CharacterSheet } from "../../interfaces/sheet";

export function Sheet({ initialData }: { initialData?: CharacterSheet }) {
  const navigate = useNavigate();

  const { id } = useParams<{ id?: string }>();
  const { handleSubmit, register, reset } = useForm<CharacterSheet>({
    defaultValues: initialData || {}
  });

  useEffect(() => {
    if (initialData) {
      reset(initialData);
    }
  }, [initialData, reset]);

  const onSubmit = async (data: CharacterSheet) => {
    try {
      if (id) {
        const response = await api.put(`/pack/update/${id}`, data);
        console.log(data);
        if (response.data.success) {
          toast.success("Ficha atualizada com sucesso!");
        }
      } else {
        const response = await api.post(`/pack/create`, data);
        if (response.data.success) {
          toast.success("Ficha criada com sucesso! Redirecionando...");
        }
        setTimeout(() => {
          navigate(`/sheet/${response.data.data.idFicha}`);
        }, 2000);
      }
    } catch (error) {
      console.error("Erro ao salvar os dados:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="sheet-form">
      <div className="full-sheet">
        <div className="header-sheet">
          <SheetHeader register={register} />
        </div>
        <div className="body-sheet">
          <div className="big-column-1">
            <div className="section section-1">
              <div className="c-1">
                <SheetStatus register={register} defaultValues={initialData}/>
              </div>
              <div className="c-2">
                <SheetSkills register={register} />
              </div>
            </div>
            <div className="section section-2">
              <SheetLanguagesProficiencies register={register} />
            </div>
          </div>
          <div className="big-column-2">
            <div className="c-3">
              <SheetCurrentStatus register={register} />
              <SheetAttacksMagics/>
              <SheetEquipment register={register} />
            </div>
          </div>
          <div className="big-column-3">
            <div className="c-4">
              <SheetCharacter register={register} />
            </div>
          </div>
        </div>
      </div>
      <button type="submit" className="save-button">
        {id ? "ATUALIZAR" : "SALVAR"}
      </button>
      <ToastContainer position="bottom-right" />
    </form>
  );
}