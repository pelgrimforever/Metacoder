//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 16, 2018, 17:04:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Tablesearch from '../../../search/tablesearch.js';
import Datesearcher from '../../../search/datesearcher.js';
import Timesearcher from '../../../search/timesearcher.js';
import Numbersearcher from '../../../search/numbersearcher.js';
import Booleansearcher from '../../../search/booleansearcher.js';
import Stringsearcher from '../../../search/stringsearcher.js';
import Foreignkeysearcher from '../../../search/foreignkeysearcher.js';
import Primarykeysearcher from '../../../search/primarykeysearcher.js';
import Relationalkeysearcher from '../../../search/relationalkeysearcher.js';
:repeatuniqueforeignkeys:
import {:Pktable:pk} from './:pktable:super.js';
:repeatuniqueforeignkeys:

export class :Table:pk {
:repeatforeignkeys:
:inpk:
	:uniquename:PK: :Pktable:pk = null;
:inpk:
:repeatforeignkeys:
:repeatpkfields:
:infk:
	:column: = () => {
:iffieldtype:
:int:
		if(this.:uniquename:PK===null) return 0;
:int: 
:long:
		if(this.:uniquename:PK===null) return '';
:long: 
:double:
		if(this.:uniquename:PK===null) return 0;
:double: 
:float:
		if(this.:uniquename:PK===null) return 0;
:float: 
:boolean:
		if(this.:uniquename:PK===null) return false;
:boolean:
:other:
		if(this.:uniquename:PK===null) return '';
:other:
:iffieldtype:
		else return this.:uniquename:PK.get:Primarycolumn:();
	}
	get:Foreigncolumn: = () => {
:iffieldtype:
:int:
		if(this.:uniquename:PK===null) return 0;
:int: 
:long:
		if(this.:uniquename:PK===null) return '';
:long: 
:double:
		if(this.:uniquename:PK===null) return 0;
:double: 
:float:
		if(this.:uniquename:PK===null) return 0;
:float: 
:boolean:
		if(this.:uniquename:PK===null) return false;
:boolean:
:other:
		if(this.:uniquename:PK===null) return '';
:other:
:iffieldtype:
		else return this.:uniquename:PK.get:Primarycolumn:();
	}
:infk:
:notfk:
:iffieldtype:
:int:
	:column:: number = 0;
:int: 
:long:
    :column:: string = '';
:long: 
:double:
    :column:: number = 0;
:double: 
:float:
    :column:: number = 0;
:float: 
:boolean:
    :column:: boolean = false;
:boolean:
:java.sql.Date:
    :column:: string = '';
    :column:UI: string = '';
:java.sql.Date:
:java.sql.Time:
    :column:: string = '';
    :column:UI: string = '';
:java.sql.Time:
:java.sql.Timestamp:
    :column:: string = '';
    :column:UI: string = '';
:java.sql.Timestamp:
:other:
    :column:: string = '';
:other:
:customfieldtype:
    :column:: any = null;:customfieldtype:
:iffieldtype:
    get:Column: = () => {
        return this.:column:;
    }
:notfk:
:repeatpkfields:
	init = () => {
:repeatforeignkeys:
:inpk:
		this.:uniquename:PK = new :Pktable:pk();
:inpk:
:repeatforeignkeys:
:repeatpkfields:
:notfk:
:iffieldtype:
:int:
		this.:column: = 0;
:int: 
:long:
		this.:column: = '';
:long: 
:double:
		this.:column: = 0;
:double: 
:float:
		this.:column: = 0;
:float: 
:boolean:
		this.:column: = 0;
:boolean:
:other:
		this.:column: = "";
:other:
:customfieldtype:
		this.:column: = null;
:customfieldtype:
:iffieldtype:
:notfk:
:repeatpkfields:
	}
}

class :Table:super {
	constructor() { }
	tablename: string = ':table:';
	PK: :Table:pk = new :Table:pk();
:repeatpkfields:
	:column: = () => {
:iffieldtype:
:int:
		if(this.PK===null) return 0;
:int: 
:long:
		if(this.PK===null) return '';
:long: 
:double:
		if(this.PK===null) return 0;
:double: 
:float:
		if(this.PK===null) return 0;
:float: 
:boolean:
		if(this.PK===null) return false;
:boolean:
:other:
		if(this.PK===null) return '';
:other:
:iffieldtype:
		else return this.PK.:column:;
	}
:repeatpkfields:
:repeatforeignkeys:
:notpk:
	:uniquename:PK: :Uniquename:pk = null;
:notpk:
:repeatforeignkeys:
	tostring = () => {
		let result = "";
		if(this.PK!=null) {
			result += :repeatpkfields:this.PK.get:Column:():separator: + " " + :separator::repeatpkfields:;
		}
		return result;
	}
:repeatfields:
:iffieldtype:
:int:
	:column:: number = 0;
:int: 
:long:
	:column:: string = '';
:long: 
:double:
	:column:: number = 0;
:double: 
:float:
	:column:: number = 0;
:float: 
:boolean:
	:column:: boolean = false;
:boolean:
:java.sql.Date:
	:column:: string = '';
	:column:UI: string = '';
:java.sql.Date:
:java.sql.Time:
	:column:: string = '';
	:column:UI: string = '';
:java.sql.Time:
:customfieldtype:
	:column: = null;
:customfieldtype:
:other:
	:column: = '';
:other:
:iffieldtype:
:repeatfields:
:repeatfields:
:iffieldtype:
:java.sql.Date:
:java.sql.Date:
:java.sql.Time:
:java.sql.Time:
:iffieldtype:
:repeatfields:
}

export class :Table:searcher extends Tablesearch {
	constructor() { 
		super(); 
:repeatallfields:
:notfk:
		this.addFieldsearcher(this.:columnjavaname:);
:notfk:
:repeatallfields:
:repeatforeignkeys:
        this.addKeysearcher(this.:uniquename:searcher);
:repeatforeignkeys:
:repeatexternalforeignkeys:
        this.addKeysearcher(this.:uniquename:searcher);
:repeatexternalforeignkeys:
	}

    filtermodel: null;
:repeatallfields:
:notfk:
:iffieldtype:
:int:
    :columnjavaname:: Numbersearcher = new Numbersearcher(":table:.:column:");
:int: 
:long:
    :columnjavaname:: Numbersearcher = new Numbersearcher(":table:.:column:");
:long: 
:double:
    :columnjavaname:: Numbersearcher = new Numbersearcher(":table:.:column:");
:double: 
:float:
    :columnjavaname:: Numbersearcher = new Numbersearcher(":table:.:column:");
:float: 
:boolean:
    :columnjavaname:: Booleansearcher = new Booleansearcher(":table:.:column:");
:boolean:
:java.sql.Date:
    :columnjavaname:: Datesearcher = new Datesearcher(":table:.:column:");
:java.sql.Date:
:java.sql.Time:
    :columnjavaname:: Timesearcher = new Timesearcher(":table:.:column:");
:java.sql.Time:
:other:
    :columnjavaname:: Stringsearcher = new Stringsearcher(":table:.:column:");
:other:
:iffieldtype:
:notfk:
:repeatallfields:
:repeatforeignkeys:
    :uniquename:searcher: Foreignkeysearcher = new Foreignkeysearcher();
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    :uniquename:searcher: Primarykeysearcher = new Primarykeysearcher();
:ifforeignkey:
:ifrelational:
    :uniquename:searcher: Relationalkeysearcher = new Relationalkeysearcher();
:ifrelational:
:repeatexternalforeignkeys:

:repeatallfields:
:notfk:
:iffieldtype:
:java.sql.Date:
    add:column:s = (values, operators) => {
    	this.:columnjavaname:.values = this.:columnjavaname:.values.concat(values);
    	this.:columnjavaname:.operators = this.:columnjavaname:.operators.concat(operators);
    }
:java.sql.Date:
:java.sql.Time:
    add:column:s = (values, operators) => {
    	this.:columnjavaname:.values = this.:columnjavaname:.values.concat(values);
    	this.:columnjavaname:.operators = this.:columnjavaname:.operators.concat(operators);
    }
:java.sql.Time: 
:int:
    add:column:s = (values, operators) => {
		  let numvalues = [values.length];
		  let i=0;
		  for(i=0; i<values.length; i++) {
		    numvalues[i] = parseFloat(values[i]);
		  }
    	this.:columnjavaname:.values = this.:columnjavaname:.values.concat(numvalues);
    	this.:columnjavaname:.operators = this.:columnjavaname:.operators.concat(operators);
    }    
:int: 
:long:
    add:column:s = (values, operators) => {
		  let numvalues = [values.length];
		  let i=0;
		  for(i=0; i<values.length; i++) {
		    numvalues[i] = parseFloat(values[i]);
		  }
    	this.:columnjavaname:.values = this.:columnjavaname:.values.concat(numvalues);
    	this.:columnjavaname:.operators = this.:columnjavaname:.operators.concat(operators);
    }
:long: 
:double:
    add:column:s = (values, operators) => {
		  let numvalues = [values.length];
		  let i=0;
		  for(i=0; i<values.length; i++) {
		    numvalues[i] = parseFloat(values[i]);
		  }
    	this.:columnjavaname:.values = this.:columnjavaname:.values.concat(numvalues);
    	this.:columnjavaname:.operators = this.:columnjavaname:.operators.concat(operators);
    }
:double: 
:float:
    add:column:s = (values, operators) => {
		  let numvalues = [values.length];
		  let i=0;
		  for(i=0; i<values.length; i++) {
		    numvalues[i] = parseFloat(values[i]);
		  }
    	this.:columnjavaname:.values = this.:columnjavaname:.values.concat(numvalues);
    	this.:columnjavaname:.operators = this.:columnjavaname:.operators.concat(operators);
    }
:float: 
:boolean:
    add:column: = (value) => {
    	this.:columnjavaname:.values = [value];
    }
:boolean: 
:customfieldtype:
:customfieldtype:
:other:
    add:column:s = (values) => {
    	this.:columnjavaname:.values = this.:columnjavaname:.values.concat(values);
    }
:other:
:iffieldtype:
:notfk:
:repeatallfields:
:repeatforeignkeys:
    set:uniquename: = (:pktable:search) => {
        this.:uniquename:searcher.setTablesearch(:pktable:search);
    }
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    set:uniquename: = (:extablename:search) => {
        this.:uniquename:searcher.setTablesearch(:extablename:search);
    }
:ifforeignkey:
:ifrelational:
    set:uniquename: = (:extablename:search) => {
        this.:uniquename:searcher.setTablesearch(:extablename:search);
    }
:ifrelational:
:repeatexternalforeignkeys:

    toJSON = () => {
        let jsonobj = {
        	"andor": this.andoroperator,
        	"maxresults": this.maxresults,
        	"docount": this.docount,
        	"primarykeys": [],
        	"fields": {},
        	"keysearch": {}
        }; 
        if(this.used()) {
            //primarykeys not implemented yet
            jsonobj.primarykeys = [];
            let i=0;
            for(i=0; i<this.fieldsearchers.length; i++) {
                var fieldsearcher = this.fieldsearchers[i];
                if(fieldsearcher.used()) {
                    jsonobj.fields[fieldsearcher.getshortname()] = fieldsearcher.toJSON();
                }
            }
            var kss = {
:repeatforeignkeys:
                ":uniquename:searcher": null,
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
                ":uniquename:searcher": null,
:ifforeignkey:
:ifrelational:
                ":uniquename:searcher": null,
:ifrelational:
:repeatexternalforeignkeys:
            }; 
:repeatforeignkeys:
            if(this.:uniquename:searcher!=null && this.:uniquename:searcher.used()) {
                kss.:uniquename:searcher = this.:uniquename:searcher.toJSON();
            }
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
            if(this.:uniquename:searcher!=null && this.:uniquename:searcher.used()) {
                kss.:uniquename:searcher = this.:uniquename:searcher.toJSON();
            }
:ifforeignkey:
:ifrelational:
            if(this.:uniquename:searcher!=null && this.:uniquename:searcher.used()) {
                kss.:uniquename:searcher = this.:uniquename:searcher.toJSON();
            }
:ifrelational:
:repeatexternalforeignkeys:
            jsonobj.keysearch = kss;
        }
        return jsonobj;
    }
}

export default :Table:super;
