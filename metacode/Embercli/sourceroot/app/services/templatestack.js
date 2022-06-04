/* 
//Metacoder: NO AUTHOMATIC UPDATE

    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/
import Ember from 'ember';
import Model from '../models/templatestackmodel';

export default Ember.Service.extend({
    stacks: [],
    activestack: null,

    initapps: function(apps) {
        var l = apps.length;
        for(var i=0; i<l; i++) {
        	var app = apps.objectAt(i);
            this.stacks.push(Model.create( { "app": app, "templatestack": [] }) );
        }
    },
    getStack: function(appid) {
        var l = this.stacks.length;
        this.activestack = null;
        for(var i=0; i<l; i++) {
        	var stack = this.stacks.objectAt(i);
            if(stack.app.appid==appid) {
            	i = l;
            	this.activestack = stack;
            }
        }
        return this.activestack;
    },
    getActiveapp: function() {
    	return this.activestack.app;
    }

});
