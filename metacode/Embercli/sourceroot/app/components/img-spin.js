import Ember from 'ember';

export default Ember.Component.extend({
	imgpath: "",
	imgsrc: "",
	width: 200,
	height: 200,
	imgid: "",
	spinnerid: "",

    setup: Ember.on('init', function() {
    	this.set("spinnerid", this.imgid + "spinner");
    	Ember.run.schedule("afterRender",this,function() {
    		this.imgpathchanged();
    	});
    }),

	imgpathchanged: Ember.observer("imgpath", function() {
		this.setidVisible(this.spinnerid);
		this.setidHidden(this.imgid);
		//var tmpImg = new Image() ;
        //tmpImg.onload = this.imgloaded();
        //tmpImg.src = this.imgpath;
		$("#" + this.imgid).one("load", this.imgloaded()).attr("src", this.imgpath);
		//$("#" + this.imgid).onload = this.imgloaded();
	}),

	imgloaded: function() {
		//$("#" + this.imgid).src = this.imgpath;
		this.setidVisible(this.imgid);
		this.setidHidden(this.spinnerid);
	},

    setidVisible(imgid) {
    	document.getElementById(imgid).classList.remove('ishidden');
        document.getElementById(imgid).classList.add('isvisible');
    },

    setidHidden(imgid) {
    	document.getElementById(imgid).classList.remove('isvisible');
        document.getElementById(imgid).classList.add('ishidden');
    }
});
