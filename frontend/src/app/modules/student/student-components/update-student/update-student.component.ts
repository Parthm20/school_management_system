import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { StudentService } from '../student-service/student.service';

@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.scss']
})
export class UpdateStudentComponent implements OnInit {

  student:any;
  isSpinning:boolean=false;
  validateForm:FormGroup;

  CLASS :string[]=[
    "Play Group","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
  ]
  GENDER:string[]=[
    "Male","Female","Other"
  ]

  constructor(private service:StudentService ,
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
    this.service.getStudentById().subscribe((res)=>{
      const student =res.studentDto;
      this.validateForm.patchValue(student);
      console.log(res)
    })
  }
  updateStudent() {
    this.isSpinning=true;
      this.service.updateStudent( this.validateForm.value).subscribe(
        (res) => {
          console.log(res)
          if (res.id == null) {
            this.snackbar.open("Student updated successfully", "Close", { duration: 5000 });
            this.getStudentById();
            this
          } else {
            this.snackbar.open("Student not found", "Close", { duration: 5000 });
          }
        }
      );
   
  }
  
}
