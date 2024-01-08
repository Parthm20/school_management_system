import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.scss']
})
export class UpdateStudentComponent implements OnInit {
  studentId:number=this.activatedRoute.snapshot.params['studentId']

  isSpinning:boolean;
  validateForm:FormGroup;

  CLASS :string[]=[
    "Play Group","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
  ]
  GENDER:string[]=[
    "Male","Female","Other"
  ]

  constructor(private service:AdminService ,private activatedRoute:ActivatedRoute,
    private fb:FormBuilder,private snackbar:MatSnackBar) { }

  ngOnInit(): void {
    
    this.validateForm=this.fb.group({
      email:['',Validators.required],
      name:['',Validators.required],
      fatherName:['',Validators.required],
      motherName:['',Validators.required],
      studentClass:['',Validators.required],
      dob:['',Validators.required],
      address:['',Validators.required],
      gender:['',Validators.required],
    })
    this.getStudentById()

  }

  getStudentById(){
    this.service.getStudentById(this.studentId).subscribe((res)=>{
      const student =res.studentDto;
      this.validateForm.patchValue(student);
      console.log(res)
    })
  }
  updateStudent() {
    if (this.studentId && this.validateForm.value) {
      this.service.updateStudent(this.studentId, this.validateForm.value).subscribe(
        (res) => {
          if (res.id == null) {
            this.snackbar.open("Student updated successfully", "Close", { duration: 5000 });
          } else {
            this.snackbar.open("Student not found", "Close", { duration: 5000 });
          }
        },
        (error) => {
          console.error("Error occurred:", error);
          // Handle error - display an error message or take appropriate action
        }
      );
    } else {
      console.error("Missing studentId or form value");
      // Handle missing data case
    }
  }
  
}
