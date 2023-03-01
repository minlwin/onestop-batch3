import { Directive, ElementRef, Input, OnInit, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appBackColor]'
})
export class BackColorDirective implements OnInit{

  @Input()
  appBackColor?:string

  constructor(private eleRef:ElementRef, private renderer: Renderer2) {
  }

  ngOnInit(): void {
    this.renderer.setStyle(this.eleRef.nativeElement,
      'background-color', this.appBackColor || 'yellow')
  }
}
