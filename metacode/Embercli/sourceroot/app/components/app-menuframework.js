/* 
//Metacoder: NO AUTHOMATIC UPDATE

    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on 15.11.2015 11:7
    Author     : Franky Laseure
*/
import Ember from 'ember';

export default Ember.Component.extend({
    indexservice: Ember.inject.service('index'),
    splashservice: Ember.inject.service('splash'),
    stackservice: Ember.inject.service('templatestack'),
    apps: [],
    siteuser: null,

    fixedapps: [],
    freeapps: [],
    allapps: [],
    
    setup: Ember.on('init', function() {
        //needed to activate observers
        this.get("indexservice").set("dummy", "");
        this.get("splashservice").initapps(this.apps);
    }),

    actions: {
        openapp: function(app) {
            this.showapp(app, true);
        },
        menutab: function(app) {
            var activeapp = this.get("stackservice").getActiveapp();
            activeapp.set("isactive", false);
            this.get('indexservice').setidHidden("appview");
            if(activeapp.fixed) {
                this.get('indexservice').setidHidden("fixedapp" + activeapp.fixedid);
            } else {
                this.get('indexservice').setidHidden("app");
            }
            if(!Ember.isEmpty(app)) {
                this.showapp(app, false);
            }
            return false;
        },
        openpopup: function() {
            this.get('indexservice').setidVisible("popup");
        },
        closepopup: function() {
            this.get('indexservice').setidHidden("popup");
        }
    },
    
    showapp(app, reopen) {
        //getStack activates the new app
        var activestack = this.get("stackservice").getStack(app.appid);
        //activestack.getActiveapp()==app (get the activate app)
        var activeapp = activestack.app;
        console.log("activeapp " + activeapp.appid);
        //get the last used template
        var activetemplate = activestack.getActiveTemplate();
        console.log("activetemplate " + activetemplate.templatename);
        activeapp.set("isactive", true);
        var applaunched = false;
        if(app.fixed) {
            //search if app is launched
            if(Ember.isEmpty(app.fixedid)) {
                //not launched, take the next free id
                //if none is free, force as normal app
                if(this.fixedapps.get('length')<10) {
                    app.fixedid = this.fixedapps.get('length');
                    this.fixedapps.addObject(app);
                    this.allapps.addObject(app);
                    this.get('indexservice').setidVisible("fixedapp" + app.fixedid);
                    applaunched = true;
                    this.sendAction("renderapp", activetemplate.templatename, "fixedapp" + app.fixedid);
                }
            } else {
                //only make visible
                this.get('indexservice').setidVisible("fixedapp" + app.fixedid);
                if(reopen) {
                    activetemplate = activestack.gotoFirst();
                    this.sendAction("renderapp", activetemplate.templatename, "fixedapp" + app.fixedid);
                }
                applaunched = true;
            }
        } 
        if(!applaunched) {
            this.get('indexservice').setidVisible("app");
            this.addFreeapp(app);
            if(reopen) {
                activetemplate = activestack.gotoFirst();
                alert("reopen template " + activetemplate.templatename);
                this.sendAction("renderapp", activetemplate.templatename, "app");
            } else {
                this.sendAction("renderapp", activetemplate.templatename, "app");
            }
        }
        this.get('indexservice').setidVisible("appview");
        return false;
    },

    addFreeapp: function(app) {
        var found = false;
        var l = this.freeapps.get('length');
        for(var i=0; i<l && !found; i++) {
            if(this.freeapps.objectAt(i).appid===app.appid) {
                found = true;
            }
        }
        if(!found) {
            this.freeapps.addObject(app);
            this.allapps.addObject(app);
        }
    },
});
