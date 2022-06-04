//Auto generated code
//don't change things here, it will be overwritten
//extend classes in :table:.ts for table class definitions
/* 
    Created on : Nov 16, 2018, 17:04:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import { Tablesearch } from './search/tablesearch';
import { Datesearcher } from './search/datesearcher';
import { Timesearcher } from './search/timesearcher';
import { Numbersearcher } from './search/numbersearcher';
import { Booleansearcher } from './search/booleansearcher';
import { Stringsearcher } from './search/stringsearcher';
import { Foreignkeysearcher } from './search/foreignkeysearcher';
import { Primarykeysearcher } from './search/primarykeysearcher';
import { Relationalkeysearcher } from './search/relationalkeysearcher';

:repeatuniqueforeignkeys:
import { :Pktable:pk } from './:pktable:base';
:repeatuniqueforeignkeys:

export class :Table:pk {
	constructor() { }
:repeatforeignkeys:
:inpk:
    :uniquename:PK: :Pktable:pk = null;
:inpk:
:repeatforeignkeys:
:repeatpkfields:
:infk:
    public :column:() {
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
    public get:Foreigncolumn:() {
        return this.:uniquename:PK.get:Primarycolumn:();
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
:other:
    :column:: string = '';
:other:
:customfieldtype:
    :column:: any = null;:customfieldtype:
:iffieldtype:
    get:Column:() {
        return this.:column:;
    }
:notfk:
:repeatpkfields:
    public init() {
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

export class :Table:base {
	constructor() { }
	tablename: string = ':table:';
	PK: :Table:pk = new :Table:pk();
:repeatpkfields:
    public :column:() {
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
    public tostring() {
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
    public add:column:s(values, operators) {
        this.addDatevalues(this.:columnjavaname:, values, operators);
    }
:java.sql.Date:
:java.sql.Time:
    public add:column:s(values, operators) {
        this.addTimevalues(this.:columnjavaname:, values, operators);
    }
:java.sql.Time: 
:int:
    public add:column:s(values, operators) {
        this.addLongvalues(this.:columnjavaname:, values, operators);
    }    
:int: 
:long:
    public add:column:s(values, operators) {
        this.addLongvalues(this.:columnjavaname:, values, operators);
    }
:long: 
:double:
    public add:column:s(values, operators) {
        this.addNumbervalues(this.:columnjavaname:, values, operators);
    }
:double: 
:float:
    public add:column:s(values, operators) {
        this.addNumbervalues(this.:columnjavaname:, values, operators);
    }
:float: 
:boolean:
    public add:column:(value) {
        this.addBooleanvalue(this.:columnjavaname:, value);
    }
:boolean: 
:customfieldtype:
:customfieldtype:
:other:
    public add:column:s(values) {
        this.addStringvalues(this.:columnjavaname:, values);
    }
:other:
:iffieldtype:
:notfk:
:repeatallfields:
:repeatforeignkeys:
    public set:uniquename:(:pktable:search) {
        this.:uniquename:searcher.setTablesearch(:pktable:search);
    }
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    public set:uniquename:(:extablename:search) {
        this.:uniquename:searcher.setTablesearch(:extablename:search);
    }
:ifforeignkey:
:ifrelational:
    public set:uniquename:(:extablename:search) {
        this.:uniquename:searcher.setTablesearch(:extablename:search);
    }
:ifrelational:
:repeatexternalforeignkeys:

    public toJSON() {
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
