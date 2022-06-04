//Auto generated code
//don't change things here, it will be overwritten
//extend classes in sitesecurity-entity-siteuser.js for table class definitions

import Ember from 'ember';
import SiteuserPK from '../models/sitesecurity-entitybase-siteuserpk';

export default Ember.Object.extend({
	tablename: 'siteuser',
    PK: null,
    siteuserPK: null,
    PKChanged: function() {
        if(this.PK!==null) {
          this.set("siteuserPK", SiteuserPK.create(this.PK));
        }
    }.observes('PK'),
    username: function() {
        if(this.siteuserPK===null) return '';
        else return this.siteuserPK.username;
    }.property('siteuserPK'),
    tostring: function() {
      var result = "";
      if(this.siteuserPK!=null) {
          result += this.siteuserPK.getUsername();
      }
      return result;
    }.property('siteuserPK'),
    userpassword: '',
    init: function() {
        this._super();
        this.set("siteuserPK", SiteuserPK.create());
    }
});
