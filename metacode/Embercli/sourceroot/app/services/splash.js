//Metacoder: NO AUTHOMATIC UPDATE
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/
import Ember from 'ember';

export default Ember.Service.extend({
	dummy: "",

    appsloaded: [],
    showsplash: true,
    initapps: function(apps) {
        var l = apps.length;
        for(var i=0; i<l; i++) {
            this.appsloaded.push({ "appid": apps[i].appid, "loaded": false });
        }
    },
    apploaded: function(app) {
        console.log("app loaded: " + app.appid);
        var result = true;
        var l = this.appsloaded.length;
        for(var i=0; i<l; i++) {
            if(app.appid==this.appsloaded[i].appid) {
                this.appsloaded[i].loaded = true;
            }
            result = result && this.appsloaded[i].loaded;
            this.set("showsplash", !result);
        }
    },

});
