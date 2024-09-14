import { Component } from '@angular/core';
import {AuthenticationService} from "../../services/services/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.scss'
})
export class ActivateAccountComponent {

  message: string= '';
  isOkay: boolean = true;//if account activation is ok
  submitted: boolean = false;//if the user has already submitted the activation code

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {
  }


  onCodeCompleted(token: string) {
    this.confirmAccount(token);
  }

  redirectToLogin() {
    this.router.navigate(['/login']);
  }

  private confirmAccount(token: string) {
    this.authService.confirm(
      {
        token
      }).subscribe({
      next: () => {
        this.message = 'Account activated successfully.\nYou can now login.';
        this.submitted = true;
        this.isOkay = true;
      },
      error:() => {
        this.message = 'Token is invalid or expired.';
        this.submitted = true;
        this.isOkay = false;
      }
    });
  }
}
