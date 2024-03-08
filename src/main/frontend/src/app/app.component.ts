import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AsideComponent } from '@components';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, AsideComponent],
  template: `
    <main class="grid grid-cols-6 w-full h-dvh bg-gray-200">
      <section class="col-span-1 p-6">
        <app-aside />
      </section>
      <section class="col-span-5 p-6 rounded-bl-3xl rounded-tl-3xl border bg-white">
        <router-outlet />
      </section>
    </main>
  `,
})
export class AppComponent {
}
