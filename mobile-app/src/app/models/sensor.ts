export interface Sensor {
  id: number;
  name: string;
  location: string;
  active: boolean;
  type: string;
}

export interface SensorCreateRequest {
  name: string;       
  location: string;   
  active: boolean;    
  type: string;       
}
