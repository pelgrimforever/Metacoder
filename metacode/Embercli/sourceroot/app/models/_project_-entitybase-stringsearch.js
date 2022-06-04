//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';
import Fieldsearcher from '../models/:project:-entitybase-fieldsearcher';

export default Fieldsearcher.extend({
    //build parameters
    compareoperator: this.STRING_EQUAL,

    init: function() {
      this._super();
      this.type = this.stringsearcher;
      this.compareoperator = this.STRING_EQUAL;
    },

    setCompareoperator: function(compare) {
        this.compareoperator = compare;
    },

    reset: function() {
        this.values.clear();
    },
    toJSON: function() {
        var json = {
	    values: this.valuestoJSON(), 
	    compareoperator: this.compareoperator,
	    andor: this.andoroperator
        };
        return json;
    }
});
