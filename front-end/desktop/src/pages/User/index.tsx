import "./style.css";
import { IoMdAdd } from "react-icons/io";
import { FaEdit } from "react-icons/fa";
import { MdDeleteForever } from "react-icons/md";
import { Header } from "../../components/Header";
import api from "../../services/api";
import { useEffect, useState } from "react";
import type { SheetResume } from "../../interfaces/sheet";
import type { ApiResponse } from "../../interfaces/response";
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer,toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";

export function UserPage() {
    const navigate = useNavigate();
    const [sheets, setSheets] = useState<SheetResume[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        async function fetchUserSheets() {
            try {
                const response = await api.get<ApiResponse>('/pack/sheets/1');
                if (response.data.success) {
                    setSheets(response.data.data);
                } else {
                    setError(response.data.message);
                }
            } catch (err) {
                setError("Erro ao carregar as fichas do usuário");
                console.error(err);
            } finally {
                setLoading(false);
            }
        }

        fetchUserSheets();
    }, []);

    async function handleDeleteSheet(sheetId: number) {
        const confirmDelete = window.confirm("Tem certeza que deseja deletar esta ficha?");
        if (!confirmDelete) return;

        try {
            const response = await api.delete<ApiResponse>(`/pack/delete/${sheetId}`);
            if (response.data.success) {
                toast.success("Ficha deletada com sucesso!");

                setSheets((prev) => prev.filter(sheet => sheet.idFicha !== sheetId));
            } else {
                toast.error("Ocorreu um erro ao deletar a ficha: " + response.data.message);
            }
        } catch (err) {
            setError("Erro ao carregar as fichas do usuário");
            console.error(err);
        } finally {
            setLoading(false);
        }
    }


    return (
        <>
            <Header></Header>
            <main className="user-page">
                <div className="user-data-container">
                    <div className="user-image">
                        <img src="https://i.imgur.com/jbKb5QG.png" alt="User Avatar" className="avatar-img" height={200}/>
                    </div>
                    <div className="user-data">
                        <h2>AltDel</h2>
                        <p>PLANO PREMIUM.</p>
                    </div>
                </div>
                <div className="user-pack-container">
                    <section className="user-pack-header">
                        <h2>Mochila</h2>
                        <button className="new" onClick={() => navigate("/criar-ficha")}><IoMdAdd fontSize={20}/><span>CRIAR NOVA FICHA</span></button>
                    </section>
                    
                    {loading ? (
                        <p>Carregando fichas...</p>
                    ) : error ? (
                        <p className="error-message">{error}</p>
                    ) : (
                        <section className="user-pack-list">
                            {sheets.length > 0 ? (
                                sheets.map((sheet) => (
                                    <div className="user-pack-item" key={sheet.idFicha}>
                                        <div className="user-pack-item-header">
                                            <h2>{sheet.nomePersonagem}</h2>
                                        </div>
                                        <div className="user-pack-item-content">
                                            <span>{sheet.classe} - Nível {sheet.nivel} - {sheet.raca}</span>
                                        </div>
                                        <footer>
                                            <button className="edit-sheet" onClick={() => navigate(`/sheet/${sheet.idFicha}`)}><FaEdit fontSize={22}/></button>
                                            <button className="delete-sheet" onClick={() => handleDeleteSheet(sheet.idFicha)}><MdDeleteForever fontSize={22} /></button>
                                        </footer>
                                    </div>
                                ))
                            ) : (
                                <p>Nenhuma ficha encontrada.</p>
                            )}
                        </section>
                    )}
                </div>
            </main>
            <ToastContainer position="bottom-right" />
        </>
    )
}