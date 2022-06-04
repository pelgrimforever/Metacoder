//Metacoder: NO AUTHOMATIC UPDATE
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on 15.11.2015 11:7
    Author     : Franky Laseure
*/

import Ember from 'ember';

export default Ember.Route.extend({
	actions: {
        renderapp: function(handlebar, outletname) {
            console.log("renderapp " + handlebar + " " + outletname);
            return this.render(handlebar, {
                into: 'index',
                outlet: outletname
            });
        },
        rendersubapp: function(handlebar, outletname) {
            return this.render(handlebar, {
                into: "submenu",
                outlet: outletname
            });
        },
        opentemplate: function(templatestack, templatename) {
            templatestack.addTemplate(templatename);
            this.rendertemplate(templatestack, templatename);
        },
        openprevious: function(templatestack) {
            var previoustemplate = templatestack.gotoPrevious();
            this.rendertemplate(templatestack, previoustemplate.templatename);
        },
        openPopup: function(templatename) {
            this.setidVisible("popup");
            this.lastpopupoutlet = templatename;
            return this.render(templatename, {
                into: 'index',
                outlet: 'popup'
            });
        },
        closePopup: function() {
            this.setidHidden("popup");
            return this.disconnectOutlet({
                outlet: 'popup',
                parentView: 'index'
            });
        }
	},
    rendertemplate: function(templatestack, templatename) {
        var app = templatestack.app;
        var outletname = "app";
        if(app.fixed) {
            outletname = app.fixedid;
        }
        console.log("opentemplate " + templatename + " " + outletname);
        return this.render(templatename, {
            into: 'index',
            outlet: outletname
        });
    },

    setidVisible: function(divid) {
        document.getElementById(divid).classList.remove('ishidden');
        document.getElementById(divid).classList.add('isvisible');
    },

    setidHidden: function(divid) {
        document.getElementById(divid).classList.remove('isvisible');
        document.getElementById(divid).classList.add('ishidden');
    }
});

