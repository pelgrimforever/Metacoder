import Ember from 'ember';
import AutoComplete from "ember-cli-auto-complete/components/auto-complete";
import Autocompletemodel from '../models/autocompletemodel';

export default AutoComplete.extend({
	valueProperty: "code",
	options: [
		Autocompletemodel.create({ code: "00:00:00", text: "00:00:00" }),
		Autocompletemodel.create({ code: "01:00:00", text: "01:00:00" }),
		Autocompletemodel.create({ code: "02:00:00", text: "02:00:00" }),
		Autocompletemodel.create({ code: "03:00:00", text: "03:00:00" }),
		Autocompletemodel.create({ code: "05:00:00", text: "04:00:00" }),
		Autocompletemodel.create({ code: "05:00:00", text: "05:00:00" }),
		Autocompletemodel.create({ code: "06:00:00", text: "06:00:00" }),
		Autocompletemodel.create({ code: "07:00:00", text: "07:00:00" }),
		Autocompletemodel.create({ code: "08:00:00", text: "08:00:00" }),
		Autocompletemodel.create({ code: "09:00:00", text: "09:00:00" }),
		Autocompletemodel.create({ code: "10:00:00", text: "10:00:00" }),
		Autocompletemodel.create({ code: "11:00:00", text: "11:00:00" }),
		Autocompletemodel.create({ code: "12:00:00", text: "12:00:00" }),
		Autocompletemodel.create({ code: "13:00:00", text: "13:00:00" }),
		Autocompletemodel.create({ code: "14:00:00", text: "14:00:00" }),
		Autocompletemodel.create({ code: "15:00:00", text: "15:00:00" }),
		Autocompletemodel.create({ code: "16:00:00", text: "16:00:00" }),
		Autocompletemodel.create({ code: "17:00:00", text: "17:00:00" }),
		Autocompletemodel.create({ code: "18:00:00", text: "18:00:00" }),
		Autocompletemodel.create({ code: "19:00:00", text: "19:00:00" }),
		Autocompletemodel.create({ code: "20:00:00", text: "20:00:00" }),
		Autocompletemodel.create({ code: "21:00:00", text: "21:00:00" }),
		Autocompletemodel.create({ code: "22:00:00", text: "22:00:00" }),
		Autocompletemodel.create({ code: "23:00:00", text: "23:00:00" })
	],
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
    selectItem: function (item) {
		var valueProperty = this.get("valueProperty");
		this.set("selectedFromList", true);
		this.set("selectedValue", item.get(valueProperty));

		this.sendAction('selectItem', item);
    },
    }
});
