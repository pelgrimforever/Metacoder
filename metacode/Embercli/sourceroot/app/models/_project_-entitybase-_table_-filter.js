//Auto generated code
//don't change things here, it will be overwritten
//extend classes in :project:-entity-:table:-filter.js for table class definitions
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';
import casttoTime from '../utils/castto-time';

export default Ember.Object.extend({
    filterconditionsUI: "all",
:repeatforeignkeys:
    :uniquename:PK: null,
:repeatforeignkeys:
:repeatallfields:
:notfk:
:iffieldtype:
:int:
    :column:: '',
    :column:selector: '=',
:int:
:long:
    :column:: '',
    :column:selector: '=',
:long:
:double:
    :column:: '',
    :column:selector: '=',
:double:
:float:
    :column:: '',
    :column:selector: '=',
:float:
:boolean:
    :column:: "all",
    :column:UI: "2",
:boolean:
:java.sql.Date:
    :column:: '',
    :column:UI: '',
    :column:selector: '=',
:java.sql.Date:
:java.sql.Time:
    :column:: '',
    :column:UI: '',
    :column:selector: '=',
:java.sql.Time:
:java.lang.String:
    :column:: '',
    :column:selector: 'equal',
:java.lang.String:
:customfieldtype:
    :column:: null,
:customfieldtype:
:other:
    :column:: '',
:other:
:iffieldtype:
:notfk:
:repeatallfields:
:repeatallfields:
:notfk:
:iffieldtype:
:java.sql.Date:
    :column:changed: function() {
        if(this.:column:UI!="") {
            newdate = new Date(this.:column:UI);
            this.:column: = newdate.getTime();
        } else this.:column: = null;
    }.observes(":column:UI"),
:java.sql.Date:
:java.sql.Time:
    :column:changed: function() {
        if(this.:column:UI!="") {
            newtime = casttoTime(this.:column:UI);
            this.:column: = newtime.getTime();
        } else this.:column: = null;
    }.observes(":column:UI"),
:java.sql.Time:
:boolean:
    :column:changed: function() {
        switch(this.:column:UI) {
            case "0": this.:column: = false; break;
            case "1": this.:column: = true; break;
            case "2": this.:column: = "all"; break;
        }
    }.observes(":column:UI"),
:boolean:
:iffieldtype:
:notfk:
:repeatallfields:
});
