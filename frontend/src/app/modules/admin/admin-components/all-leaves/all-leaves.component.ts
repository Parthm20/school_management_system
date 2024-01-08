import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-all-leaves',
  templateUrl: './all-leaves.component.html',
  styleUrls: ['./all-leaves.component.scss']
})
export class AllLeavesComponent implements OnInit {

  leaves :any;
  constructor(private service:AdminService,private snackbar:MatSnackBar) { }

  ngOnInit(): void {
    this.getAllLeaves();
  }

  getAllLeaves(){
    this.service.getAllAppliedLeaves().subscribe((res)=>{
      console.log(res);
      this.leaves=res;
    })
  }

  changeLeaveStatus(leaveId: number, status: string): void {
    this.service.changeLeaveStatus(leaveId, status).subscribe(
      (res) => {
        console.log(res); 
        
        if (res.id !== null) {
          this.snackbar.open('Leave approved successfully', 'Close', {
            duration: 5000
          });
          this.getAllLeaves(); 
        } else {
          this.snackbar.open('Something went wrong', 'ERROR', {
            duration: 5000
          });
        }
      });
  }

}
