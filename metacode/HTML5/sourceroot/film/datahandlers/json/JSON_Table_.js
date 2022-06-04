:Table:PKtoJSON = function(:table:PK) {
    var jsonobj = {
:repeatpkfields:
        :column:: :table:PK.get:Column:():,:
:repeatpkfields:
    };
    return JSON.stringify(jsonobj);
};

:Table:toJSON = function(:table:) {
    var jsonobj = {
        PK: :Table:PKtoJSON(:table:.get:Table:PK())
    };
:repeatforeignkeys:
:notpk:
    if(:table:.get:Uniquename:PK()!=null) {
        jsonobj.:uniquename:PK = :Pktable:toJSON(:table:.get:Uniquename:PK());
    }
:notpk:
:repeatforeignkeys:
:repeatfields:
    jsonobj.:column: = :table:.get:Column:();
:repeatfields:
    return JSON.stringify(jsonobj);
};

JSONto:Table:PK = function(jsonobj) {
    :table:PK = new :Table:PK();
:repeatpkfields:
    :table:PK.set:Column:(jsonobj.:column:);
:repeatpkfields:
    return :table:PK;
};

JSONto:Table: = function(jsonobj) {
    :table: = new :Table:();
    :table:.set:Table:PK(JSONto:Table:PK(jsonobj.PK));
:repeatforeignkeys:
:notpk:
    :table:.set:Uniquename:PK(JSONto:Pktable:PK(jsonobj.:uniquename:PK));
:notpk:
:repeatforeignkeys:
:repeatfields:
    :table:.set:Column:(jsonobj.:column:);
:repeatfields:
    return :table:;
};

