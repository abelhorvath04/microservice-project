import {
  AfterViewInit,
  Component,
  inject,
  NO_ERRORS_SCHEMA,
  OnInit,
  signal
} from "@angular/core";
import {
  NativeScriptCommonModule,
  NativeScriptRouterModule,
} from "@nativescript/angular";
import { MeasurementService } from "../../services/measurements.service";
import { SensorService } from "../../services/sensor.service";
import { MeasurementModel } from "../../models/measurement";
import { ActivatedRoute } from "@angular/router";
import { Sensor } from "~/app/models/sensor";
import { DatePipe } from "@angular/common";
import { LineSeries } from "nativescript-ui-chart";

@Component({
  selector: "app-sensors-detail-page",
  standalone: true,
  imports: [NativeScriptCommonModule, NativeScriptRouterModule, DatePipe],
  schemas: [NO_ERRORS_SCHEMA],
  templateUrl: "./sensor-data.component.html",
  styleUrls: ["./sensor-data.component.css"],
})
export class SensorDataComponent implements OnInit, AfterViewInit {

  route = inject(ActivatedRoute);
  sensor = signal<Sensor | null>(null);
  measurements = signal<MeasurementModel[]>([]);
  lastMeasurement = signal<MeasurementModel | null>(null);

  lineSeries: LineSeries;

  constructor(
    private measurementService: MeasurementService,
    private sensorService: SensorService
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.params.id;

    this.sensorService.getAllSensors().subscribe({
      next: (data) => {
        const foundSensor = data.find((sensor) => sensor.id === id);
        this.sensor.set(foundSensor || null);
        this.loadMeasurements();
      },
      error: (err) => {
        console.log("Hiba: ", err);
        this.sensor.set(null);
      },
    });
  }

  ngAfterViewInit(): void {
  }

  loadMeasurements(): void {
    this.measurementService.getAllMeasurements().subscribe({
      next: (data) => {
        this.measurements.set(data || []);

        this.measurements.set(
          this.measurements()
            .filter((measurement) => String(measurement.sensorId) === String(this.sensor()?.id))
            .sort(
              (a, b) =>
                new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime()
            )
        );

        this.lastMeasurement.set(this.measurements()[0] || null);
      },
      error: (err) => {
        console.log("Error: ", err);
        this.measurements.set([]);
      },
    });
  }

  filterLast10Measurements(): void {
    const last10 = this.measurements().slice(0, 10);
    this.measurements.set(last10);
  }
}
