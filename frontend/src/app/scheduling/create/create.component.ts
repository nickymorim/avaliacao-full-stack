import { Component, OnInit } from '@angular/core';
import { SchedulingService } from '../scheduling.service';
import { Scheduling } from 'src/app/models/scheduling.model';

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
    private schedulingService: SchedulingService
  ) { }

  ngOnInit(): void {
  }

  createScheduling() {
    const scheduling = {} as Scheduling;
    scheduling.originAccount = this.originAccount;
    scheduling.destinationAccount = this.destinationAccount;
    scheduling.scheduleDate = this.scheduleDate;
    scheduling.transferValue = this.transferValue;
    scheduling.transactionType = this.transactionType;

    this.schedulingService.create(scheduling);
  }
}
