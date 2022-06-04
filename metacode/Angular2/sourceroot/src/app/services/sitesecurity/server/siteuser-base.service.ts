//Auto generated code
//don't change things here, it will be overwritten
//redefine classes in sitesecurity-entity-siteuser-datahandler.js
/* 
    Created on : Nov 20, 2018, 10:39:01 AM
    Generated on 21.10.2018 11:19
    Author     : Franky Laseure
*/

import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from "rxjs/Observable"

import { ServerService } from './server.service';
import { Siteuserpk } from '../data/siteuserbase';
import { Siteuser } from '../data/siteuser';
import { Siteuserjson } from '../conversion/siteuser-json';

export class SiteuserBaseService extends ServerService {
    //SELECT OPERATIONS
    SELECT_SITEUSER = 2;

    url = this.WEBSERVICE + "rssiteuser_select";

	constructor(public http: HttpClient) { 
        super();
	}

	private extractDataArray(res: Response): Siteuser[] {
		let siteusers: Siteuser[] = [];
		//check if res.status excists, this means no data has returned
		if (typeof res.status == 'undefined') {
			const jsonsiteusers = JSON.parse(JSON.stringify(res))
            const siteuserjson: Siteuserjson = new Siteuserjson();
			for(let i = 0; i < jsonsiteusers.length; i++) {
				siteusers.push(siteuserjson.fromJSON(jsonsiteusers[i]));
			}
		} else {
			console.log("Webservice return status: " + res.status);
		}
    	return siteusers;
	}

	protected extractDataObject(res: Response): Siteuser {
		let siteuser: Siteuser = null;
		//check if res.status excists, this means no data has returned
		if (typeof res.status == 'undefined') {
			const jsonsiteuser = JSON.parse(JSON.stringify(res))
            const siteuserjson: Siteuserjson = new Siteuserjson();
    		siteuser = siteuserjson.fromJSON(jsonsiteuser);
		} else {
			console.log("Webservice return status: " + res.status);
		}
    	return siteuser;
	}

	private extractStatus(res: Response) {
		//check if res.status excists, this means no data has returned
		if (typeof res.status == 'undefined') {
		} else {
			console.log("Webservice return status: " + res.status);
		}
    	return res;
	}

	public getcount(): Observable<number> {
		const operation = this.SELECT_COUNT;
		const body = { "operation": operation };
		return this.http.post(this.url, body, this.httpOptions)
			.pipe(map(this.extractDataCount));
	}

	public getAll(): Observable<Siteuser[]> {
		const operation = this.SELECT_ALL;
		const body = { "operation": operation };
		return this.http.post(this.url, body, this.httpOptions)
			.pipe(map(this.extractDataArray));
	}

	public getOne(siteuserpk: Siteuserpk): Observable<Siteuser> {
		const operation = this.SELECT_SITEUSER;
        const siteuserjson: Siteuserjson = new Siteuserjson();
		const body = { "operation": operation, 
            "siteuserpk": siteuserjson.PKtoJSONstring(siteuserpk)
        };
		return this.http.post(this.url, body, this.httpOptions)
			.pipe(map(this.extractDataObject));
	}
}
