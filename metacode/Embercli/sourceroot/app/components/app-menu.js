/* 
//Metacoder: NO AUTHOMATIC UPDATE

    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/
import Ember from 'ember';

export default Ember.Component.extend({
    classNames: ['app-menu'],

    applist: [],
    
    didInsertElement: function() {
    },
    actions: {
        openapp: function(app) {
            this.sendAction("openapp", app);
        }
    }
});
