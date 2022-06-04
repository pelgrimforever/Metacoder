//Auto generated code
//don't change things here, it will be overwritten
//change in :table:-json.js
/* 
    Created on : Nov 19, 2018, 12:42:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import { :Table:pk } from '../data/:table:base';
import { :Table: } from '../data/:table:';
:repeatuniqueforeignkeys:
import { :Pktable:pk } from '../data/:pktable:base';
import { :Pktable:json } from './:pktable:-json';
:repeatuniqueforeignkeys:

import * as moment from 'moment';

export class :Table:JsonBase {
	constructor() { 
    }

    public PKtoJSON(:table:PK) {
        let jsonobj = {
:repeatpkfields:
:notfk:
:iffieldtype:
:long:
            ":column:": null,
:long:
:double:
            ":column:": null,
:double:
:float:
            ":column:": null,
:float:
:other:
            ":column:": null,
:other:
:iffieldtype:
:notfk:
:repeatpkfields:
:repeatforeignkeys::inpk:
:repeatforeignkeyfields:
:iffieldtype:
:long:
	        ":column:": "" + :table:PK.:uniquename:PK.get:Primarycolumn:(),
:long:
:double:
	        ":column:": "" + :table:PK.:uniquename:PK.get:Primarycolumn:(),
:double:
:float:
	        ":column:": "" + :table:PK.:uniquename:PK.get:Primarycolumn:(),
:float:
:other:
	        ":column:": :table:PK.:uniquename:PK.get:Primarycolumn:(),
:other:
:iffieldtype:
:repeatforeignkeyfields:
:inpk::repeatforeignkeys:
	    };
        if(:table:PK!=null) {
:repeatpkfields:
:notfk:
:iffieldtype:
:long:
            jsonobj.:column: = "" + :table:PK.:column:;
:long:
:double:
            jsonobj.:column: = "" + :table:PK.:column:;
:double:
:float:
            jsonobj.:column: = "" + :table:PK.:column:;
:float:
:other:
            jsonobj.:column: = :table:PK.:column:;
:other:
:iffieldtype:
:notfk:
:repeatpkfields:
        }
	    return jsonobj;
	}

	public PKtoJSONstring(:table:PK) {
        return JSON.stringify(this.PKtoJSON(:table:PK));
	}
	public toJSON(:table:) {
	    var jsonobj = {
	        "PK": this.PKtoJSON(:table:.PK),
:repeatforeignkeys:
:notpk:
		    ":uniquename:PK": null,
:notpk:
:repeatforeignkeys:
:repeatfields:
:iffieldtype:
:long:
    	    ":column:": "" + :table:.:column:,
:long:
:double:
    	    ":column:": "" + :table:.:column:,
:double:
:float:
    	    ":column:": "" + :table:.:column:,
:float:
:java.sql.Date:
    	    ":column:": "" + :table:.:column:,
:java.sql.Date:
:java.sql.Time:
    	    ":column:": "" + :table:.:column:,
:java.sql.Time:
:other:
    	    ":column:": :table:.:column:,
:other:
:iffieldtype:
:repeatfields:
	    };
:repeatforeignkeys:
:notpk:
	    if(:table:.:uniquename:PK!=null) {
            let :pktable:json = new :Pktable:json();
		    jsonobj.:uniquename:PK = :pktable:json.PKtoJSON(:table:.:uniquename:PK);
	    }
:notpk:
:repeatforeignkeys:
	    return jsonobj;
	}

	public PKfromJSON(jsonobj): :Table:pk {
		let :table:PK = new :Table:pk();
	    if(jsonobj!=null) {
:repeatforeignkeys:
:inpk:
            let :uniquename:properties = {
:repeatforeignkeyfields:
:iffieldtype:
:long:
	            ":primarycolumn:": "" + jsonobj.:foreigncolumn:,
:long:
:double:
	            ":primarycolumn:": parseFloat(jsonobj.:foreigncolumn:),
:double:
:float:
	            ":primarycolumn:": parseFloat(jsonobj.:foreigncolumn:),
:float:
:other:
	            ":primarycolumn:": jsonobj.:foreigncolumn:,
:other:
:iffieldtype:
:repeatforeignkeyfields:
            }
            let :pktable:json = new :Pktable:json();
        	let :uniquename:PK = :pktable:json.PKfromJSON(:uniquename:properties);
		    :table:PK.:uniquename:PK = :uniquename:PK;
:inpk:
:repeatforeignkeys:
:repeatpkfields:
:notfk:
	        :table:PK.:column: = jsonobj.:column:;
:notfk:
:repeatpkfields:
	    }
	    return :table:PK;
	}
	public fromJSON(jsonobj): :Table: {
		let model = new :Table:();
	    model.PK = this.PKfromJSON(jsonobj.PK);
:repeatforeignkeys:
:notpk:
        let :pktable:json = new :Pktable:json();
	    let :uniquename:PK = :pktable:json.PKfromJSON(jsonobj.:uniquename:PK);
	    model.:uniquename:PK = :uniquename:PK;
:notpk:
:repeatforeignkeys:
:repeatfields:
:iffieldtype:
:long:
	    model.:column: = "" + jsonobj.:column:;
:long:
:double:
	    model.:column: = parseFloat(jsonobj.:column:);
:double:
:float:
	    model.:column: = parseFloat(jsonobj.:column:);
:float:
:java.sql.Date:
	    model.:column: = jsonobj.:column:;
	    if(model.:column:) {
            model.:column:UI = moment(new Date(jsonobj.:column:)).format("YYYY MM DD");
	    } else {
            model.:column: = "";
        }
:java.sql.Date:
:java.sql.Time:
	    model.:column: = jsonobj.:column:;
	    if(model.:column:) {
            model.:column:UI = moment(new Date(jsonobj.:column:)).format("hh:mm:ss");
	    } else {
            model.:column: = "";
        }
:java.sql.Time:
:customfieldtype:
	    model.:column: = jsonobj.:column:;
:customfieldtype:
:other:
	    model.:column: = jsonobj.:column:;
:other:
:iffieldtype:
:repeatfields:
	    return model;
	}
}

