import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-get-all-teachers',
  templateUrl: './get-all-teachers.component.html',
  styleUrls: ['./get-all-teachers.component.scss']
})
export class GetAllTeachersComponent implements OnInit {

  teachers=[]
  constructor(private service:AdminService,private snackbar:MatSnackBar) { }

  ngOnInit(): void {
    this.getAllTeachers();
  }

  getAllTeachers(){
    this.service.getAllTeachers().subscribe((res)=>{
      console.log(res);
      this.teachers=res;
    })
  }
  deleteTeacher(teacherId:number){
    console.log(teacherId)
    this.service.deleteTeacher(teacherId).subscribe((res)=>{
      console.log(res);
      this.getAllTeachers()
      this.snackbar.open("Teacher Delete Successfully","Close",{duration:5000})
    })

  }

}
