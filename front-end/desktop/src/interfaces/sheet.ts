export interface SheetResume {
  idFicha: number;
  nomePersonagem: string;
  classe: string;
  raca: string;
  nivel: number;
}

export interface Atributos {
  forca: number;
  destreza: number;
  constituicao: number;
  inteligencia: number;
  sabedoria: number;
  carisma: number;
}

export interface Pericias {
  forca: boolean;
  destreza: boolean;
  constituicao: boolean;
  inteligencia: boolean;
  sabedoria: boolean;
  carisma: boolean;
  acrobacia: boolean;
  arcanismo: boolean;
  atletismo: boolean;
  atuacao: boolean;
  blefar: boolean;
  enganacao: boolean;
  furtividade: boolean;
  historia: boolean;
  intimidacao: boolean;
  intuicao: boolean;
  investigacao: boolean;
  lidarComAnimais: boolean;
  medicina: boolean;
  natureza: boolean;
  percepcao: boolean;
  persuasao: boolean;
  prestidigitacao: boolean;
  religiao: boolean;
  sobrevivencia: boolean;
}

export interface Inventario {
  pc: string;  // Cobre
  pp: string;  // Prata
  pe: string;  // Electrum
  po: string;  // Ouro
  pl: string;  // Platina
  itens: string;
}

export interface Descricao {
  historia: string;
  aparencia: string;
  personalidade: string;
  ideal: string;
  ligacao: string;
  defeito: string;
  proficiencia: string;
}

export interface CharacterSheet {
  nomePersonagem: string;
  classeArmadura: number;
  idClasse: string;
  idRaca: string;
  antecedente: string;
  tendencia: string;
  xp: string;
  nivel: string;
  atributos: Atributos;
  inspiracao: boolean;
  pericias: Pericias;
  idiomas: string;
  deslocamento: string;
  pontosVidaBase: string;
  vidaTemporaria: string;
  dadoDeVida: string;
  inventario: Inventario;
  descricao: Descricao;
}

export interface CharacterSheetResponse {
  success: boolean;
  message?: string;
  data: CharacterSheet;
}

// PROPS

export interface SheetHeaderProps {
  register: any;
  defaultValues?: {
    nomePersonagem?: string;
    idClasse?: string;
    idRaca?: string;
    antecedente?: string;
    tendencia?: string;
    xp?: string | number;
    nivel?: string | number;
  };
}

export interface SheetStatusProps {
  register: any;
  defaultValues?: {
    atributos?: Atributos;
  };
}

export interface SheetSkillsProps {
  register: any;
  defaultValues?: {
    inspiracao?: boolean;
    bonusProficiencia?: number;
    pericias?: {
      forca?: boolean;
      destreza?: boolean;
      constituicao?: boolean;
      inteligencia?: boolean;
      sabedoria?: boolean;
      carisma?: boolean;
      acrobacia?: boolean;
      arcanismo?: boolean;
      atletismo?: boolean;
      atuacao?: boolean;
      enganacao?: boolean;
      furtividade?: boolean;
      historia?: boolean;
      intimidacao?: boolean;
      intuicao?: boolean;
      investigacao?: boolean;
      lidarComAnimais?: boolean;
      medicina?: boolean;
      natureza?: boolean;
      percepcao?: boolean;
      persuasao?: boolean;
      prestidigitacao?: boolean;
      religiao?: boolean;
      sobrevivencia?: boolean;
    };
  };
}

export interface SheetLanguagesProficienciesProps {
  register: any;
  defaultValues?: {
    percepcaoPassiva?: number;
    idiomas?: string;
  };
}

export interface SheetCurrentStatusProps {
  register: any;
  defaultValues?: {
    classeArmadura?: number;
    deslocamento?: number;
    pontosVidaBase?: number;
    vidaTemporaria?: number;
    dadoDeVida?: 6 | 8 | 10 | 12;
  };
}

export interface SheetEquipmentProps {
  register: any;
  defaultValues?: {
    inventario?: {
      id?: number;
      pc?: number;
      pp?: number;
      pe?: number;
      po?: number;
      pl?: number;
    };
  };
}

export interface SheetCharacterProps {
  register: any;
  defaultValues?: {
    descricao?: {
      personalidade?: string;
      ideal?: string;
      ligacao?: string;
      defeito?: string;
      proficiencia?: string;
    };
  };
}
