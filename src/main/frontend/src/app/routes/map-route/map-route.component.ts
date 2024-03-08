import { AsyncPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { MapComponent } from '@components';
import { CompanyService } from 'app/services/company.service';
import * as L from 'leaflet';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-map-route',
  standalone: true,
  imports: [MapComponent, AsyncPipe],
  template: `
    @if(companies | async; as companiesResponse) {
      <div class="grid grid-cols-6">
        <section class="col-span-2">
          <ul>
            @for(company of companiesResponse; track company.id) {
              <li>{{ company.name }}</li>
            }
          </ul>
        </section>
        <section class="w-full h-full">
        <app-map-component [markers]="getMarkers() | async" />
        </section>
      </div>
    }`
})
export class MapRouteComponent {
  private companyService = inject(CompanyService);

  companies = this.companyService.getCompanies();

  getMarkers = () =>
    this.companies.pipe(
      map((companies) =>
        companies.map((company) =>
          L.marker([company.location.latitude, company.location.longitude])
        )
      )
    );


}
