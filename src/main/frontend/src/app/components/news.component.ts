import { Component } from '@angular/core';
import { Greeting } from '../models/Greeting';
import { DataService } from '../services/DataService'
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-news',
  templateUrl: '../templates/news.component.html',
  styleUrls: ['../styles/news.component.css']
})
export class NewsComponent implements OnInit {
  title = 'News Component';
  greetings: Greeting[] = [];
  errorMessage: string;

  constructor(private dataService: DataService) {
  }

  ngOnInit(): void {
    this.dataService.getGreetings().
      subscribe( greetings => this.greetings = greetings, 
      error => this.errorMessage = <any>error );
  }
}
