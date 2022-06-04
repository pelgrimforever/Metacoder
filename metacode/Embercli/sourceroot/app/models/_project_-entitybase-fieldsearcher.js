//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';

export default Ember.Object.extend({
    //configuration constants
    booleansearcher: 0,
    datesearcher: 1,
    numbersearcher: 2,
    stringsearcher: 3,
    timesearcher: 4,
    
    //Contants
    AND: 0,
    OR: 1,

    STRING_EQUAL: 0,
    STRING_LIKE: 1,

    NUMERIC_EQUAL: 0,
    NUMERIC_BIGGER: 1,
    NUMERIC_BIGGEREQUAL: 2,
    NUMERIC_SMALLER: 3,
    NUMERIC_SMALLEREQUAL: 4,

    DATETIME_EQUAL: 0,
    DATETIME_BIGGER: 1,
    DATETIME_BIGGEREQUAL: 2,
    DATETIME_SMALLER: 3,
    DATETIME_SMALLEREQUAL: 4,

    //config parameters
    type: 0,

    //build parameters
    fieldname: "",
    values: [],
    andoroperator: this.OR,

    init: function() {
        this.values = [];
        this.andoroperator = this.OR;
    },
    getshortname: function() {
        var parts = this.fieldname.split(".");
        return parts[parts.length-1];
    },
    setAndoroperator: function(andor) {
        this.andoroperator = andor;
    },
    used: function() {
        return this.values.get('length')>0;
    },
    valuestoJSON: function() {
        return this.values.toArray();
    },
    evaluateselector: function(selector) {
        var result = this.NUMERIC_EQUAL;
        if(selector==">") {
            result = this.NUMERIC_BIGGER;
        } else if(selector==">=") {
            result = this.NUMERIC_BIGGEREQUAL;
        } else if(selector=="<=") {
            result = this.NUMERIC_SMALLEREQUAL;
        } else if(selector=="<") {
            result = this.NUMERIC_SMALLER;
        }
        return result;
    }
});
