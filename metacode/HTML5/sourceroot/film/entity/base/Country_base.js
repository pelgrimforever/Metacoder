function :Table:_base() {
    var :table:PK;
:repeatforeignkeys:
:notpk:
    var :uniquename:PK;
:notpk:
:repeatforeignkeys:
:repeatfields:
    var :columnjavaname:;
:repeatfields:

    this.get:Table:PK = function() {
        return :table:PK;
    };
    
    this.set:Table:PK = function(_:table:PK) {
        :table:PK = _:table:PK;
    };
    
:repeatfields:
    this.get:Column: = function() {
        return :columnjavaname:;
    }

    this.set:Column: = function(_:columnjavaname:) {
        :columnjavaname: = _:columnjavaname:;
    }

:repeatfields:
:repeatforeignkeys:
:notpk:
    this.get:Uniquename:PK = function() {
        return :uniquename:PK;
    }

    this.set:Uniquename:PK = function(_:uniquename:PK) {
        :uniquename:PK = _:uniquename:PK;
    }

:notpk:
:repeatforeignkeys:
};

function :Table:PK(:repeatpkfields:_:columnjavaname::,::repeatpkfields:) {
:repeatforeignkeys:
:inpk:
    var :uniquename:PK = new :Pktable:PK(:repeatforeignkeyfields::foreigncolumn::,::repeatforeignkeyfields:);
:inpk:
:repeatforeignkeys:
:repeatpkfields:
:notfk:
    var :columnjavaname: = _:columnjavaname:;
:notfk:
:repeatpkfields:

:repeatforeignkeys:
:inpk:
    this.get:Uniquename:PK = function() {
        return :uniquename:PK;
    }

    this.set:Uniquename:PK = function(_:pktable:PK) {
        :uniquename:PK = _:pktable:PK;
    }

:inpk:
:repeatforeignkeys:
:repeatpkfields:
:infk:
    this.get:Foreigncolumn: = function() {
        return :uniquename:PK.get:Primarycolumn:();
    }

    this.set:Foreigncolumn: = function(_:foreigncolumn:) {
        :uniquename:PK.set:Primarycolumn:(_:foreigncolumn:);
    }

:infk:
:notfk:
    this.get:Column: = function() {
        return :columnjavaname:;
    }

    this.set:Column: = function(_:columnjavaname:) {
        :columnjavaname: = _:columnjavaname:;
    }

:notfk:
:repeatpkfields:
};
