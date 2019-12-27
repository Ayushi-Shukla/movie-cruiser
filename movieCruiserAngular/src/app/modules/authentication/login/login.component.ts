import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { user } from '../User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  newUser: user;

  constructor(private authService: AuthenticationService, private router: Router) {
    this.newUser=new user();
  }

  ngOnInit() {
  }

  loginUser(){
    console.log(this.newUser);
    this.authService.loginUser(this.newUser).subscribe((data)=>{
      console.log("Logged in!!! ");
      console.log(data);

      if (data['token']){
        this.authService.setToken(data['token']);
        console.log('token' , data['token']);
        this.router.navigate(['/movies/popular']);
      }
    })
  }

}
