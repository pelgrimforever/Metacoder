//Metacoder: NO AUTHOMATIC UPDATE
//Change function definitions here, NOT in dataservice-base.js
/* 
    Created on : Mar 08, 2016, 15:52:01 PM
    Generated on 1.1.2016 17:37
    Author     : Franky Laseure
*/

import Ember from 'ember';
import DataserviceBase from '../services/dataservice-base';

export default DataserviceBase.extend({
	dummy: "",

    setup: Ember.on('init', function() {
    	this.set("tablelist", []);
    	this.loadAlltables();
    	this.siteuserchanged();
    }),	

    //load user depended data
	siteuserchanged: Ember.observer('loginservice.siteuser', function() { 
		
	}),

	//custom lists
	//******list: [],
	//******listchanges: 0, 

	/*get******list: function() {
		var siteuser = this.get("loginservice").siteuser;
		if(siteuser==null) {
			this.set("******list", []);
		} else {
			this.set("******list", this.get("******service").load********4recipeuser(siteuser.******PK));
		}
	},*/

});
