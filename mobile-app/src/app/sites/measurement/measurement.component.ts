import { Component, NO_ERRORS_SCHEMA, OnInit } from "@angular/core";
import { MeasurementService } from '../../services/measurements.service';
import { MeasurementModel } from '../../models/measurement';
import { NativeScriptModule } from "@nativescript/angular";

@Component({
  selector: 'app-charts',
  templateUrl: './measurement.component.html',
  imports: [ NativeScriptModule ],
  styleUrls: ['./measurement.component.css'],
  schemas: [NO_ERRORS_SCHEMA]
})
export class MeasurementComponent implements OnInit {
  public measurements: MeasurementModel[] = [];
  public filteredMeasurements: MeasurementModel[] = [];

  constructor(private measurementService: MeasurementService) {}

  ngOnInit(): void {
    this.loadMeasurements();
  }

  loadMeasurements(): void {
    this.measurementService.getAllMeasurements().subscribe({
      next: (data) => {
        this.measurements = data || [];
        this.filteredMeasurements = this.measurements;
      },
      error: (err) => {
        console.log('Error: ', err);
        this.measurements = [];
        this.filteredMeasurements = [];
      },
    });
  }

  filterMeasurementsByPeriod(period: string): void {
    const now = new Date();
    let filtered: MeasurementModel[];

    switch (period) {
      case '24h':
        filtered = this.measurements.filter((item) =>
          new Date(item.timestamp).getTime() > now.getTime() - 24 * 60 * 60 * 1000
        );
        break;
      case '7d':
        filtered = this.measurements.filter((item) =>
          new Date(item.timestamp).getTime() > now.getTime() - 7 * 24 * 60 * 60 * 1000
        );
        break;
      case '30d':
        filtered = this.measurements.filter((item) =>
          new Date(item.timestamp).getTime() > now.getTime() - 30 * 24 * 60 * 60 * 1000
        );
        break;
      default:
        filtered = this.measurements;
    }

    this.filteredMeasurements = filtered;
  }
}
