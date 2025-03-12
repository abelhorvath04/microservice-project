import { Routes } from '@angular/router';
import { HomePageComponent } from './sites/index/index.component';
import { SensorDataComponent } from './sites/sensor-data/sensor-data.component';
import { ChartComponent } from './sites/chart/chart.component';
import { MeasurementComponent } from './sites/measurement/measurement.component';

export const routes: Routes = [
    { path: '', component: HomePageComponent },
    { path: 'sensors/:id', component: SensorDataComponent },
    { path: 'chart', component: ChartComponent },
    {path: 'measurement', component: MeasurementComponent }
    ];
