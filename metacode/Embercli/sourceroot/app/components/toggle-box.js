import Ember from 'ember';

export default Ember.Component.extend({
    buttonlist: null,
    toggleboxdata: null,
    buttonselected: null,
    previousselected: null,
    toggleboxdataidchanged: Ember.observer('toggleboxdata.id', function() {
        //set all cssclasses to notselected
        if(this.toggleboxdata!=null) {
        	var l = this.buttonlist.length;
        	var button;
        	for(var i=0; i<l; i++) {
        		button = this.buttonlist[i];
        		if(button.id == this.toggleboxdata.id) {
        			this.buttonselected = button;
        		} else {
                    button.set("cssclass", "buttonnotselected");
                }
        	}
            if(this.buttonselected!=null) {
                this.buttonselected.set("cssclass", "buttonselected");
            }
            this.previousselected = this.buttonselected;
        }
    }).on('init'),
    actions: {
        select: function(button) {
            if(this.buttonselected!=null) {
                this.buttonselected.set("cssclass", "buttonnotselected");
            }
            this.set("buttonselected", button);
            this.previousselected = this.buttonselected;
            this.buttonselected.set("cssclass", "buttonselected");
            this.sendAction("toggleboxchanged", this.buttonselected);
        }
    }
});
