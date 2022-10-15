import { Component, OnInit } from '@angular/core';
import { SchedulingService } from '../scheduling.service';
import { Scheduling } from 'src/app/models/scheduling.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  originAccount = "";
  destinationAccount = "";
  scheduleDate = "";
  transferValue = 0;
  transactionType = "";

  constructor(
    private schedulingService: SchedulingService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  createScheduling() {
    const scheduling = {} as Scheduling;
    scheduling.originAccount = this.originAccount;
    scheduling.destinationAccount = this.destinationAccount;
    scheduling.scheduleDate = this.scheduleDate;
    scheduling.value = this.transferValue;
    scheduling.transactionType = this.transactionType;

    this.schedulingService.create(scheduling).subscribe((scheduling) => {
      console.log(scheduling);
      this.router.navigate(['list']);
    }, (error) => {
      alert('Erro ao criar agendamento');
    });
  }
}
