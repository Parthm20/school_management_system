import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-pay-fee',
  templateUrl: './pay-fee.component.html',
  styleUrls: ['./pay-fee.component.scss']
})
export class PayFeeComponent implements OnInit {
  studentId:number=this.activatedRoute.snapshot.params['studentId'];
  isSpinning:boolean=false;
  validateForm: FormGroup;

  MONTH: string[] = [
    'January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December'
  ];
  constructor(private service:AdminService,private fb:FormBuilder, 
    private activatedRoute :ActivatedRoute, private snackbar:MatSnackBar) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      amount: ['', Validators.required],
      month: ['', Validators.required],
      givenBy: ['', Validators.required],
      description: ['', Validators.required],
    });
  }

  payFee(){
    console.log(this.validateForm.value);
    this.service.payFee(this.studentId,this.validateForm.value).subscribe((res)=>{
      console.log(res)
      if(res.id ==null){
        this.snackbar.open("Fee paid successfully","Close",{duration:5000});
      }
      else{this.snackbar.open("Something went wrong","Close",{duration:5000});}
    })

  }

}
