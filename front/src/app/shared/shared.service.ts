import { Injectable } from '@angular/core';
import { AbstractControl } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  public getFormControlErrorText(ctrl: AbstractControl): string {
    if (ctrl.hasError('required')) {
      return 'Ce champ est requis';
    } else if (ctrl.hasError('email')) {
      return 'Merci d\'entrer une adresse mail valide';
    } else if (ctrl.hasError('minlength')) {
      return 'Ce champ ne contient pas assez de caractères';
    } else if (ctrl.hasError('maxlength')) {
      return 'Ce champ contient trop de caractères';
    } else if (ctrl.hasError('pattern')) {
      return 'Ce champ doit contenir au minimum 8 caractères et au moins une majuscule, une minuscule, un chiffre et un caractère spécial';
    } else {
      return 'Ce champ contient une erreur';
    }
  }
}
