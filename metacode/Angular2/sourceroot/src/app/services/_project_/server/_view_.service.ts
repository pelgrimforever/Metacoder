//Metacoder: NO AUTHOMATIC UPDATE
//Change function definitions here, NOT in :table:base.service.ts
/* 
    Created on : Dec 16, 2018, 18:30:01
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

