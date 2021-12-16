export interface IAirlineCompany {
  id?: number;
  name?: string | null;
  website?: string | null;
  email?: string | null;
  headquarters?: string | null;
  foundation?: Date | null;
}

export class AirlineCompany implements IAirlineCompany {
  constructor(
    public id?: number,
    public name?: string | null,
    public website?: string | null,
    public email?: string | null,
    public headquarters?: string | null,
    public foundation?: Date | null
  ) {}
}
