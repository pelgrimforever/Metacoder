//Metacoder: NO AUTHOMATIC UPDATE
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';

export default Ember.Route.extend({
    actions: {
        openModal: function(modalName) {
            return this.render(modalName, {
              into: 'application',
              outlet: 'modal'
            });
        },
        closeModal: function() {
            return this.disconnectOutlet({
              outlet: 'modal',
              parentView: 'application'
            });
        }
    }
});
