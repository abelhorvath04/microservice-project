import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgChartsModule } from 'ng2-charts';
import { ChartConfiguration, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-mainpage',
  standalone: true,
  imports: [CommonModule, FormsModule, NgChartsModule],
  templateUrl: './mainpage.component.html',
  styleUrl: './mainpage.component.css'
})
export class MainpageComponent implements OnInit {

  @ViewChild(BaseChartDirective) chart!: BaseChartDirective;

  measurements: any[] = [];
  sensors: any[] = [];
  activeSensors: any[] = [];
  loading: boolean = true; 

  newMeasurement = {
    sensorId: null,
    temperature: null,
    humidity: null,
  };
  newSensor = { name: '', location: '', active: true, type: '' }; // Model for creating/updating sensors

  lineChartType: ChartType = 'line';

  editMode = false; // Determines if we're editing a sensor
  selectedSensor: any = null; // Holds the sensor currently being edited

  ngAfterViewInit(): void {
    window.scrollTo(0, 0);
  }

  lineChartData: ChartConfiguration['data'] = {
    datasets: [
      {
        data: [],
        label: 'Temperature (°C)',
        borderColor: 'rgba(75,192,192,1)',
        fill: false,
      },
      {
        data: [],
        label: 'Humidity (%)',
        borderColor: 'rgba(153,102,255,1)',
        fill: false,
      },
    ],
    labels: [],
  };

  lineChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    plugins: {
      legend: {
        display: true,
      },
    },
  };

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchAllData();
  }

  fetchAllData(): void {
    this.loading = true; // Indicate loading
    
    forkJoin({
      sensors: this.http.get<any[]>('http://localhost:8080/sensors'),
      measurements: this.http.get<any[]>('http://localhost:8080/measurements'),
    }).subscribe(
      ({ sensors, measurements }) => {
        this.activeSensors = sensors.filter(sensor => sensor.active);
        this.sensors = sensors;
        this.measurements = measurements;

        // Update chart data
        this.lineChartData.labels = measurements.map((m) =>
          new Date(m.timestamp).toLocaleTimeString()
        );
        this.lineChartData.datasets[0].data = measurements.map((m) => m.temperature);
        this.lineChartData.datasets[1].data = measurements.map((m) => m.humidity);

        this.loading = false; // Data is ready
        if (this.chart) {
          this.chart.update();
        }
      },
      (error) => {
        console.error('Error fetching data:', error);
        this.loading = false; // In case of error
      }
    );
  }

  isValidTemperature(): boolean {
    return this.newMeasurement.temperature !== null &&
      this.newMeasurement.temperature >= -100 &&
      this.newMeasurement.temperature <= 100;
  }

  isValidHumidity(): boolean {
    return this.newMeasurement.humidity !== null &&
      this.newMeasurement.humidity >= 0 &&
      this.newMeasurement.humidity <= 100;
  }

  fetchMeasurements(): void {
    this.http.get<any[]>('http://localhost:8080/measurements').subscribe(
      (response) => {
        this.measurements = response;

        this.lineChartData.labels = response.map((m) =>
          new Date(m.timestamp).toLocaleTimeString()
        );
        this.lineChartData.datasets[0].data = response.map((m) => m.temperature);
        this.lineChartData.datasets[1].data = response.map((m) => m.humidity);

        if (this.chart) {
          this.chart.update();
        }
      },
      (error) => console.error('Error fetching measurements:', error)
    );
  }

  addMeasurement(): void {

    const measurement = {
      sensorId: this.newMeasurement.sensorId,
      timestamp: new Date().toISOString(),
      temperature: this.newMeasurement.temperature,
      humidity: this.newMeasurement.humidity,
    };

    this.http.post('http://localhost:8080/measurements', measurement).subscribe(
      (response) => {
        console.log('Measurement added successfully:', response);
        this.fetchMeasurements();
        this.newMeasurement = { sensorId: null, temperature: null, humidity: null }; // Reset form
      },
      (error) => {
        console.error('Error adding measurement:', error);
      }
    );
  }

  fetchSensors(): void {
    this.http.get<any[]>('http://localhost:8080/sensors').subscribe(
      (response) => {
        this.sensors = response;
      },
      (error) => {
        console.error('Error fetching objects', error);
      }
    )
  }

  createSensor(): void {
    if (!this.newSensor.name || !this.newSensor.location || !this.newSensor.type) {
      return;
    }

    this.http.post('http://localhost:8080/sensors', this.newSensor).subscribe(
      (response) => {
        console.log('Sensor created:', response);
        this.fetchSensors();
        this.newSensor = { name: '', location: '', active: true, type: '' }; // Reset form
      },
      (error) => {
        console.error('Error creating sensor:', error);
      }
    );
  }

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

  editSensor(sensor: any): void {
    setTimeout(() => {
      this.scrollToElement("editSensor");
    }, 1);
    this.editMode = true; // Enable edit mode
    this.selectedSensor = { ...sensor }; // Clone the sensor to avoid direct modification
  }

  updateSensor(): void {
    if (this.selectedSensor) {
      this.http
        .put(`http://localhost:8080/sensors/${this.selectedSensor.id}`, this.selectedSensor)
        .subscribe(
          (response) => {
            console.log('Sensor updated:', response);
            this.fetchSensors(); // Refresh the sensor list
            this.cancelEdit(); // Exit edit mode
          },
          (error) => {
            console.error('Error updating sensor:', error);
          }
        );
    }
  }

  cancelEdit(): void {
    this.scrollToElement("sensorTable");
    this.editMode = false; // Disable edit mode
    this.selectedSensor = null; // Clear the selected sensor
  }

  deleteSensor(sensorId: number): void {
    console.log('Deleting sensor with ID:', sensorId); // Debug log

    if (confirm('Are you sure you want to delete this sensor?')) {
      this.http.delete(`http://localhost:8080/sensors/${sensorId}`).subscribe(
        () => {
          console.log(`Sensor with ID ${sensorId} deleted successfully`);
          this.fetchSensors(); // Refresh the sensor list
        },
        (error) => {
          console.error('Error deleting sensor:', error);
        }
      );
    }
  }
}
