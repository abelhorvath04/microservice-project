import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sensor } from '../models/sensor';

@Injectable({
  providedIn: 'root',
})
export class SensorService {
  private apiUrl = `https://7290-2a02-ab88-5681-7b80-90fa-7f36-ab80-993e.ngrok-free.app/sensors`;

  constructor(private http: HttpClient) {}
  
  getAllSensors(): Observable<Sensor[]> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'ngrok-skip-browser-warning': 'true',
    });

    return this.http.get<Sensor[]>(this.apiUrl, { headers });
  }
}
