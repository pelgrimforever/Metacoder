//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';
:repeatuniqueforeignkeys:
import :Pktable:PK from '../models/:project:-entitybase-:pktable:pk';
:repeatuniqueforeignkeys:

export default Ember.Object.extend({
:repeatforeignkeys:
:inpk:
    :uniquename:PK: null,
:inpk:
:repeatforeignkeys:
:repeatpkfields:
:infk:
    :column:: function() {
:iffieldtype:
:int:
        if(this.:uniquename:PK===null) return 0;
:int: 
:long:
        if(this.:uniquename:PK===null) return '';
:long: 
:double:
        if(this.:uniquename:PK===null) return 0;
:double: 
:float:
        if(this.:uniquename:PK===null) return 0;
:float: 
:boolean:
        if(this.:uniquename:PK===null) return false;
:boolean:
:other:
        if(this.:uniquename:PK===null) return '';
:other:
:iffieldtype:
        else return this.:uniquename:PK.get:Primarycolumn:();
    }.property(':uniquename:PK'),
    get:Foreigncolumn:: function() {
        return this.:uniquename:PK.get:Primarycolumn:();
    },
:infk:
:notfk:
:iffieldtype:
:int:
    :column:: 0,
:int: 
:long:
    :column:: '',
:long: 
:double:
    :column:: 0,
:double: 
:float:
    :column:: 0,
:float: 
:boolean:
    :column:: false,
:boolean:
:other:
    :column:: '',
:other:
:customfieldtype:
    :column:: null,:customfieldtype:
:iffieldtype:
    get:Column:: function() {
        return this.:column:;
    },
:notfk:
:repeatpkfields:
    init: function() {
        this._super();
:repeatforeignkeys:
:inpk:
        this.set(":uniquename:PK", :Pktable:PK.create());
:inpk:
:repeatforeignkeys:
:repeatpkfields:
:notfk:
:iffieldtype:
:int:
        this.set(":column:", 0);
:int: 
:long:
        this.set(":column:", '');
:long: 
:double:
        this.set(":column:", 0);
:double: 
:float:
        this.set(":column:", 0);
:float: 
:boolean:
        this.set(":column:", 0);
:boolean:
:other:
        this.set(":column:", "");
:other:
:customfieldtype:
        this.set(":column:", null);
:customfieldtype:
:iffieldtype:
:notfk:
:repeatpkfields:
    }
});
