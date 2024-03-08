import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Company, type CompanyResponse } from '@models';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private http = inject(HttpClient);
  private BASE_URL = 'http://localhost:8080/company';

  getCompanies = () =>
    this.http
      .get<CompanyResponse>(this.BASE_URL)
      .pipe(map((response) => response.companies));


  getCompany = (id: number) =>
    this.http
      .get<Company>(`${this.BASE_URL}/${id}`)
      .pipe(map((response) => response));


}

