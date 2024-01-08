import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from '../services/storage/storage.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup |undefined;

  constructor(private service:AuthService,private fb:FormBuilder,
    private router:Router ,private snackbar:MatSnackBar) { }

  ngOnInit(): void {
    this.loginForm=this.fb.group({
      email:['',Validators.required],
      password:['',Validators.required]
    })
  }

  login(){
    console.log(this.loginForm.value);
    this.service.login(
      this.loginForm.get(['email'])!.value,
      this.loginForm.get(['password'])!.value,
    ).subscribe((response)=>{
      console.log(response);
      if(StorageService.isAdminLoggedIn()){
        this.snackbar.open("Admin Login successfully","Close",{duration:5000});
        this.router.navigateByUrl("admin/students");
      }else if(StorageService.isStudentLoggedIn()){
        this.snackbar.open("Student Login successfully","Close",{duration:5000});
        this.router.navigateByUrl("student/dashboard");
      }
      else{
        this.snackbar.open("Bad credentials","Close",{duration:5000});
      }
      
    }) 
  }

  
  
}
