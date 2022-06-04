//Auto generated code
//don't change things here, it will be overwritten
//extend classes in :project:-entity-:table:-searcher.js for table class definitions
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';
import Tablesearch from '../models/:project:-entitybase-tablesearch';
import Datesearch from '../models/:project:-entitybase-datesearch';
import Timesearch from '../models/:project:-entitybase-timesearch';
import Numbersearch from '../models/:project:-entitybase-numbersearch';
import Booleansearch from '../models/:project:-entitybase-booleansearch';
import Stringsearch from '../models/:project:-entitybase-stringsearch';
import Foreignkeysearch from '../models/:project:-entitybase-foreignkeysearch';
import Primarykeysearch from '../models/:project:-entitybase-primarykeysearch';
import Relationalkeysearch from '../models/:project:-entitybase-relationalkeysearch';

export default Tablesearch.extend({
    tablename: ":table:",
    filtermodel: null,:repeatallfields:
:notfk:
:columnjavaname:: null,:notfk:
:repeatallfields:
:repeatforeignkeys:
:uniquename:searcher: null,:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
:uniquename:searcher: null,:ifforeignkey:
:ifrelational:
:uniquename:searcher: null,:ifrelational:
:repeatexternalforeignkeys:

    init: function() {
        this._super();
:repeatallfields:
:notfk:
:iffieldtype:
:java.sql.Date:
        this.:columnjavaname: = Datesearch.create({fieldname: ":table:.:column:"});
:java.sql.Date: 
:java.sql.Time:
        this.:columnjavaname: = Timesearch.create({fieldname: ":table:.:column:"});
:java.sql.Time: 
:java.sql.Timestamp:
        this.:columnjavaname: = Timesearch.create({fieldname: ":table:.:column:"});
:java.sql.Timestamp: 
:byte:
        this.:columnjavaname: = Numbersearch.create({fieldname: ":table:.:column:"});
:byte:
:int:
        this.:columnjavaname: = Numbersearch.create({fieldname: ":table:.:column:"});
:int:
:long:
        this.:columnjavaname: = Numbersearch.create({fieldname: ":table:.:column:"});
:long: 
:double:
        this.:columnjavaname: = Numbersearch.create({fieldname: ":table:.:column:"});
:double:
:float:
        this.:columnjavaname: = Numbersearch.create({fieldname: ":table:.:column:"});
:float: 
:boolean:
        this.:columnjavaname: = Booleansearch.create({fieldname: ":table:.:column:"});
:boolean: 
:other:
        this.:columnjavaname: = Stringsearch.create({fieldname: ":table:.:column:"});
:other:
:iffieldtype:
:notfk:
:repeatallfields:
:repeatforeignkeys:
        this.:uniquename:searcher = Foreignkeysearch.create();
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
        this.:uniquename:searcher = Primarykeysearch.create();
:ifforeignkey:
:ifrelational:
        this.:uniquename:searcher = Relationalkeysearch.create();
:ifrelational:
:repeatexternalforeignkeys:
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
    },

:repeatallfields:
:notfk:
:iffieldtype:
:java.sql.Date:
    add:column:s: function(values, operators) {
        this.addDatevalues(this.:columnjavaname:, values, operators);
    },
:java.sql.Date:
:java.sql.Time:
    add:column:s: function(values, operators) {
        this.addTimevalues(this.:columnjavaname:, values, operators);
    },
:java.sql.Time: 
:int:
    add:column:s: function(values, operators) {
        this.addLongvalues(this.:columnjavaname:, values, operators);
    },    
:int: 
:long:
    add:column:s: function(values, operators) {
        this.addLongvalues(this.:columnjavaname:, values, operators);
    },
:long: 
:double:
    add:column:s: function(values, operators) {
        this.addNumbervalues(this.:columnjavaname:, values, operators);
    },    
:double: 
:float:
    add:column:s: function(values, operators) {
        this.addNumbervalues(this.:columnjavaname:, values, operators);
    },
:float: 
:boolean:
    add:column:: function(value) {
        this.addBooleanvalue(this.:columnjavaname:, value);
    },
:boolean: 
:customfieldtype:
:customfieldtype:
:other:
    add:column:s: function(values) {
        this.addStringvalues(this.:columnjavaname:, values);
    },
:other:
:iffieldtype:
:notfk:
:repeatallfields:
:repeatforeignkeys:
    set:uniquename:: function(:pktable:search) {
        this.:uniquename:searcher.setTablesearch(:pktable:search);
    },    
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    set:uniquename:: function(:extablename:search) {
        this.:uniquename:searcher.setTablesearch(:extablename:search);
    },
:ifforeignkey:
:ifrelational:
    set:uniquename:: function(:extablename:search) {
        this.:uniquename:searcher.setTablesearch(:extablename:search);
    },
:ifrelational:
:repeatexternalforeignkeys:
    toJSON: function() {
        var jsonobj = {
        }; 
        if(this.used()) {
            jsonobj.andor = this.andoroperator;
            jsonobj.maxresults = this.maxresults;
            jsonobj.docount = this.docount;
            //primarykeys not implemented yet
            jsonobj.primarykeys = [];
            jsonobj.fields = {};
            var i=0;
            for(i=0; i<this.fieldsearchers.get('length'); i++) {
                var fieldsearcher = this.fieldsearchers.objectAt(i);
                if(fieldsearcher.used()) {
                    jsonobj.fields[fieldsearcher.getshortname()] = fieldsearcher.toJSON();
                }
            }
            var kss = {
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
});
