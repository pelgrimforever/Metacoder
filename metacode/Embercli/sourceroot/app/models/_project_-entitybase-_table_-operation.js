//Auto generated code
//don't change things here, it will be overwritten
//extend classes in :project:-entity-:table:-operation.js for table class definitions
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';
import IDataServlet from '../models/:project:-entitybase-idataservlet';

export default IDataServlet.extend({
    //SELECT OPERATIONS
    SELECT_COUNT: 0,
    SELECT_ALL: 1,
    SELECT_:TABLE:: 2,
    SELECT_SEARCH: 3,
    SELECT_SEARCHCOUNT: 4,
    SELECT_Siteusergroup: 100 + 0,
:repeatforeignkeys:
    SELECT_:Uniquename:: 100 + :counter:,
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    SELECT_:Uniquename:: 100 + :counter:,
:ifforeignkey:
:repeatexternalforeignkeys:

    //UPDATE OPERATIONS
    UPDATE_:TABLE:: 10,

    //INSERT OPERATIONS
    INSERT_:TABLE:: 20,

    //DELETE OPERATIONS
:repeatforeignkeys:
    DELETE_:Uniquename:: 100 + :counter:,
:repeatforeignkeys:
    DELETE_:TABLE:: 30  
});
