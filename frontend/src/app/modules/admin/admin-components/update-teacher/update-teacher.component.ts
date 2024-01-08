import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-update-teacher',
  templateUrl: './update-teacher.component.html',
  styleUrls: ['./update-teacher.component.scss']
})
export class UpdateTeacherComponent implements OnInit {
  teacherId:number=this.activatedRoute.snapshot.params['teacherId']

  validateForm:FormGroup;
  isSpinning:boolean=false;

  GENDER:string[]=[
    "Male","Female","Other"
  ]

  constructor(private service:AdminService,private activatedRoute:ActivatedRoute,
    private fb:FormBuilder,private snackbar:MatSnackBar) { }

  ngOnInit(): void {
    this.getTeacherById();
    this.validateForm=this.fb.group({
      name :['',Validators.required],
      gender :['',Validators.required],
      department :['',Validators.required],
      qualification :['',Validators.required],
      address :['',Validators.required],
      dob :['',Validators.required],

    })
  }

  getTeacherById(){
    this.service.getTeacherById(this.teacherId).subscribe((res)=>{
      console.log(res)
      this.validateForm.patchValue(res.teacherDto);
    })
  }

  updateTeacher(){
    this.service.updateTeacher(this.teacherId,this.validateForm.value).subscribe((res)=>{
      console.log(res);
      if(res.id!=null){
          this.snackbar.open("Updated Teacher successfully","Close",{duration:5000});
      }
      else{
        this.snackbar.open("Something went wrong","Close",{duration:5000});
      }
    })

  }
}
