import "./style.css";
import { useState, useEffect } from "react";
import type { SheetStatusProps } from "../../../interfaces/sheet";

export function SheetStatus({ register, defaultValues }: SheetStatusProps) {
  const DEFAULT_ATRIBUTO = 10;

  const [status, setStatus] = useState({
    for: defaultValues?.atributos?.forca ?? DEFAULT_ATRIBUTO,
    dex: defaultValues?.atributos?.destreza ?? DEFAULT_ATRIBUTO,
    con: defaultValues?.atributos?.constituicao ?? DEFAULT_ATRIBUTO,
    int: defaultValues?.atributos?.inteligencia ?? DEFAULT_ATRIBUTO,
    wis: defaultValues?.atributos?.sabedoria ?? DEFAULT_ATRIBUTO,
    cha: defaultValues?.atributos?.carisma ?? DEFAULT_ATRIBUTO,
  });

  const [bonus, setBonus] = useState({
    for: 0,
    dex: 0,
    con: 0,
    int: 0,
    wis: 0,
    cha: 0,
  });

  const calculateBonus = (value: number) => Math.floor((value - 10) / 2);

  useEffect(() => {
    setBonus({
      for: calculateBonus(status.for),
      dex: calculateBonus(status.dex),
      con: calculateBonus(status.con),
      int: calculateBonus(status.int),
      wis: calculateBonus(status.wis),
      cha: calculateBonus(status.cha),
    });
  }, [status]);

  useEffect(() => {
    if (defaultValues?.atributos) {
      setStatus({
        for: defaultValues.atributos.forca,
        dex: defaultValues.atributos.destreza,
        con: defaultValues.atributos.constituicao,
        int: defaultValues.atributos.inteligencia,
        wis: defaultValues.atributos.sabedoria,
        cha: defaultValues.atributos.carisma,
      });
    }
  }, [defaultValues]);

  const handleStatusChange = (e: React.ChangeEvent<HTMLInputElement>, stat: string) => {
    const value = parseInt(e.target.value) || 0;
    setStatus(prev => ({ ...prev, [stat]: value }));
  };

  return (
    <div className="s-status">
      <ul>
        {[
          { key: 'for', label: 'FORÇA', name: 'atributos.forca' },
          { key: 'dex', label: 'DESTREZA', name: 'atributos.destreza' },
          { key: 'con', label: 'CONSTITUIÇÃO', name: 'atributos.constituicao' },
          { key: 'int', label: 'INTELIGÊNCIA', name: 'atributos.inteligencia' },
          { key: 'wis', label: 'SABEDORIA', name: 'atributos.sabedoria' },
          { key: 'cha', label: 'CARISMA', name: 'atributos.carisma' },
        ].map(({ key, label, name }) => (
          <li key={key} className={key === 'for' ? 'first-status' : ''}>
            <label>{label}</label>
            <div className="bonus">
              <h2>{bonus[key as keyof typeof bonus] >= 0 ? `+${bonus[key as keyof typeof bonus]}` : bonus[key as keyof typeof bonus]}</h2>
            </div>
            <input 
              type="number" 
              {...register(name, { valueAsNumber: true })}
              onChange={(e) => handleStatusChange(e, key)}
              value={status[key as keyof typeof status]}
            />
          </li>
        ))}
      </ul>
    </div>
  );
}