import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './components/app.component';
import { RouterComponent } from './components/router.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { TableComponent } from './components/table.component';
import { NewsComponent } from './components/news.component';
import { FooterComponent } from './components/footer.component';
import { DataService } from './services/DataService'

const appRoutes: Routes = [
  { path: 'TableComponent', component: TableComponent },
  { path: 'NewsComponent', component: NewsComponent },
];


@NgModule({
  declarations: [
    AppComponent,
    TableComponent,
    NewsComponent,
    RouterComponent,
    FooterComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}, DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
