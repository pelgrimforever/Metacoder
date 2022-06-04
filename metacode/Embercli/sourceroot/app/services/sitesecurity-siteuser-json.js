//Auto generated code
//don't change things here, it will be overwritten

import Ember from 'ember';
import SiteuserPK from '../models/sitesecurity-entitybase-siteuserpk';
import Siteuser from '../models/sitesecurity-entity-siteuser';

export default Ember.Service.extend({
    PKtoJSON: function(siteuserPK) {
        var jsonobj = null;
        if(siteuserPK!=null) {
            var jsonobj = {
                username: siteuserPK.username
            };
	    }
	    return jsonobj;
	},
	PKtoJSONstring: function(siteuserPK) {
            return JSON.stringify(this.PKtoJSON(siteuserPK));
	},
	toJSON: function(siteuser) {
	    var jsonobj = {
	        PK: this.PKtoJSON(siteuser.siteuserPK)
	    };
	    jsonobj.userpassword = siteuser.userpassword;
	    return jsonobj;
		},
	toJSONstring: function(siteuser) {
	    return JSON.stringify(this.toJSON(siteuser));
	},
	PKfromJSON: function(siteuserPK, jsonobj) {
	    if(jsonobj!=null) {
	        siteuserPK.set("username", jsonobj.username);
	    }
	},
	fromJSON: function(model, jsonobj) {
	    var siteuserPK = SiteuserPK.create();
	    this.PKfromJSON(siteuserPK, jsonobj.PK);
	    model.set("siteuserPK", siteuserPK);
	    model.set("userpassword", jsonobj.userpassword);
	}
});
