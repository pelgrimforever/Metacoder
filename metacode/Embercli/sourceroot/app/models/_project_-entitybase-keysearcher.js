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
    foreignkeysearcher: 0,
    primarykeysearcher: 1,
    relationalkeysearcher: 2,

    //build parameters
    tablesearches: [],
    andoroperator: 1,

    init: function() {
        this.tablesearches = [];
    },
    setTablesearch: function(tablesearch) {
        //this.tablesearches.clear();
        this.tablesearches.pushObject(tablesearch);
    },
    getTablesearch: function() {
        if(this.tablesearches.get('length')>0) {
            return this.tablesearches.objectAt(0);
        } else {
            return null;
        }
    },
    resetfields: function() {
        var i=0;
        var tablesearch = null;
        for(i=0; i<this.tablesearches.get('length'); i++) {
            tablesearch = this.tablesearches.objectAt(i);
            tablesearch.resetfields();
        }
    },
    reset: function() {
        var i=0;
        var tablesearch = null;
        for(i=0; i<this.tablesearches.get('length'); i++) {
            tablesearch = this.tablesearches.objectAt(i);
            tablesearch.reset();
        }
    },
    used: function() {
        var isused = false;
        var i=0;
        var tablesearch = null;
        for(i=0; i<this.tablesearches.get('length'); i++) {
            tablesearch = this.tablesearches.objectAt(i);
            if(tablesearch.used()) {
                isused = true;
                break;
            }
        }
        return isused;
    },
    toJSON: function() {
        var json = {};
        if(this.used()) {
            var tablesearch = null;
            var jsonarray = [];
            for(var i=0; i<this.tablesearches.get('length'); i++) {
                tablesearch = this.tablesearches.objectAt(i);
                jsonarray.push(tablesearch.toJSON());
            }
            json = jsonarray.toArray();
	    }
        return json;
    }
});
