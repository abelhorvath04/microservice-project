import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ScrollService {
  scrollToElement(elementId: string): void {
    const element = document.getElementById(elementId);
    const offset = 100; // Pixel offset

    if (element) {
      const position = element.getBoundingClientRect().top + window.scrollY - offset;
      window.scrollTo({ top: position, behavior: 'smooth' });
    } else {
      console.warn(`Element with ID "${elementId}" not found.`);
    }
  }
}
