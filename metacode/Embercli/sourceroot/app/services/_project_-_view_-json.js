//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';
import momentFormat from 'ember-moment/computeds/format';

export default Ember.Service.extend({
	fromJSON: function(model, jsonobj) {
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
