//Auto generated code
//don't change things here, it will be overwritten
//redefine classes in :table:.service.ts
/* 
    Created on : Nov 20, 2018, 10:39:01 AM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from "rxjs/Observable"

import { ServerService } from './server.service';
import { :Table:pk } from '../data/:table:base';
import { :Table: } from '../data/:table:';
import { :Table:json } from '../conversion/:table:-json';
import { :Table:searcher } from '../data/:table:base';
:repeatforeignkeys:
import { :Pktable:json } from '../conversion/:pktable:-json';
import { :Pktable:pk } from '../data/:pktable:base';
:repeatforeignkeys:
:repeatuniqueexternalforeignkeys:
import { :Extablename:json } from '../conversion/:extablename:-json';
import { :Extablename:pk } from '../data/:extablename:base';
:repeatuniqueexternalforeignkeys:

export class :Table:BaseService extends ServerService {
    //SELECT OPERATIONS
    SELECT_:TABLE: = 2;
    SELECT_Siteusergroup = 100 + 0;
:repeatforeignkeys:
    SELECT_:Uniquename: = 100 + :counter:;
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    SELECT_:Uniquename: = 100 + :counter:;
:ifforeignkey:
:repeatexternalforeignkeys:

    //UPDATE OPERATIONS
    UPDATE_:TABLE: = 10;

    //INSERT OPERATIONS
    INSERT_:TABLE: = 20;

    //DELETE OPERATIONS
:repeatforeignkeys:
    DELETE_:Uniquename: = 100 + :counter:;
:repeatforeignkeys:
    DELETE_:TABLE: = 30;
	
    url = this.WEBSERVICE + "rs:tablename:";
    urlSELECT = this.url + "_select";
    urlINSERT = this.url + "_insert";
    urlUPDATE = this.url + "_update";
    urlDELETE = this.url + "_delete";

	constructor(public http: HttpClient) { 
        super();
	}

	protected extractDataArray(res: Response): :Table:[] {
		let :table:s: :Table:[] = [];
		//check if res.status excists, this means no data has returned
		if (typeof res.status == 'undefined') {
			const json:table:s = JSON.parse(JSON.stringify(res))
            const :table:json: :Table:json = new :Table:json();
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
            const :table:json: :Table:json = new :Table:json();
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

	public getcount(user): Observable<number> {
		const operation = this.SELECT_COUNT;
		let auth = "";
		if(user!=null) auth = user.auth;
		const body = { "auth" : auth, "operation": operation };
		return this.http.post(this.urlSELECT, body, this.httpOptions)
			.pipe(map(this.extractDataCount));
	}

	public getAll(user): Observable<:Table:[]> {
		const operation = this.SELECT_ALL;
		let auth = "";
		if(user!=null) auth = user.auth;
		const body = { "auth" : auth, "operation": operation };
		return this.http.post(this.urlSELECT, body, this.httpOptions)
			.pipe(map(this.extractDataArray));
	}

	public getOne(user, :table:pk: :Table:pk): Observable<:Table:> {
		const operation = this.SELECT_:TABLE:;
        const :table:json: :Table:json = new :Table:json();
		let auth = "";
		if(user!=null) auth = user.auth;
		const body = { 
            "auth" : auth, 
            "operation": operation, 
            ":table:pk": :table:json.PKtoJSON(:table:pk)
        };
		return this.http.post(this.urlSELECT, body, this.httpOptions)
			.pipe(map(this.extractDataObject));
	}

:repeatforeignkeys:
	public load:Table:s4:uniquename:(user, :pktable:pk: :Table:pk): Observable<:Table:[]> {
		const operation = this.SELECT_:Uniquename:;
        const :pktable:json: :Pktable:json = new :Pktable:json();
		let auth = "";
		if(user!=null) auth = user.auth;
        const body = { 
            "auth" : auth, 
            "operation": operation, 
            ":pktable:pk": :pktable:json.PKtoJSON(:pktable:pk)
        };
		return this.http.post(this.urlSELECT, body, this.httpOptions)
			.pipe(map(this.extractDataArray));
	}

:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
	public load:Table:4:uniquename:(user, :uniquename:pk: :Extablename:pk): Observable<:Table:[]> {
		const operation = this.SELECT_:Uniquename:;
        const :extablename:json: :Extablename:json = new :Extablename:json();
		let auth = "";
		if(user!=null) auth = user.auth;
        const body = { 
            "auth" : auth, 
            "operation": operation, 
            ":extablename:pk": :extablename:json.PKtoJSON(:uniquename:pk)
        };
		return this.http.post(this.urlSELECT, body, this.httpOptions)
			.pipe(map(this.extractDataArray));
	}

:ifforeignkey:
:repeatexternalforeignkeys:
	public search(user, :table:searcher: :Table:searcher): Observable<:Table:[]> {
		const operation = this.SELECT_SEARCH;
		let auth = "";
		if(user!=null) auth = user.auth;
        const body = { 
            "auth" : auth, 
            "operation": operation, 
            "search": :table:searcher.toJSON() 
        };
		return this.http.post(this.urlSELECT, body, this.httpOptions)
			.pipe(map(this.extractDataArray));
	}

	public searchcount(user, :table:searcher: :Table:searcher): Observable<number> {
		const operation = this.SELECT_SEARCHCOUNT;
		let auth = "";
		if(user!=null) auth = user.auth;
        const body = { 
            "auth" : auth, 
            "operation": operation, 
            "search": :table:searcher.toJSON() 
        };
		return this.http.post(this.urlSELECT, body, this.httpOptions)
			.pipe(map(this.extractDataCount));
	}

	public insert(user, :table:: :Table:) {
		const operation = this.INSERT_:TABLE:;
        const :table:json: :Table:json = new :Table:json();
		let auth = "";
		if(user!=null) auth = user.auth;
        const body = { 
            "auth" : auth, 
            "operation": operation, 
            ":table:": :table:json.toJSON(:table:) 
        };
		return this.http.post(this.urlINSERT, body, this.httpOptions)
			.pipe(map(this.extractStatus));
	}

	public save(user, :table:: :Table:) {
		const operation = this.UPDATE_:TABLE:;
        const :table:json: :Table:json = new :Table:json();
		let auth = "";
		if(user!=null) auth = user.auth;
        const body = { 
            "auth" : auth, 
            "operation": operation, 
            ":table:": :table:json.toJSON(:table:) 
        };
		return this.http.post(this.urlUPDATE, body, this.httpOptions)
			.pipe(map(this.extractStatus));
	}

	public del(user, :table:: :Table:) {
		const operation = this.DELETE_:TABLE:;
        const :table:json: :Table:json = new :Table:json();
		let auth = "";
		if(user!=null) auth = user.auth;
        const body = { 
            "auth" : auth, 
            "operation": operation, 
            ":table:": :table:json.toJSON(:table:) 
        };
		return this.http.post(this.urlDELETE, body, this.httpOptions)
			.pipe(map(this.extractStatus));
	}
}
