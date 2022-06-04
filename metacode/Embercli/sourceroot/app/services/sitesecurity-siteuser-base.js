//Auto generated code
//don't change things here, it will be overwritten
//redefine classes in sitesecurity-entity-siteuser-datahandler.js

import Ember from 'ember';
import ISiteuserOperation from '../models/sitesecurity-entity-siteuser-operation';
import Siteuser from '../models/sitesecurity-entity-siteuser';

export default Ember.Service.extend({
	SiteuserJSON: Ember.inject.service('sitesecurity-siteuser-json'),
	WEBSERVICE: "/SitesecurityWebservices/webresources/",
	servicename: "rssiteuser",

	getcount: function(controller) {
		var result = 0;
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_COUNT;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.countresponse(response.recordcount);
		});
	},

	getSiteusers: function() {
		var siteusers = [];
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
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
		  for(var siteuser in response) {
		    var model = Siteuser.create();
		    self.get('SiteuserJSON').fromJSON(model, siteuser);
		    siteusers.addObject(model);
		  }
		});
		return siteusers;
	},

	getSiteuser: function(siteuserpk) {
		var siteuser = Siteuser.create();
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_SITEUSER;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"siteuserpk\": " + this.get('SiteuserJSON').PKtoJSONstring(siteuserpk) + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    self.get('SiteuserJSON').fromJSON(siteuser, response);
		});
		return siteuser;
	},

	loadSiteuser4siteusergroup: function(siteusergrouppk) {
		var siteuser = Siteuser.create();
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_Siteusergroup;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"siteusergrouppk\": " + this.get('SiteusergroupJSON').PKtoJSONstring(siteusergrouppk) + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    self.get('SiteuserJSON').fromJSON(siteuser, response);
		});
		return siteuser;
	},

	search: function(siteusersearcher) {
		var siteusers = [];
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_SEARCH;
		var searchstring = JSON.stringify(siteusersearcher.toJSON());
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"search\": " + searchstring + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		  for(var siteuser in response) {
		    var model = Siteuser.create();
		    self.get('SiteuserJSON').fromJSON(model, siteuser);
		    siteusers.addObject(model);
		  }
		});
		return siteusers;
	},

	searchcount: function(siteusersearcher, controller) {
		var result = 0;
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_SEARCHCOUNT;
		var searchstring = JSON.stringify(siteusersearcher.toJSON());
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"search\": " + searchstring + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.countresponse(response.recordcount);
		});
	},

	insert: function(siteuser, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_INSERT;
		var operation = idata.INSERT_SITEUSER;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"siteuser\": " + this.get('SiteuserJSON').toJSONstring(siteuser) + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    self.get('SiteuserJSON').insertresponse(response);
		});
	},

	save: function(siteuser, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_UPDATE;
		var operation = idata.UPDATE_SITEUSER;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"siteuser\": " + this.get('SiteuserJSON').toJSONstring(siteuser) + "}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.saveresponse(response);
		});
	},

	del: function(siteuser, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_DELETE;
		var operation = idata.DELETE_SITEUSER;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"siteuser\": " + this.get('SiteuserJSON').toJSONstring(siteuser) + "}",
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
		var idata = ISiteuserOperation.create();
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
		    controller.countresponse(response.recordcount);
		});
	},

	sec_getSiteusers: function(user) {
		var siteusers = [];
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_ALL;
		var auth = "";
		if(user!=null) auth = user.auth;
		var self = this;
		$.ajax({
			type: "POST",
			url: url,
			data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}" +
		                ", \"auth\": \"" + auth + "\"}",
			dataType: "json",
			contentType: "application/json;"
		}).then(function(response) {
		  for(var siteuser in response) {
		    var model = Siteuser.create();
		    self.get('SiteuserJSON').fromJSON(model, siteuser);
		    siteusers.addObject(model);
		  }
		});
		return siteusers;
	},

	sec_getSiteuser: function(user, siteuserpk) {
		var siteuser = Siteuser.create();
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_SITEUSER;
		var auth = "";
		if(user!=null) auth = user.auth;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"siteuserpk\": " + this.get('SiteuserJSON').PKtoJSONstring(siteuserpk) +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    self.get('SiteuserJSON').fromJSON(siteuser, response);
		});
		return siteuser;
	},

	sec_loadSiteuser4siteusergroup: function(user, siteusergrouppk) {
		var siteuser = Siteuser.create();
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_Siteusergroup;
		var auth = "";
		if(user!=null) auth = user.auth;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"siteusergrouppk\": " + this.get('SiteusergroupJSON').PKtoJSONstring(siteusergrouppk) +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    self.get('SiteuserJSON').fromJSON(siteuser, response);
		});
		return siteuser;
	},

	sec_search: function(user, siteusersearcher) {
		var siteusers = [];
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_SEARCH;
		var searchstring = JSON.stringify(siteusersearcher.toJSON());
		var auth = "";
		if(user!=null) auth = user.auth;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"search\": " + searchstring +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		  for(var siteuser in response) {
		    var model = Siteuser.create();
		    self.get('SiteuserJSON').fromJSON(model, siteuser);
		    siteusers.addObject(model);
		  }
		});
		return siteusers;
	},

	sec_searchcount: function(siteusersearcher, user, controller) {
		var result = 0;
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SECURESELECT;
		var operation = idata.SELECT_SEARCHCOUNT;
		var searchstring = JSON.stringify(siteusersearcher.toJSON());
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
			controller.countresponse(response.recordcount);
		});
	},

	sec_insert: function(siteuser, user, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SECUREINSERT;
		var operation = idata.INSERT_SITEUSER;
		var auth = "";
		if(user!=null) auth = user.auth;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"siteuser\": " + this.get('SiteuserJSON').toJSONstring(siteuser) +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    controller.insertresponse(response);
		});
	},

	sec_save: function(siteuser, user, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SECUREUPDATE;
		var operation = idata.UPDATE_SITEUSER;
		var auth = "";
		if(user!=null) auth = user.auth;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"siteuser\": " + this.get('SiteuserJSON').toJSONstring(siteuser) +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    self.get('SiteuserJSON').saveresponse(response);
		});
	},

	sec_del: function(siteuser, user, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SECUREDELETE;
		var operation = idata.DELETE_SITEUSER;
		var auth = "";
		if(user!=null) auth = user.auth;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"siteuser\": " + this.get('SiteuserJSON').toJSONstring(siteuser) +
		                ", \"auth\": \"" + auth + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    self.get('SiteuserJSON').delresponse(response);
		});
	}
//SECURE SECTION END
});
