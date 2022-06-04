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
	WEBSERVICE: "/:Project:Webservices/webresources/",
	servicename: "rs:tablename:",

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
		  if(controller) {
			controller.callbackall(":table:");
		  }
		});
		return :table:s;
	}
});
