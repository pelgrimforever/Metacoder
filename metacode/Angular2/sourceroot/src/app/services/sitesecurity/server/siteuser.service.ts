//Metacoder: NO AUTHOMATIC UPDATE
//Change function definitions here, NOT in sitesecurity-siteuser-json-base.js
/* 
    Created on : Nov 20, 2018, 10:39:01 AM
    Generated on 1.1.2016 17:37
    Author     : Franky Laseure
*/
import { HttpClient, HttpParams } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from "rxjs/Observable"

import { SiteuserBaseService } from './siteuser-base.service';
import { Siteuser } from '../data/siteuser';

@Injectable({
	providedIn: 'root'
})

export class SiteuserService extends SiteuserBaseService {
	
	SELECT_AUTHENTICATE = 9;

	constructor(public http: HttpClient) {
        super(http);
    }

	public authenticate(data): Observable<Siteuser> {
		const operation = this.SELECT_AUTHENTICATE;
		const body = { 
            "operation": operation, 
            "data": data
        };
		return this.http.post(this.url, body, this.httpOptions)
			.pipe(map(this.extractDataObject));
	}
}

