export interface IAccommodation {
  id?: number;
  name?: string | null;
  type?: string | null;
  sustainableAccommodation?: boolean | null;
  website?: string | null;
  email?: string | null;
  phone?: string | null;
  address?: string | null;
  classification?: number | null;
}

export class Accommodation implements IAccommodation {
  constructor(
    public id?: number,
    public name?: string | null,
    public type?: string | null,
    public sustainableAccommodation?: boolean | null,
    public website?: string | null,
    public email?: string | null,
    public phone?: string | null,
    public address?: string | null,
    public classification?: number | null
  ) {
    this.sustainableAccommodation = this.sustainableAccommodation ?? false;
  }
}
