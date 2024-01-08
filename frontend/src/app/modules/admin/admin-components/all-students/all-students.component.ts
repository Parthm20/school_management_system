import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-all-students',
  templateUrl: './all-students.component.html',
  styleUrls: ['./all-students.component.scss']
})
export class AllStudentsComponent implements OnInit {
  students:any;

  constructor(private service:AdminService , private snackbar :MatSnackBar) { }

  ngOnInit() {
    this.getAllStudents();
  }

  getAllStudents(){
    this.service.getAllStudents().subscribe((res)=>{
      console.log(res);
      this.students=res;
    })
  }

  deleteStudent(studentId :number){
    this.service.deleteStudents(studentId).subscribe((res)=>{
      console.log(res);
      this.getAllStudents();
      this.snackbar.open("Student deleted successfully","Close",{duration:5000})
    })

  }
}
