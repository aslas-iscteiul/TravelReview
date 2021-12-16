export interface IScore {
  id?: number;
  number?: number | null;
  description?: string | null;
}

export class Score implements IScore {
  constructor(public id?: number, public number?: number | null, public description?: string | null) {}
}
