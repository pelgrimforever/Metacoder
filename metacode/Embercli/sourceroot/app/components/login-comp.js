/* 
//Metacoder: NO AUTHOMATIC UPDATE

    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on 15.11.2015 11:7
    Author     : Franky Laseure
*/
import Ember from 'ember';
import base64 from 'npm:hi-base64';
import Logindata from '../models/logindata';

export default Ember.Component.extend({
    loginservice: Ember.inject.service('login'),
    splashservice: Ember.inject.service('splash'),
    siteuserservice: Ember.inject.service('sitesecurity-siteuser'),
    loginmodel: Logindata.create({ name: "", password: ""}),
    showlogin: true,
    app: null, //provided by parent frame in app-menuframework

    setup: Ember.on('init', function() {
        this.get("splashservice").apploaded(this.app);
    }),
    actions: {
        apploaded: function() {
            console.log("sendAction apploaded login");
            this.sendAction("apploaded", this.app);
        },
        login: function() {
            var model = this.get("loginmodel");
            var rawdata = model.name + ":" + md5(model.password);
            var data = base64.encode(rawdata);
            this.get("siteuserservice").authenticate(data, this);
        },
        logout: function() {
        }
    },
    authenticateresult: function(user) {
        if(Ember.isEmpty(user.siteuserPK.username)) {
        	this.get("loginservice").set("siteuser", null);
        	alert("Gebruiker niet gekend.");
        } else {
        	this.get("loginservice").set("siteuser", user);
            this.set("showlogin", false);
        }
    }
});

