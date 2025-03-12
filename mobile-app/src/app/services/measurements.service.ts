// src/app/services/sensor.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MeasurementModel } from '../models/measurement';

@Injectable({
  providedIn: 'root',
})
export class MeasurementService {
  private apiUrl = `https://7290-2a02-ab88-5681-7b80-90fa-7f36-ab80-993e.ngrok-free.app/measurements`;

  constructor(private http: HttpClient) {}

  getAllMeasurements(): Observable<MeasurementModel[]> {
    return this.http.get<MeasurementModel[]>(this.apiUrl, {
      headers: {
        'ngrok-skip-browser-warning': 'true',
        'Content-Type': 'application/json',
      },
    });
  }
}
