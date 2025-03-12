import { Component, NO_ERRORS_SCHEMA, OnInit } from "@angular/core";
import { NativeScriptCommonModule, NativeScriptRouterModule } from "@nativescript/angular";
import { MeasurementService } from "../../services/measurements.service";
import { SensorService } from "../../services/sensor.service";
import { Sensor } from "~/app/models/sensor";
import { MeasurementModel } from "~/app/models/measurement";

@Component({
  selector: "app-home-page",
  standalone: true,
  imports: [NativeScriptCommonModule, NativeScriptRouterModule],
  schemas: [NO_ERRORS_SCHEMA],
  templateUrl: "./index.component.html",
  styleUrls: ["./index.component.css"],
})
export class HomePageComponent implements OnInit {
  public sensors: Sensor[] = [];
  public measurements: MeasurementModel[] = [];
  public filteredMeasurements: MeasurementModel[] = [];
  public selectedPeriod: string = "24h";  // Default period for filtering

  constructor(
    private measurementService: MeasurementService,
    private sensorService: SensorService
  ) {}

  ngOnInit(): void {
    this.loadMeasurements();
    this.loadSensors();
  }

  loadMeasurements(): void {
    this.measurementService.getAllMeasurements().subscribe({
      next: (data) => {
        this.measurements = data || [];
        this.filterMeasurementsByPeriod(this.selectedPeriod);  // Apply initial filter
      },
      error: (err) => {
        console.log("Error: ", err);
        this.measurements = [];
      },
    });
  }

  loadSensors(): void {
    this.sensorService.getAllSensors().subscribe({
      next: (data) => {
        this.sensors = data || [];
      },
      error: (err) => {
        console.log("Error: ", err);
        this.sensors = [];
      },
    });
  }

  // Function to filter measurements based on selected period
  filterMeasurementsByPeriod(period: string): void {
    const now = new Date();
    let startDate: Date;

    if (period === "24h") {
      startDate = new Date(now.setHours(now.getHours() - 24));
    } else if (period === "7d") {
      startDate = new Date(now.setDate(now.getDate() - 7));
    } else if (period === "30d") {
      startDate = new Date(now.setDate(now.getDate() - 30));
    }

    this.filteredMeasurements = this.measurements.filter((measurement) => {
      const measurementDate = new Date(measurement.timestamp);  // Use the timestamp field
      return measurementDate >= startDate;
    });
  }

  // Method to change the filter when a new period is selected
  onPeriodChange(period: string): void {
    this.selectedPeriod = period;
    this.filterMeasurementsByPeriod(period);
  }
}
