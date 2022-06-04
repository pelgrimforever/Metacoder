//Auto generated code
//don't change things here; it will be overwritten
//Super class for database services
/* 
    Created on : Nov 20; 2018; 12:17:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from "rxjs/Observable";

export class ServerService {
	WEBSERVICE = "/:Project:Webservices/webresources/";

    //OPERATIONS TYPES
    VAR_OPERATION = "OPERATION";

    OPERATIONTYPE_INSERT = 100;
    OPERATIONTYPE_UPDATE = 101;
    OPERATIONTYPE_DELETE = 102;
    OPERATIONTYPE_SELECT = 103;
    OPERATIONTYPE_BACKUP = 104;

    OPERATIONTYPE_SECUREINSERT = 105;
    OPERATIONTYPE_SECUREUPDATE = 106;
    OPERATIONTYPE_SECUREDELETE = 107;
    OPERATIONTYPE_SECURESELECT = 108;
    OPERATIONTYPE_SECUREBACKUP = 109;
    
    OPERATIONTYPE_AUTHENTICATE = -1;

    //SELECT OPERATIONS
    SELECT_COUNT = 0;
    SELECT_ALL = 1;
    SELECT_SEARCH = 3;
    SELECT_SEARCHCOUNT = 4;

	httpOptions = {
		headers: new HttpHeaders({
			'Content-Type':  'application/json',
			})
	};

    protected extractDataCount(res: Response): number {
        let count: number = 0;
        //check if res.status exists, this means no data has returned
        if (typeof res.status == 'undefined') {
            const jsoncount = JSON.parse(JSON.stringify(res))
            count = jsoncount.recordcount;
        } else {
            console.log("Webservice return status: " + res.status);
        }
        return count;
    }
}
