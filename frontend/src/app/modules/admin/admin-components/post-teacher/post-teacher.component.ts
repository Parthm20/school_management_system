import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-post-teacher',
  templateUrl: './post-teacher.component.html',
  styleUrls: ['./post-teacher.component.scss']
})
export class PostTeacherComponent implements OnInit {

  validateForm:FormGroup;
  isSpinning:boolean=false;

  GENDER:string[]=[
    "Male","Female","Other"
  ]

  constructor(private service:AdminService,private fb:FormBuilder
    ,private snackbar:MatSnackBar) { }

  ngOnInit(): void {
    this.validateForm=this.fb.group({
      name :['',Validators.required],
      gender :['',Validators.required],
      department :['',Validators.required],
      qualification :['',Validators.required],
      address :['',Validators.required],
      dob :['',Validators.required],

    })
  }

  postTeacher(){
    console.log(this.validateForm.value);
    this.service.postTeacher(this.validateForm.value).subscribe((res)=>{
      console.log(res)
      if(res.id != null){
        this.snackbar.open("Teacher Posted successfully","Close",{duration:5000});
      }
      else{
        this.snackbar.open("Something went wrong","Close",{duration:5000});

      }
    })
  }

}
