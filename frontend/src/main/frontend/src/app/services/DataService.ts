import { Injectable } from '@angular/core';
import { Greeting } from '../models/Greeting'
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class DataService {
    constructor (private http: Http) {}

    private dataUrl="greetings.ajax";
    public getGreetings = function(): Observable<Greeting[]> {
    return this.http.get(this.dataUrl)
                    .map(this.extractData)
                    .catch(this.handleError);
    }
    private extractData(res: Response) {
        let body = res.json();
        return body || { };
    }

  private handleError (error: Response | any) {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

}
