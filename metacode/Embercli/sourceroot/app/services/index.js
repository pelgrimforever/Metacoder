//Metacoder: NO AUTHOMATIC UPDATE
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';

export default Ember.Service.extend({
    dummy: "",

    setidVisible(divid) {
        document.getElementById(divid).classList.remove('ishidden');
        document.getElementById(divid).classList.add('isvisible');
    },

    setidHidden(divid) {
        document.getElementById(divid).classList.remove('isvisible');
        document.getElementById(divid).classList.add('ishidden');
    },

    setidSUBVisible(divid) {
        document.getElementById(divid).classList.remove('issubhidden');
        document.getElementById(divid).classList.add('issubvisible');
    },

    setidSUBHidden(divid) {
        document.getElementById(divid).classList.remove('issubvisible');
        document.getElementById(divid).classList.add('issubhidden');
    },

    setidDisplay(divid) {
        document.getElementById(divid).classList.remove('hide');
        document.getElementById(divid).classList.add('display');
    },

    setidHide(divid) {
        document.getElementById(divid).classList.remove('display');
        document.getElementById(divid).classList.add('hide');
    },
});

