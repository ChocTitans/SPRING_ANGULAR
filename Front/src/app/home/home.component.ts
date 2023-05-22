import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HomeService} from "./home.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
  public helloMessage: string = "";
  constructor(
    private httpClient: HttpClient,
    private homeService: HomeService
  ) { }

  ngOnInit(): void {
    this.homeService.getHelloMessage().subscribe(
      (data) => {
        this.helloMessage = data.message;
      }
    )
  }
}
