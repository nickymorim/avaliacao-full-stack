import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Scheduling } from '../models/scheduling.model';

const API = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class SchedulingService {

  private readonly scheduledTranfers: Scheduling[]

  constructor(
    private httpClient: HttpClient
  ) { 
    this.scheduledTranfers = [];
  }

  create(scheduling: Scheduling) {
    console.log("Criando agendamento");
    return this.httpClient.post(`${API}/bank/scheduling/`, scheduling);
  }

  list() {
    console.log("Listando agendamentos");
    return this.httpClient.get<Scheduling[]>(`${API}/bank/scheduling/`);
  }
}
