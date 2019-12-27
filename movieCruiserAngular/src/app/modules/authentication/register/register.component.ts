import { Component, OnInit } from '@angular/core';
import { user } from '../User';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { formArrayNameProvider } from '@angular/forms/src/directives/reactive_directives/form_group_name';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  newUser: user;

  constructor(private authService: AuthenticationService, private router: Router) {
    this.newUser=new user();
  }

  ngOnInit() {
  }

  registerUser(){
    this.authService.registerUser(this.newUser).subscribe((data)=>{
      console.log("user data: ", data);
      this.router.navigate(['/login']);
    })
  }

  resetInput(registerForm: NgForm){
    registerForm.reset();
  }

}
