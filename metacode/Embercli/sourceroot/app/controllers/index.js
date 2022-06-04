//Metacoder: NO AUTHOMATIC UPDATE
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on 15.11.2015 11:7
    Author     : Franky Laseure
*/

import Ember from 'ember';
import Appconfig from '../models/appconfig';

export default Ember.Controller.extend({
    indexservice: Ember.inject.service('index'),
    dataservice: Ember.inject.service('dataservice'),
    templatestackservice: Ember.inject.service('templatestack'),
	apps: [ 
		Appconfig.create({ appid:"login", ondashboard: true, component: "login-comp", size: 1, openonclick: false, handlebar: "login", fixed: true, name: "login" }),
	],
    setup: Ember.on('init', function() {
        //this is to trigger the init from indexservice and dataservice
        this.get("indexservice").set("dummy", null);
        this.get("dataservice").set("dummy", null);
        this.get("templatestackservice").initapps(this.apps);
    }),	
    actions: {
    }
});

