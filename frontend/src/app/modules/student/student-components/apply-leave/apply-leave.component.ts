import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StudentService } from '../student-service/student.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-apply-leave',
  templateUrl: './apply-leave.component.html',
  styleUrls: ['./apply-leave.component.scss']
})
export class ApplyLeaveComponent implements OnInit {
  isSpinning: boolean=false;
  validateForm:FormGroup;

  constructor(private fb:FormBuilder,private service:StudentService,
    private snackbar:MatSnackBar,private router:Router) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      subject: ['', Validators.required],
      body: ['',Validators.required],
    });
  }
  
  applyLeave() {
    this.isSpinning = true;
    console.log(this.validateForm.value);
    this.service.applyLeave(this.validateForm.value).subscribe(
      (res) => {
        console.log(res);
        this.isSpinning = false;
        if (res.id != null) {
          this.snackbar.open('Leave submitted successfully', 'SUCCESS', { duration: 5000 });
          this.router.navigateByUrl('student/dashboard');
        } else {
          this.snackbar.open('Something went wrong', 'ERROR', { duration: 5000 });
        }
      }
    );
  }
  

}
