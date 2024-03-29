import { Injectable } from '@angular/core';
import { AbstractControl } from '@angular/forms';

/**
 * Shared service.
 */
@Injectable({
  providedIn: 'root'
})
export class SharedService {

  /**
   * Get form control error message.
   * @param ctrl From control.
   * @returns Error message.
   */
  public getFormControlErrorText(ctrl: AbstractControl): string {
    if (ctrl.hasError('required')) {
      return 'Ce champ est requis';
    } else if (ctrl.hasError('email')) {
      return 'Merci d\'entrer une adresse mail valide';
    } else if (ctrl.hasError('minlength')) {
      return 'Ce champ ne contient pas assez de caractères';
    } else if (ctrl.hasError('maxlength')) {
      return 'Ce champ contient trop de caractères';
    } else {
      return 'Ce champ contient une erreur';
    }
  }
}
