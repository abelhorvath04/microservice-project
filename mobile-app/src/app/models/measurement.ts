export interface MeasurementModel {
  id: number;
  timestamp: string;
  temperature: number;
  humidity: number;
  sensorId: number;
}

export interface MeasurementCreateRequest {
  sensorId: number;
  timestamp: string;
  temperature: number;
  humidity: number;
}
