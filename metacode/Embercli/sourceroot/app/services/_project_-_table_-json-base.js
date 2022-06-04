//Auto generated code
//don't change things here, it will be overwritten
//change in :project:-:table:-json.js
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';
import momentFormat from 'ember-moment/computeds/format';
import :Table:PK from '../models/:project:-entitybase-:table:pk';
import :Table: from '../models/:project:-entity-:table:';
:repeatuniqueforeignkeys:
import :Pktable:PK from '../models/:project:-entitybase-:pktable:pk';
:repeatuniqueforeignkeys:

export default Ember.Service.extend({
	:Table:json: Ember.inject.service(':project:-:table:-json'),
:repeatuniqueforeignkeys:
	:Pktable:json: Ember.inject.service(':project:-:pktable:-json'),

:repeatuniqueforeignkeys:
    PKtoJSON: function(:table:PK) {
        var jsonobj = null;
        if(:table:PK!=null) {
            var jsonobj = {
:repeatpkfields:
:notfk:
:iffieldtype:
:long:
                :column:: "" + :table:PK.:column::long:
:double:
                :column:: "" + :table:PK.:column::double:
:float:
                :column:: "" + :table:PK.:column::float:
:other:
                :column:: :table:PK.:column::other:
:iffieldtype:
:notfk:
:,:
:repeatpkfields:
            };
:repeatforeignkeys::inpk:
:repeatforeignkeyfields:
:iffieldtype:
:long:
	    jsonobj.:column: = "" + :table:PK.:uniquename:PK.get:Primarycolumn:();
:long:
:double:
	    jsonobj.:column: = "" + :table:PK.:uniquename:PK.get:Primarycolumn:();
:double:
:float:
	    jsonobj.:column: = "" + :table:PK.:uniquename:PK.get:Primarycolumn:();
:float:
:other:
	    jsonobj.:column: = :table:PK.:uniquename:PK.get:Primarycolumn:();
:other:
:iffieldtype:
:repeatforeignkeyfields:
:inpk::repeatforeignkeys:
	    }
	    return jsonobj;
	},
	PKtoJSONstring: function(:table:PK) {
            return JSON.stringify(this.PKtoJSON(:table:PK));
	},
	toJSON: function(:table:) {
	    var jsonobj = {
	        PK: this.PKtoJSON(:table:.:table:PK)
	    };
:repeatforeignkeys:
:notpk:
	    if(:table:.:uniquename:PK!=null) {
		jsonobj.:uniquename:PK = this.get(":Pktable:json").PKtoJSON(:table:.:uniquename:PK);
	    }
:notpk:
:repeatforeignkeys:
:repeatfields:
:iffieldtype:
:long:
	    jsonobj.:column: = "" + :table:.:column:;
:long:
:double:
	    jsonobj.:column: = "" + :table:.:column:;
:double:
:float:
	    jsonobj.:column: = "" + :table:.:column:;
:float:
:java.sql.Date:
	    jsonobj.:column: = "" + :table:.:column:;
:java.sql.Date:
:java.sql.Time:
	    jsonobj.:column: = "" + :table:.:column:;
:java.sql.Time:
:other:
	    jsonobj.:column: = :table:.:column:;
:other:
:iffieldtype:
:repeatfields:
	    return jsonobj;
		},
	toJSONstring: function(:table:) {
	    return JSON.stringify(this.toJSON(:table:));
	},
	PKfromJSON: function(:table:PK, jsonobj) {
	    if(jsonobj!=null) {
:repeatforeignkeys:
:inpk:
	        var :uniquename:properties = {
:repeatforeignkeyfields:
:iffieldtype:
:long:
	            :primarycolumn:: "" + jsonobj.:foreigncolumn::long:
:double:
	            :primarycolumn:: parseFloat(jsonobj.:foreigncolumn:, 10):double:
:float:
	            :primarycolumn:: parseFloat(jsonobj.:foreigncolumn:, 10):float:
:other:
	            :primarycolumn:: jsonobj.:foreigncolumn::other:
:iffieldtype:
:,:
:repeatforeignkeyfields:
                }
		var :uniquename:PK = :Pktable:PK.create();
		this.get(":Pktable:json").PKfromJSON(:uniquename:PK, :uniquename:properties);
		:table:PK.set(":uniquename:PK", :uniquename:PK);
:inpk:
:repeatforeignkeys:
:repeatpkfields:
:notfk:
	        :table:PK.set(":column:", jsonobj.:column:);
:notfk:
:repeatpkfields:
	    }
	},
	fromJSON: function(model, jsonobj) {
	    var :table:PK = :Table:PK.create();
	    this.PKfromJSON(:table:PK, jsonobj.PK);
	    model.set(":table:PK", :table:PK);
:repeatforeignkeys:
:notpk:
	    var :uniquename:PK = :Pktable:PK.create();
	    this.get(":Pktable:json").PKfromJSON(:uniquename:PK, jsonobj.:uniquename:PK);
	    model.set(":uniquename:PK", :uniquename:PK);
:notpk:
:repeatforeignkeys:
:repeatfields:
:iffieldtype:
:long:
	    model.set(":column:", "" + jsonobj.:column:);
:long:
:double:
	    model.set(":column:", parseFloat(jsonobj.:column:, 10));
:double:
:float:
	    model.set(":column:", parseFloat(jsonobj.:column:, 10));
:float:
:java.sql.Date:
	    model.set(":column:", jsonobj.:column:);
	    if(Ember.isEmpty(model.:column:)) model.set(":column:", "");
	    if(!Ember.isEmpty(model.:column:)) {
                model.set(":column:UI", moment(new Date(jsonobj.:column:)).format("YYYY MM DD"));
	    }
:java.sql.Date:
:java.sql.Time:
	    model.set(":column:", jsonobj.:column:);
	    if(Ember.isEmpty(model.:column:)) model.set(":column:", "");
	    if(!Ember.isEmpty(model.:column:)) {
		model.set(":column:UI", moment(new Date(jsonobj.:column:)).format("hh:mm:ss"));
	    }
:java.sql.Time:
:customfieldtype:
	    model.set(":column:", jsonobj.:column:);
:customfieldtype:
:other:
	    model.set(":column:", jsonobj.:column:);
:other:
:iffieldtype:
:repeatfields:
	}
});
