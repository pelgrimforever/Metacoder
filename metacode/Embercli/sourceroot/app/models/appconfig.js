//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';

export default Ember.Object.extend({
    appid: null,        //app id
    component: "",       //component, show in small app panels to represent the minified app or a menu choice
    openonclick: false, //when true, a click in the small app will open the normal sized handlebar
    handlebar: null,    //normal sized handlebar
    fixed: false,       //when true, a div is used which is set visible/invisible, the UI will not be destroyed
    name: "",           //display name of the app
    isactive: false     //internal property, indicates if the handlebar frop this app is active in the UI
});
