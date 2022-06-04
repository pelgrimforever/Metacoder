//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 19, 2018, 12:42:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import { :Table:pk } from '../../../../../../data/:project:/table/super/:table:super.js';
import :Table: from '../../../../../../data/:project:/table/:table:.js';
:repeatuniqueforeignkeys:
import { :Pktable:pk } from '../../../../../../data/:project:/table/super/:pktable:super.js';
import :Pktable:Json from '../:pktable:json.js';
:repeatuniqueforeignkeys:
import Moment from 'moment';
import :Table:Json from '../:table:json.js';

class :Table:Jsonsuper {
	static PKtoJSON = (:table:PK) => {
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
			":column:": :table:PK.:uniquename:PK==null ? null : "" + :table:PK.:uniquename:PK.get:Primarycolumn:(),
:long:
:double:
			":column:": :table:PK.:uniquename:PK==null ? null : "" + :table:PK.:uniquename:PK.get:Primarycolumn:(),
:double:
:float:
			":column:": :table:PK.:uniquename:PK==null ? null : "" + :table:PK.:uniquename:PK.get:Primarycolumn:(),
:float:
:other:
			":column:": :table:PK.:uniquename:PK==null ? null : :table:PK.:uniquename:PK.get:Primarycolumn:(),
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

	static PKtoJSONstring = (:table:PK) => {
		return JSON.stringify(this.PKtoJSON(:table:PK));
	}
	
	static toJSON = (:table:) => {
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
:java.sql.Timestamp:
			":column:": "" + :table:.:column:,
:java.sql.Timestamp:
:other:
			":column:": :table:.:column:,
:other:
:iffieldtype:
:repeatfields:
		};
:repeatforeignkeys:
:notpk:
		if(:table:.:uniquename:PK!=null) {
			jsonobj.:uniquename:PK = :Pktable:Json.PKtoJSON(:table:.:uniquename:PK);
		}
:notpk:
:repeatforeignkeys:
		return jsonobj;
	}

	static PKfromJSON = (jsonobj): :Table:pk => {
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
:java.sql.Date:
				":primarycolumn:": jsonobj.:foreigncolumn:,
				if(jsonobj.:foreigncolumn:) {
					":primarycolumn:UI" = Moment(new Date(jsonobj.:foreigncolumn:)).format("YYYY MM DD");
				}
:java.sql.Date:
:java.sql.Time:
				":primarycolumn:": jsonobj.:foreigncolumn:,
				if(jsonobj.:foreigncolumn:) {
					":primarycolumn:UI" = Moment(new Date(jsonobj.:foreigncolumn:)).format("hh:mm:ss");
				}
:java.sql.Time:
:java.sql.Timestamp:
				":primarycolumn:": jsonobj.:foreigncolumn:,
				if(jsonobj.:foreigncolumn:) {
					":primarycolumn:UI" = Moment(new Date(jsonobj.:foreigncolumn:)).format("YYYY MM DD - hh:mm:ss");
				}
:java.sql.Timestamp:
:other:
				":primarycolumn:": jsonobj.:foreigncolumn:,
:other:
:iffieldtype:
:repeatforeignkeyfields:
			}
			let :uniquename:PK = :Pktable:Json.PKfromJSON(:uniquename:properties);
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
	static fromJSON = (jsonobj): :Table: => {
		let model = new :Table:();
		model.PK = this.PKfromJSON(jsonobj.PK);
:repeatforeignkeys:
:notpk:
		let :uniquename:PK = :Pktable:Json.PKfromJSON(jsonobj.:uniquename:PK);
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
			model.:column:UI = Moment(new Date(jsonobj.:column:)).format("YYYY MM DD");
		} else {
			model.:column: = "";
		}
:java.sql.Date:
:java.sql.Time:
		model.:column: = jsonobj.:column:;
		if(model.:column:) {
			model.:column:UI = Moment(new Date(jsonobj.:column:)).format("hh:mm:ss");
		} else {
			model.:column: = "";
		}
:java.sql.Time:
:java.sql.Timestamp:
		model.:column: = jsonobj.:column:;
		if(model.:column:) {
			model.:column:UI = Moment(new Date(jsonobj.:column:)).format("YYYY MM DD - hh:mm:ss");
		} else {
			model.:column: = "";
		}
:java.sql.Timestamp:
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

export default :Table:Jsonsuper;
