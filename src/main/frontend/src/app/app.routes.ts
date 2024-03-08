import { Routes } from '@angular/router';
import { MapRouteComponent } from '@routes';

export const routes: Routes = [
  { path: '', redirectTo: 'map', pathMatch: 'full' },
  { path: 'map', title: 'Companies Map', component: MapRouteComponent },
];
