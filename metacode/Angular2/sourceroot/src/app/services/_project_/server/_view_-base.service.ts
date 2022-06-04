//Auto generated code
//don't change things here, it will be overwritten
//redefine classes in :table:.service.ts
/* 
    Created on : Dec 16, 2018, 18:24:01
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from "rxjs/Observable"

import { ServerService } from './server.service';
import { :Table: } from '../data/:table:';
import { :Table:Json } from '../conversion/:table:-json';

export class :Table:BaseService extends ServerService {
    //SELECT OPERATIONS
    SELECT_ALL: 1;
	
    url = this.WEBSERVICE + "rs:tablename:";
    urlSELECT = this.url + "_select";

	constructor(public http: HttpClient) { 
        super();
	}

	protected extractDataArray(res: Response): :Table:[] {
		let :table:s: :Table:[] = [];
		//check if res.status excists, this means no data has returned
		if (typeof res.status == 'undefined') {
			const json:table:s = JSON.parse(JSON.stringify(res))
            const :table:json: :Table:Json = new :Table:Json();
			for(let i = 0; i < json:table:s.length; i++) {
				:table:s.push(:table:json.fromJSON(json:table:s[i]));
			}
		} else {
			console.log("Webservice return status: " + res.status);
		}
    	return :table:s;
	}

	protected extractDataObject(res: Response): :Table: {
		let :table:: :Table: = null;
		//check if res.status excists, this means no data has returned
		if (typeof res.status == 'undefined') {
			const json:table: = JSON.parse(JSON.stringify(res))
            const :table:json: :Table:Json = new :Table:Json();
    		:table: = :table:json.fromJSON(json:table:);
		} else {
			console.log("Webservice return status: " + res.status);
		}
    	return :table:;
	}

	protected extractStatus(res: Response) {
		//check if res.status excists, this means no data has returned
		if (typeof res.status == 'undefined') {
		} else {
			console.log("Webservice return status: " + res.status);
		}
    	return res;
	}

	public getAll(): Observable<:Table:[]> {
		const operation = this.SELECT_ALL;
		const body = { "operation": operation };
		return this.http.post(this.urlSELECT, body, this.httpOptions)
			.pipe(map(this.extractDataArray));
	}

}
