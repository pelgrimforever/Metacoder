//Auto generated code
//don't change things here, it will be overwritten
//change in siteuser-json.js
/* 
    Created on : Nov 19, 2018, 12:42:01 PM
    Generated on 21.10.2018 11:19
    Author     : Franky Laseure
*/

import { Siteuserpk } from '../data/siteuserbase';
import { Siteuser } from '../data/siteuser';

import * as moment from 'moment';

export class SiteuserJsonBase {
	constructor() { 
    }

    public PKtoJSON(siteuserPK) {
        let jsonobj = {
            "username": null,
	    };
        if(siteuserPK!=null) {
            jsonobj.username = siteuserPK.username;
        }
	    return jsonobj;
	}

	public PKtoJSONstring(siteuserPK) {
        return JSON.stringify(this.PKtoJSON(siteuserPK));
	}
	public toJSON(siteuser) {
	    var jsonobj = {
	        "PK": this.PKtoJSON(siteuser.PK),
    	    "userpassword": siteuser.userpassword,
	    };
	    return jsonobj;
	}

	public PKfromJSON(jsonobj) {
		let siteuserPK = new Siteuserpk();
	    if(jsonobj!=null) {
	        siteuserPK.username = jsonobj.username;
	    }
	    return siteuserPK;
	}
	public fromJSON(jsonobj) {
		let model = new Siteuser();
	    model.PK = this.PKfromJSON(jsonobj.PK);
	    model.userpassword = jsonobj.userpassword;
	    return model;
	}
}

