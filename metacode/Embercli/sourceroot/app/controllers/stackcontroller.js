/* 
//Metacoder: NO AUTHOMATIC UPDATE

    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/
import Ember from 'ember';

export default Ember.Controller.extend({
    templatestackservice: Ember.inject.service('templatestack'),
    stack: null,
    template: null,

    init: function() {
    	this.stack = this.get("templatestackservice").activestack;
    	this.template = this.stack.getActiveTemplate();
    	this.template.callback = this;
    },

	actions: {
        open: function(templatename) {
            this.send("opentemplate", this.stack, templatename);
        },
	},
	childclosed: function() {
        //return the data model for this instance
        this.set("model", this.template.data);
		console.log("stackcontroller for " + this.template.templatename + " closed with no action");
	}
});
