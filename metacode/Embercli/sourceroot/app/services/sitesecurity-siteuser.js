//Metacoder: NO AUTHOMATIC UPDATE
//Change function definitions here, NOT in sitesecurity-entitybase-siteuser-operation.js

import Ember from 'ember';
import ServiceSiteuserBase from '../services/sitesecurity-siteuser-base';
import ISiteuserOperation from '../models/sitesecurity-entity-siteuser-operation';
import Siteuser from '../models/sitesecurity-entity-siteuser';

export default ServiceSiteuserBase.extend({
	SiteuserJSON: Ember.inject.service('sitesecurity-siteuser-json'),
	authenticate: function(data, controller) {
		var url = this.WEBSERVICE + this.servicename;
		var idata = ISiteuserOperation.create();
		var operationtype = idata.OPERATIONTYPE_SELECT;
		var operation = idata.SELECT_AUTHENTICATE;
		var self = this;
		$.ajax({
		        type: "POST",
		        url: url,
		        data: "{\"operation\": {\"type\": " + operationtype + ", \"operation\": " + operation + "}, \"data\": \"" + data + "\"}",
		        dataType: "json",
		        contentType: "application/json;"
		}).then(function(response) {
		    var siteuser = Siteuser.create();
		    self.get('SiteuserJSON').fromJSON(siteuser, response);
		    siteuser.auth = data;
		    controller.authenticateresult(siteuser);
		});
	}    
});
