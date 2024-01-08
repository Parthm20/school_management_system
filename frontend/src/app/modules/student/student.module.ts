import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { DashboardComponent } from './student-components/dashboard/dashboard.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ApplyLeaveComponent } from './student-components/apply-leave/apply-leave.component';
import { GetAllLeavesComponent } from './student-components/get-all-leaves/get-all-leaves.component';
import { UpdateStudentComponent } from './student-components/update-student/update-student.component';



@NgModule({
  declarations: [
    DashboardComponent,
    ApplyLeaveComponent,
    GetAllLeavesComponent,
    UpdateStudentComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    //
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule
  ]
})
export class StudentModule { }
