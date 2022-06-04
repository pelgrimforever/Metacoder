//Metacoder: NO AUTHOMATIC UPDATE
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on 15.11.2015 11:7
    Author     : Franky Laseure
*/

import Ember from 'ember';

export default Ember.Service.extend({
	siteuser: null,
	loginok: false,
	siteuserchanged: Ember.observer('siteuser', function() { 
		this.set("loginok", this.siteuser!=null); 
	}),
});


