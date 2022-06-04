/* 
//Metacoder: NO AUTHOMATIC UPDATE

    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/
import Ember from 'ember';

export default Ember.Component.extend({
    classNames: ['app-tabs'],

    allapps: null,
    
    didInsertElement: function() {
    },
    actions: {
        menutab: function(app) {
            this.sendAction("menutab", app);
        }
    }
});
