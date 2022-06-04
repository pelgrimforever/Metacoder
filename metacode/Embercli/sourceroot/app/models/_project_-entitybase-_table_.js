//Auto generated code
//don't change things here, it will be overwritten
//extend classes in :project:-entity-:table:.js for table class definitions
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';
import :Table:PK from '../models/:project:-entitybase-:table:pk';
import casttoTime from '../utils/castto-time';

export default Ember.Object.extend({
	tablename: ':table:',
    PK: null,
    :table:PK: null,
    PKChanged: function() {
        if(this.PK!==null) {
          this.set(":table:PK", :Table:PK.create(this.PK));
        }
    }.observes('PK'),
:repeatpkfields:
    :column:: function() {
:iffieldtype:
:int:
        if(this.:table:PK===null) return 0;
:int: 
:long:
        if(this.:table:PK===null) return '';
:long: 
:double:
        if(this.:table:PK===null) return 0;
:double: 
:float:
        if(this.:table:PK===null) return 0;
:float: 
:boolean:
        if(this.:table:PK===null) return false;
:boolean:
:other:
        if(this.:table:PK===null) return '';
:other:
:iffieldtype:
        else return this.:table:PK.:column:;
    }.property(':table:PK'),
:repeatpkfields:
:repeatforeignkeys:
:notpk:
    :uniquename:PK: null,
:notpk:
:repeatforeignkeys:
    tostring: function() {
      var result = "";
      if(this.:table:PK!=null) {
          result += :repeatpkfields:this.:table:PK.get:Column:():separator: + " " + :separator::repeatpkfields:;
      }
      return result;
    }.property(':table:PK'),
:repeatfields:
:iffieldtype:
:int:
    :column:: 0,
:int: 
:long:
    :column:: '',
:long: 
:double:
    :column:: 0,
:double: 
:float:
    :column:: 0,
:float: 
:boolean:
    :column:: false,
:boolean:
:java.sql.Date:
    :column:: '',
    :column:UI: '',
:java.sql.Date:
:java.sql.Time:
    :column:: '',
    :column:UI: '',
:java.sql.Time:
:customfieldtype:
    :column:: null,
:customfieldtype:
:other:
    :column:: '',
:other:
:iffieldtype:
:repeatfields:
:repeatfields:
:iffieldtype:
:java.sql.Date:
    :column:changed: Ember.observer(":column:UI", function() {
        //add local time difference, javascript is using the browser time settings
        var localtimeoffset = (new Date().getTimezoneOffset()) * 1000 * 60;
        this.:column: = (new Date(this.:column:UI)).getTime() - localtimeoffset;
    }),
:java.sql.Date:
:java.sql.Time:
    :column:changed: Ember.observer(":column:UI", function() {
        var newtime = casttoTime(this.:column:UI);
        this.:column: = newtime.getTime();
    }),
:java.sql.Time:
:iffieldtype:
:repeatfields:
    init: function() {
        this._super();
        this.set(":table:PK", :Table:PK.create());
    }
});
