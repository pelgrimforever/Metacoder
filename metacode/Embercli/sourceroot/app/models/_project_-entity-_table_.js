//Metacoder: NO AUTHOMATIC UPDATE
//Change function definitions here, NOT in :project:-entitybase-:table:.js
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';
import :Table:Base from '../models/:project:-entitybase-:table:';

export default :Table:Base.extend({
    auth: null,
    tostring: function() {
      var result = this._super();
      return result;
    }.property(':table:PK')  
});
