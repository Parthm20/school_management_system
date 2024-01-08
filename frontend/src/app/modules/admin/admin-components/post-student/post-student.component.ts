import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-post-student',
  templateUrl: './post-student.component.html',
  styleUrls: ['./post-student.component.scss']
})
export class PostStudentComponent implements OnInit {
  isSpinning:boolean;
  validateForm:FormGroup;

  
  CLASS :string[]=[
    "Play Group","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
  ]
  GENDER:string[]=[
    "Male","Female","Other"
  ]
  confirmationValidator=(control : FormControl):{[s:string]:boolean} =>{
    if(!control.value){
      return {required : true};
    }else if(control.value !== this.validateForm.controls["password"].value){
      return {confirm :true,error:true};
    }
    return {};
   }
  
  constructor(private service:AdminService,private fb:FormBuilder,
    private snackbar :MatSnackBar) { }

  ngOnInit():void {
    this.validateForm=this.fb.group({
      email:['',Validators.required],
      password:['',Validators.required],
      checkPassword:['',Validators.required , this.confirmationValidator],
      name:['',Validators.required],
      fatherName:['',Validators.required],
      motherName:['',Validators.required],
      studentClass:['',Validators.required],
      dob:['',Validators.required],
      address:['',Validators.required],
      gender:['',Validators.required],
    })
  }
  
  

  postStudent(){
    console.log(this.validateForm.value);
    this.isSpinning=true;
    this.service.addStudent(this.validateForm.value).subscribe((res)=>{
      this.isSpinning=false;
      if(res.id != null){
        this.snackbar.open("Student posted successfully","Close",{duration:5000});
      }else{
        this.snackbar.open("Student already exist","Close",{duration:5000});
      }
      console.log(res);
    })

  }
 
   
  

}
