//Auto generated code
//don't change things here, it will be overwritten
//redefine classes in :project:-entity-:table:-datahandler.js
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';
import I:Table:Operation from '../models/:project:-entity-:table:-operation';
import :Table: from '../models/:project:-entity-:table:';

export default Ember.Service.extend({
	:Table:JSON: Ember.inject.service(':project:-:table:-json'),
:repeatforeignkeys:
	:Pktable:JSON: Ember.inject.service(':project:-:pktable:-json'),
:repeatforeignkeys:
:repeatreferences:
	:Extablename:JSON: Ember.inject.service(':project:-:extablename:-json'),
:repeatreferences:
	WEBSERVICE: "/:Project:Webservices/webresources/",
	servicename: "rs:table:",

	getcount: function(controller) {
		var result = 0;
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_COUNT;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.:table:countresponse(response.recordcount);
		});
	},

	getAll: function(controller) {
		var :table:s = [];
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_ALL;
		var self = this;
		$.ajax({
			type: "POST",
			url: url,
			data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}}",
			dataType: "json",
			contentType: "application/json;"
		}).then(function(response) {
			for(var i = 0; i < response.length; i++) {
				var :table: = response[i];
				var model = :Table:.create();
				self.get(":Table:JSON").fromJSON(model, :table:);
				:table:s.addObject(model);
			}
			if(controller!=null) {
				controller.callbackall(":table:");
			}	
		});
		return :table:s;
	},

	getOne: function(:table:pk, controller) {
		var :table: = :Table:.create();
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_:TABLE:;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":table:pk\": " + self.get(":Table:JSON").PKtoJSONstring(:table:pk) + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    self.get(":Table:JSON").fromJSON(:table:, response);
			if(controller!=null) {
				controller.callbackone(":table:");
			}	
		});
		return :table:;
	},

:repeatforeignkeys:
	load:Table:s4:uniquename:: function(:pktable:pk, controller) {
		var :table:s = [];
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_:Uniquename:;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":pktable:pk\": " + this.get(':Pktable:JSON').PKtoJSONstring(:pktable:pk) + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
			for(var i = 0; i < response.length; i++) {
				var :table: = response[i];
				var model = :Table:.create();
				self.get(":Table:JSON").fromJSON(model, :table:);
				:table:s.addObject(model);
			}      
			if(controller!=null) {
				controller.:table:s4:uniquename:();
			}	
		});
		return :table:s;
	},

:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
	load:Table:4:uniquename:: function(:uniquename:pk, controller) {
		var :table: = :Table:.create();
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_:Uniquename:;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":extablename:pk\": " + this.get(':Extablename:JSON').PKtoJSONstring(:uniquename:pk) + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    self.get(":Table:JSON").fromJSON(:table:, response);
			if(controller!=null) {
				controller.:table:s4:uniquename:();
			}	
		});
		return :table:;
	},

:ifforeignkey:
:repeatexternalforeignkeys:
	search: function(:table:searcher, controller) {
		var :table:s = [];
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_SEARCH;
		var searchstring = JSON.stringify(:table:searcher.toJSON());
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"search\": " + searchstring + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
			for(var i = 0; i < response.length; i++) {
				var :table: = response[i];
				var model = :Table:.create();
				self.get(":Table:JSON").fromJSON(model, :table:);
				:table:s.addObject(model);
			}
			if(controller!=null) {
				controller.:table:search();
			}	
		});
		return :table:s;
	},

	searchcount: function(:table:searcher, controller, resultparameter) {
		var result = 0;
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_SEARCHCOUNT;
		var searchstring = JSON.stringify(:table:searcher.toJSON());
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"search\": " + searchstring + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.set(resultparameter, response.recordcount);
		});
	},

	insert: function(:table:, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_INSERT;
		var operation = idata.INSERT_:TABLE:;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":table:\": " + self.get(":Table:JSON").toJSONstring(:table:) + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.insertresponse(response);
		});
	},

	save: function(:table:, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_UPDATE;
		var operation = idata.UPDATE_:TABLE:;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":table:\": " + self.get(":Table:JSON").toJSONstring(:table:) + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.saveresponse(response);
		});
	},

	del: function(:table:, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_DELETE;
		var operation = idata.DELETE_:TABLE:;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":table:\": " + self.get(":Table:JSON").toJSONstring(:table:) + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.delresponse(response);
		});
	},

//SECURE SECTION START
	sec_getcount: function(user, controller) {
		var result = 0;
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_COUNT;
		var auth = "";
		if(user!=null) auth = user.auth;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}" +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.:table:countresponse(response.recordcount);
		});
	},

	sec_get:Table:s: function(user, controller) {
		var :table:s = [];
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_ALL;
		var self = this;
		var auth = "";
		if(user!=null) auth = user.auth;
		$.ajax({
			type: "POST",
			url: url,
			data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}" +
		                ", \"auth\": \"" + auth + "\"}",
			dataType: "json",
			contentType: "application/json;"
		}).then(function(response) {
			for(var i = 0; i < response.length; i++) {
				var :table: = response[i];
				var model = :Table:.create();
				self.get(":Table:JSON").fromJSON(model, :table:);
				:table:s.addObject(model);
			}
			if(controller!=null) {
				controller.callbackall(":table:");
			}	
		});
		return :table:s;
	},

	sec_getOne: function(user, :table:pk, controller) {
		var :table: = :Table:.create();
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_:TABLE:;
		var self = this;
		var auth = "";
		if(user!=null) auth = user.auth;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":table:pk\": " + self.get(":Table:JSON").PKtoJSONstring(:table:pk) +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    self.get(":Table:JSON").fromJSON(:table:, response);
			if(controller!=null) {
				controller.callbackone(":table:");
			}	
		});
		return :table:;
	},

:repeatforeignkeys:
	sec_load:Table:s4:uniquename:: function(user, :pktable:pk, controller) {
		var :table:s = [];
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_:Uniquename:;
		var self = this;
		var auth = "";
		if(user!=null) auth = user.auth;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":pktable:pk\": " + self.get(":Pktable:JSON").PKtoJSONstring(:pktable:pk) +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
			for(var i = 0; i < response.length; i++) {
				var :table: = response[i];
				var model = :Table:.create();
				self.get(":Table:JSON").fromJSON(model, :table:);
				:table:s.addObject(model);
			}
			if(controller!=null) {
				controller.:table:s4:uniquename:();
			}	
		});
		return :table:s;
	},

:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
	sec_load:Table:4:uniquename:: function(user, :uniquename:pk, controller) {
		var :table: = :Table:.create();
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_:Uniquename:;
		var self = this;
		var auth = "";
		if(user!=null) auth = user.auth;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":extablename:pk\": " + self.get(":Extablename:JSON").PKtoJSONstring(:extablename:pk) +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    self.get(":Table:JSON").fromJSON(:table:, response);
			if(controller!=null) {
				controller.:table:s4:uniquename:();
			}	
		});
		return :table:;
	},

:ifforeignkey:
:repeatexternalforeignkeys:
	sec_search: function(user, :table:searcher, controller) {
		var :table:s = [];
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_SEARCH;
		var searchstring = JSON.stringify(:table:searcher.toJSON());
		var self = this;
		var auth = "";
		if(user!=null) auth = user.auth;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"search\": " + searchstring +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
			for(var i = 0; i < response.length; i++) {
				var :table: = response[i];
				var model = :Table:.create();
				self.get(":Table:JSON").fromJSON(model, :table:);
				:table:s.addObject(model);
			}
			if(controller!=null) {
				controller.:table:search();
			}	
		});
		return :table:s;
	},

	sec_searchcount: function(:table:searcher, user, controller, resultparameter) {
		var result = 0;
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_SEARCHCOUNT;
		var searchstring = JSON.stringify(:table:searcher.toJSON());
		var self = this;
		var auth = "";
		if(user!=null) auth = user.auth;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"search\": " + searchstring +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.set(resultparameter, response.recordcount);
		});
	},

	sec_insert: function(:table:, user, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SECUREINSERT;
		var operation = idata.INSERT_:TABLE:;
		var self = this;
		var auth = "";
		if(user!=null) auth = user.auth;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":table:\": " + self.get(":Table:JSON").toJSONstring(:table:) +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.insertresponse(":table:", response);
		});
	},

	sec_save: function(:table:, user, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SECUREUPDATE;
		var operation = idata.UPDATE_:TABLE:;
		var self = this;
		var auth = "";
		if(user!=null) auth = user.auth;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":table:\": " + self.get(":Table:JSON").toJSONstring(:table:) +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.saveresponse(":table:", response);
		});
	},

	sec_del: function(:table:, user, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = I:Table:Operation.create();
		var operationtype = idata.OPERATIONTYPE_SECUREDELETE;
		var operation = idata.DELETE_:TABLE:;
		var self = this;
		var auth = "";
		if(user!=null) auth = user.auth;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \":table:\": " + self.get(":Table:JSON").toJSONstring(:table:) +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.delresponse(":table:", response);
		});
	}
//SECURE SECTION END
});
