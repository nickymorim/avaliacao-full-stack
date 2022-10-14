import { Component, Input, OnInit } from '@angular/core';
import { Scheduling } from 'src/app/models/scheduling.model';
import { SchedulingService } from '../scheduling.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  @Input() scheduleList: Scheduling[] = [];

  constructor(
    private schedulingService: SchedulingService
  ) { }

  ngOnInit(): void {
    this.schedulingService.list().subscribe((schedules) => {
      console.log(`Lista de agendamentos: ${schedules}`);
      this.scheduleList = schedules;
    })
  }

}
