/* 
//Metacoder: NO AUTHOMATIC UPDATE

    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/
import Ember from 'ember';
import AutoComplete from "../components/autocomplete-list";

export default AutoComplete.extend({
	valueProperty: "code",
	suggestions: function() {
		var inputVal = this.get("inputVal") || "";
		var newarray = [];
		this.get("options").forEach(function(item) {
			var option = item.code.toLowerCase();
			var inputoption = inputVal.toLowerCase();
			if(option.toLowerCase().lastIndexOf(inputoption, 0) === 0) {
				newarray.push(item);
			}
		});
		return newarray;
	}.property("inputVal", "options.[]"),
	optionsToMatch: function() {
	      var caseInsensitiveOptions = Ember.A([]);
	      this.get("options").forEach(function(item) {
	          var option = item.code;
	          caseInsensitiveOptions.push(option.toLowerCase());
	      });
	      return caseInsensitiveOptions;
	}.property("options.[]"),
  	determineSuggestions: function(options, input) {
	  return [];
	},
    actions: {
	    itemSelected: function(item) {
	    	alert("itemSelected " + item.code);
	    }
    }
});
