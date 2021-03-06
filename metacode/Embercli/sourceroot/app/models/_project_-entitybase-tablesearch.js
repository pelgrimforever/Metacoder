//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';

export default Ember.Object.extend({
    //Contants
    AND: 0,
    OR: 1,

    EQUAL: 0,
    LIKE: 1,

    maxresults: 50,
    docount: false,

    primarykeys: [],
    fieldsearchers: [],
    keysearchers: [],
    andoroperator: 1,
    
    init: function() {
        this.primarykeys = [];
        this.fieldsearchers = [];
        this.keysearchers = [];
        this.andoroperator = this.OR;
    },
    addFieldsearcher: function(fieldsearcher) {
        fieldsearcher.reset();
        this.fieldsearchers.pushObject(fieldsearcher);
    },
    addPrimarykey: function(primarykey) {
        this.primarykeys.pushObject(primarykey);
    },
    addKeysearcher: function(keysearcher) {
        this.keysearchers.pushObject(keysearcher);
    },
    addStringvalues: function(stringsearch, values) {
        stringsearch.values.pushObjects(values);
    },
    addLongvalues: function(numbersearch, values, operators) {
        numbersearch.values.pushObjects(values);
        numbersearch.operators.pushObjects(operators);
    },
    addNumbervalues: function(numbersearch, values, operators) {
        var numvalues = [values.length];
        var i=0;
        for(i=0; i<values.length; i++) {
            numvalues[i] = parseFloat(values[i]);
        }
        numbersearch.values.pushObjects(numvalues);
        numbersearch.operators.pushObjects(operators);
    },
    addDatevalues: function(datesearch, values, operators) {
        datesearch.values.pushObjects(values);
        datesearch.operators.pushObjects(operators);
    },
    addTimevalues: function(timesearch, values, operators) {
        timesearch.values.pushObjects(values);
        timesearch.operators.pushObjects(operators);
    },
    addBooleanvalue: function(booleansearch, value) {
        booleansearch.values.clear();
        booleansearch.values.pushObject(value);
    },
    setTablesearcher: function(keysearcher, tablesearch) {
        keysearcher.setTablesearch(tablesearch);
    },
    used: function() {
        var used = this.primarykeys.get('length')>0;
        if(!used) {
            var i=0;
            var fieldsearcher = null;
            for(i=0; i<this.fieldsearchers.get('length'); i++) {
                fieldsearcher = this.fieldsearchers.objectAt(i);
                if(fieldsearcher.used()) {
                    used = true;
                    break;
                }
            }
        }
        if(!used) {
            var i=0;
            var keysearcher = null;
            for(i=0; i<this.keysearchers.get('length'); i++) {
                keysearcher = this.keysearchers.objectAt(i);
                if(keysearcher.used()) {
                    used = true;
                    break;
                }
            }
        }
        return used;
    },
    resetfields: function() {
        var i=0;
        var fieldsearcher = null;
        for(i=0; i<this.fieldsearchers.get('length'); i++) {
            fieldsearcher = this.fieldsearchers.objectAt(i);
            fieldsearcher.reset();
        }
    },
    reset: function() {
        this.resetfields();
        this.primarykeys.clear();
    }
});
