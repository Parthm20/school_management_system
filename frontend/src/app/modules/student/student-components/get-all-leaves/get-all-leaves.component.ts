import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student-service/student.service';

@Component({
  selector: 'app-get-all-leaves',
  templateUrl: './get-all-leaves.component.html',
  styleUrls: ['./get-all-leaves.component.scss']
})
export class GetAllLeavesComponent implements OnInit {
  isSpinning = false;
  leaves: any;

  constructor(private service: StudentService) {}

  ngOnInit(): void {
    this.getAllLeaves();
  }

  getAllLeaves() {
    this.isSpinning = true;
    this.service.getAllAppliedLeavesByStudentId().subscribe(
      (res) => {
        console.log(res);
        this.leaves = res;
        this.isSpinning = false;
      }
    );
  }
}