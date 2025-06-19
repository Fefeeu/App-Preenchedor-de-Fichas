import "./style.css";
import { useState, useEffect } from "react";

export function SheetStatus({ register }: any) {
  const [status, setStatus] = useState({
    for: 10,
    dex: 10,
    con: 10,
    int: 10,
    wis: 10,
    cha: 10,
  });

  const [bonus, setBonus] = useState({
    for: 0,
    dex: 0,
    con: 0,
    int: 0,
    wis: 0,
    cha: 0,
  });

  const calculateBonus = (value: number) => {
    return Math.floor((value - 10) / 2);
  };

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

  const handleStatusChange = (e: React.ChangeEvent<HTMLInputElement>, stat: string) => {
    const value = parseInt(e.target.value) || 0;
    setStatus(prev => ({ ...prev, [stat]: value }));
  };

  return (
    <div className="s-status">
      <ul>
        <li className="first-status">
          <label>FORÇA</label>
          <div className="bonus">
            <h2>{bonus.for >= 0 ? `+${bonus.for}` : bonus.for}</h2>
          </div>
          <input 
            type="number" 
            {...register('atributos.forca')}
            onChange={(e) => handleStatusChange(e, 'for')}
            value={status.for}
          />
        </li>
        <li>
          <label>DESTREZA</label>
          <div className="bonus">
            <h2>{bonus.dex >= 0 ? `+${bonus.dex}` : bonus.dex}</h2>
          </div>
          <input 
            type="number" 
            {...register('atributos.destreza')}
            onChange={(e) => handleStatusChange(e, 'dex')}
            value={status.dex}
          />
        </li>
        <li>
          <label>CONSTITUIÇÃO</label>
          <div className="bonus">
            <h2>{bonus.con >= 0 ? `+${bonus.con}` : bonus.con}</h2>
          </div>
          <input 
            type="number" 
            {...register('atributos.constituicao')}
            onChange={(e) => handleStatusChange(e, 'con')}
            value={status.con}
          />
        </li>
        <li>
          <label>INTELIGÊNCIA</label>
          <div className="bonus">
            <h2>{bonus.int >= 0 ? `+${bonus.int}` : bonus.int}</h2>
          </div>
          <input 
            type="number" 
            {...register('atributos.inteligencia')}
            onChange={(e) => handleStatusChange(e, 'int')}
            value={status.int}
          />
        </li>
        <li>
          <label>SABEDORIA</label>
          <div className="bonus">
            <h2>{bonus.wis >= 0 ? `+${bonus.wis}` : bonus.wis}</h2>
          </div>
          <input 
            type="number" 
            {...register('atributos.sabedoria')}
            onChange={(e) => handleStatusChange(e, 'wis')}
            value={status.wis}
          />
        </li>
        <li>
          <label>CARISMA</label>
          <div className="bonus">
            <h2>{bonus.cha >= 0 ? `+${bonus.cha}` : bonus.cha}</h2>
          </div>
          <input 
            type="number" 
            {...register('atributos.carisma')}
            onChange={(e) => handleStatusChange(e, 'cha')}
            value={status.cha}
          />
        </li>
      </ul>
    </div>
  );
}