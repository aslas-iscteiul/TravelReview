export interface IAttraction {
  id?: number;
  name?: string | null;
  description?: string | null;
}

export class Attraction implements IAttraction {
  constructor(public id?: number, public name?: string | null, public description?: string | null) {}
}
