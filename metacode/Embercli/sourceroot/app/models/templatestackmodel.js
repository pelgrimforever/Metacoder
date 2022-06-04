//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Jan 26, 2016, 10:56:00 AM
    Generated on 19.0.2016 8:46
    Author     : Franky Laseure
*/

import Ember from 'ember';
import TemplateModel from '../models/templatestackmodel-template';

export default Ember.Object.extend({
    app: null,
    templates: [],

    setup: Ember.on('init', function() {
        this.set("templates", [] );
    }),
    //get the template name that is active
    //if no result, activate the default template from the app definition
    getActiveTemplate: function() {
    	var result = null;
    	if(this.templates.length>0) {
            result = this.templates.objectAt(this.templates.length-1);
    	} else {
    		result = TemplateModel.create( { "templatename": this.app.handlebar } );
    		this.templates.push(result);
    	}
    	return result;
    },
    //add new template to the stack
    addTemplate: function(template) {
        var newtemplate = TemplateModel.create( { "templatename": template } );
        if(this.templates.length>0) {
            newtemplate.callback = this.templates[this.templates.length-1];
        }
        this.templates.push(newtemplate);
    },
    getPrevioustemplate: function() {
    	var previoustemplate = null;
    	if(this.templates.length>1) {
            previoustemplate = this.templates.objectAt(this.templates.length-2);
    	}
    	return previoustemplate;
    },
    //set childdata of previous template
    setChilddata: function(childdata) {
        var activetemplate = this.getPrevioustemplate();
    	var previoustemplate = this.getPrevioustemplate();
    	if(previoustemplate!=null) {
    		previoustemplate.set("childdata", childdata);
            console.log("previoustemplate.callback " + previoustemplate.templatename);
    		activetemplate.callback.childclosed();
    	}
    },
    gotoPrevious: function() {
    	if(this.templates.length>0) {
    		this.templates.pop();
    		return this.getActiveTemplate();
    	}
    },
    getFirst: function() {
        return this.templates[0];
    },
    gotoFirst: function() {
        while(this.templates.length>1) {
            this.templates.pop();
        }
        return this.getActiveTemplate();
    },
    gotoFirst: function() {
        while(this.templates.length>0) {
            this.templates.pop();
        }
        return this.getActiveTemplate();
    }
});
