//Metacoder: NO AUTHOMATIC UPDATE
//Change function definitions here, NOT in sitesecurity-entitybase-siteuser.js

import Ember from 'ember';
import SiteuserBase from '../models/sitesecurity-entitybase-siteuser';

export default SiteuserBase.extend({
    auth: null,
    tostring: function() {
      var result = this._super();
      return result;
    }.property('siteuserPK')  
});
