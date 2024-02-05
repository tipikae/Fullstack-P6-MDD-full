import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedService } from './shared.service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule
  ], 
  exports: [
    MaterialModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    SharedService
  ]
})
export class SharedModule { }
