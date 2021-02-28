import { Component, OnInit } from '@angular/core';
import {ApiService} from '../services/api.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Weight} from '../models/Weight';
import {AirportStats} from '../models/AirportStats';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  flightNumbers: number[];
  departureDates: string[];
  iataCodes: string[];
  weight: Weight;
  airportStats: AirportStats;
  hasError = false;
  airportStatsError = false;

  constructor(private apiService: ApiService) { }

  weightForm = new FormGroup({
    flightNumber: new FormControl('', [Validators.required]),
    departureDate: new FormControl('', [Validators.required])
  });

  airportStatsForm = new FormGroup({
    iataCode: new FormControl('', [Validators.required]),
    date: new FormControl('', [Validators.required])
  });

  ngOnInit(): void {
    this.apiService.getFlights().subscribe(res => this.flightNumbers = res);
    this.apiService.getDepartureDates().subscribe(res => this.departureDates = res);
    this.apiService.getIataCodes().subscribe(res => this.iataCodes = res);
  }

  onSubmit() {
    this.weight = null;
    this.hasError = false;
    if (false === this.weightForm.valid) {
      return;
    }
    const onSuccess = (res) => {
      this.weight = res;
    };
    const onError = (err) => {
      console.log(err);
      this.hasError = true;
    };

    this.apiService.getWeightFor(this.weightForm.value.flightNumber, this.weightForm.value.departureDate)
      .subscribe(onSuccess, onError);

  }

  onSubmitAirportStats() {
    console.log(this.airportStatsForm)
    this.airportStatsError = false;
    this.airportStats = null;
    if (false === this.airportStatsForm.valid) {
      return;
    }
    const onSuccess = (res) => {
      this.airportStats = res;
    };
    const onError = (err) => {
      console.log(err);
      this.hasError = true;
    };

    this.apiService.getAirportStats(this.airportStatsForm.value.iataCode, this.airportStatsForm.value.date)
      .subscribe(onSuccess, onError);

  }

}
