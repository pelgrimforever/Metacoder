function :Table:handler_base() {
    
    var WEBSERVICE = "/FilmWebservices/webresources/";
    var servicename = "rs:table:";

    this.get:Table: = function(:table:pk, :table:loaded) {
        url = WEBSERVICE + servicename;
        $.ajax({
            type: "POST",
            url: url,
            data: "{\"operation\": {\"type\": 103, \"operation\": 1}, \":table:pk\": " + :Table:PKtoJSON(:table:pk) + "}",
            dataType: "json",
            contentType: "application/json;",
            success: function(data, textStatus){
                var :table: = JSONto:Table:(data);
                :table:loaded(:table:);
            },
            error: function(data){
              alert("error");
            }
        });
    };
};
