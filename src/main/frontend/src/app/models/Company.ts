export type CompanyLocation = {
  latitude: number;
  longitude: number;
};

export type Company = {
  id: number;
  name: string;
  sector: string;
  numberOfEmployees: number;
  revenues: number;
  description: string;
  location: CompanyLocation;
}
