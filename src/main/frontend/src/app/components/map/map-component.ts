import { AfterViewInit, Component, Input } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map-component',
  standalone: true,
  imports: [],
  template: `<div id="map" class="w-full h-full"></div>`,
})
export class MapComponent implements AfterViewInit {
  private MALAGA_COORDINATES = {
    lat: 36.72016,
    lng: -4.42034
  };

  @Input() markers: L.Marker[] | null = null;

  map: L.Map | undefined;

  ngAfterViewInit(): void {
    this.map = L.map('map').setView(this.MALAGA_COORDINATES, 16);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 16,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
    }).addTo(this.map);

    console.log(this.markers);
    if (this.markers) this.markers.forEach(marker => marker.addTo(this.map!));
  }

}
