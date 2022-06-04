//Auto generated code
//don't change things here, it will be overwritten
//redefine class in dataservice.js
/* 
    Created on : Mar 8, 2016, 15:50:01 PM
    Generated on 8.2.2016 15:50
    Author     : Franky Laseure
*/

import Ember from 'ember';

export default Ember.Service.extend({
    loginservice: Ember.inject.service('login'),

:repeattables:
	:table:service: Ember.inject.service(':project:-:table:'),
:repeattables:
:repeatviews:
	:table:service: Ember.inject.service(':project:-:table:'),
:repeatviews:

	tablelist: [],

:repeattables:
	:table:all: [],
	:table:list: [],
	:table:: null,
	:table:allchanges: 0, 
	:table:listchanges: 0, 
	:table:changes: 0, 
	:table:inserted: 0,
	:table:saved: 0,
	:table:deleted: 0,

:repeattables:
:repeatviews:
	:table:all: [],
	:table:list: [],
	:table:: null,
	:table:allchanges: 0, 
	:table:listchanges: 0, 
	:table:changes: 0, 

:repeatviews:

	loadAlltables: function() {
		var l = this.tablelist.length;
		for(var i=0; i<l; i++) {
			var tablename = this.tablelist[i];
			this.getAll(tablename);
		}
	},
	getAll: function(tablename) {
		this.set(tablename + "all", this.get(tablename + "service").getAll(this));
	},
	callbackall: function(tablename) {
		this.set(tablename + "allchanges", this.get(tablename + "allchanges") + 1);
		console.log("updated  " + tablename + "allchanges " + this.get(tablename + "allchanges"));
	},
	callbacklist: function(tablename) {
		this.set(tablename + "listchanges", this.get(tablename + "listchanges") + 1);
		console.log("updated  " + tablename + "listchanges " + this.get(tablename + "listchanges"));
	},
	getOne: function(tablename, primarykey) {
		this.set(tablename, this.get(tablename + "service").getOne(primarykey, this));
	},
	callbackone: function(tablename) {
		this.set(tablename + "changes", this.get(tablename + "changes") + 1);
	},
	
	//database changes
	sec_insert: function(tablename, record) {
		var siteuser = this.get("loginservice").siteuser;
		this.get(tablename + "service").sec_insert(record, siteuser, this);
	},
	insertresponse: function(tablename, response) {
        var status = response.status;
        if(status=='OK') {
        	this.set(tablename + "inserted", this.get(tablename + "inserted") + 1);
        } else {
            alert("Foutboodschap: " + status);
        }
	},

	sec_save: function(tablename, record) {
		var siteuser = this.get("loginservice").siteuser;
		this.get(tablename + "service").sec_save(record, siteuser, this);
	},
	saveresponse: function(tablename, response) {
        var status = response.status;
        if(status=='OK') {
        	this.set(tablename + "saved", this.get(tablename + "saved") + 1);
        } else {
            alert("Foutboodschap: " + status);
        }
	},

	sec_del: function(tablename, record) {
		var siteuser = this.get("loginservice").siteuser;
		this.get(tablename + "service").sec_del(record, siteuser, this);
	},
	delresponse: function(tablename, response) {
        var status = response.status;
        if(status=='OK') {
        	this.set(tablename + "deleted", this.get(tablename + "deleted") + 1);
        } else {
            alert("Foutboodschap: " + status);
        }
	},
});

