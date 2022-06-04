//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Dec 16, 2018, 18:32:01
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import :Table: from '../../../../../data/:project:/view/:table:.js';
import Moment from 'moment';

class :Table:Json {
	static toJSON = (:table:) => {
		var jsonobj = {
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
		return jsonobj;
	}

	static fromJSON = (jsonobj): :Table: => {
		let model = new :Table:();
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

export default :Table:Json;
