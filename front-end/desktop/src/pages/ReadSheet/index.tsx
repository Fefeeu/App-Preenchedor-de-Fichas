import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Header } from "../../components/Header";
import { Sheet } from "../../components/Sheet";
import api from "../../services/api";
import type { CharacterSheet, CharacterSheetResponse } from "../../interfaces/sheet";
import { convertToCharacterSheet } from "../../utils/conversor";

export function ReadSheetPage() {
  const { id } = useParams<{ id: string }>();
  const [sheetData, setSheetData] = useState<CharacterSheet | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function fetchSheet() {
      try {
        const response = await api.get<CharacterSheetResponse>(`/pack/sheet/${id}`);
        
        if (response.data.success) {
          const formatted = convertToCharacterSheet(response.data.data);
          setSheetData(formatted);
          console.log("Dados da ficha formatados:", formatted);
          console.log("Ficha carregada com sucesso:", response.data.data);
        } else {
          setError("Ficha não encontrada");
        }
      } catch (err) {
        setError("Erro ao carregar a ficha");
        console.error(err);
      } finally {
        setLoading(false);
      }
    }

    if (id) {
      fetchSheet();
    } else {
      setLoading(false);
    }
  }, [id]);

  if (loading) {
    return (
      <>
        <Header />
        <main>
          <p>Carregando ficha...</p>
        </main>
      </>
    );
  }

  if (error) {
    return (
      <>
        <Header />
        <main>
          <p className="error-message">{error}</p>
        </main>
      </>
    );
  }

  return (
    <>
      <Header />
      <main>
        <Sheet initialData={sheetData || undefined} />
      </main>
    </>
  );
}