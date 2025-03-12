import { Component, NO_ERRORS_SCHEMA, OnInit } from '@angular/core';
import { MeasurementModel } from '../../models/measurement'; 
import { ObservableArray } from '@nativescript/core'; 
import { NativeScriptUIChartModule } from 'nativescript-ui-chart/angular'; 
import { MeasurementService } from '~/app/services/measurements.service';

@Component({
  selector: 'app-chart',
  standalone: true,
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css'],
  imports: [NativeScriptUIChartModule],
  schemas: [NO_ERRORS_SCHEMA],
})
export class ChartComponent implements OnInit {

  public measurementsData: ObservableArray<MeasurementModel> = new ObservableArray([]);
  public filteredMeasurements: MeasurementModel[] = [];

  constructor(private measurementService: MeasurementService) {}

  ngOnInit(): void {
    this.loadMeasurements();
  }

  loadMeasurements(): void {
    this.measurementService.getAllMeasurements().subscribe({
      next: (data) => {
        this.measurementsData = new ObservableArray(data || []);
        this.filteredMeasurements = data || [];
      },
      error: (err) => {
        console.log('Error: ', err);
        this.measurementsData = new ObservableArray([]);
        this.filteredMeasurements = [];
      },
    });
  }

  formatDate(timestamp: string): string {
    const date = new Date(timestamp);
    const day = ('0' + date.getDate()).slice(-2);
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const year = date.getFullYear().toString().slice(-2);
    return `${day}/${month}/${year}`;
  }

  // Rendezze az adatokat időpont szerint (timestamp alapján)
  get chartData() {
    const sortedData = this.measurementsData.slice().sort((a, b) => {
      const dateA = new Date(a.timestamp).getTime();
      const dateB = new Date(b.timestamp).getTime();
      return dateA - dateB;
    });

    return sortedData.map((item, index) => ({
      date: this.formatDate(item.timestamp),
      temperature: item.temperature,
      humidity: item.humidity,
      index: index,
    }));
  }
}

