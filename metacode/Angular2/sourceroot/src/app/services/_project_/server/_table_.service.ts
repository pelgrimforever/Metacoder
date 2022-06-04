//Metacoder: NO AUTHOMATIC UPDATE
//Change function definitions here, NOT in :table:base.service.ts
/* 
    Created on : Nov 20, 2018, 10:39:01 AM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/
import { HttpClient, HttpParams } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable"
import { map } from 'rxjs/operators';

import { :Table:BaseService } from './:table:-base.service';

@Injectable({
	providedIn: 'root'
})

export class :Table:Service extends :Table:BaseService {
	constructor(public http: HttpClient) {
        super(http);
    }
}

