import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpClientModule} from '@angular/common/http'
import { AdminRoutingModule } from './admin-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './admin-components/dashboard/dashboard.component';
import { PostStudentComponent } from './admin-components/post-student/post-student.component';
import { AllStudentsComponent } from './admin-components/all-students/all-students.component';

//////
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { UpdateStudentComponent } from './admin-components/update-student/update-student.component';
import { PayFeeComponent } from './admin-components/pay-fee/pay-fee.component';
import { AllLeavesComponent } from './admin-components/all-leaves/all-leaves.component';
import {MatMenuModule} from '@angular/material/menu';
import { PostTeacherComponent } from './admin-components/post-teacher/post-teacher.component';
import { GetAllTeachersComponent } from './admin-components/get-all-teachers/get-all-teachers.component';
import { UpdateTeacherComponent } from './admin-components/update-teacher/update-teacher.component';





@NgModule({
  declarations: [
    DashboardComponent,
    PostStudentComponent,
    AllStudentsComponent,
    UpdateStudentComponent,
    PayFeeComponent,
    AllLeavesComponent,
    PostTeacherComponent,
    GetAllTeachersComponent,
    UpdateTeacherComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,

    /////
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatMenuModule


  ]
})
export class AdminModule { }
