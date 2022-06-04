//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 20, 2018, 10:39:01 AM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import :Project:service from '../../../../:project:service.js';
import { :Table:pk } from '../../../../../data/:project:/table/super/:table:super.js';
import :Table: from '../../../../../data/:project:/table/:table:.js';
import :Table:Json from '../conversion/:table:json.js';
:repeatuniqueforeignkeys:
import { :Pktable:pk } from '../../../../../data/:project:/table/super/:pktable:super.js';
import :Pktable:Json from '../conversion/:pktable:json.js';
:repeatuniqueforeignkeys:
:repeatuniqueexternalforeignkeys:
import { :Extablename:pk } from '../../../../../data/:project:/table/super/:extablename:super.js';
import :Extablename:Json from '../conversion/:extablename:json.js';
:repeatuniqueexternalforeignkeys:


class Rs:table:super extends :Project:service {	

  static restserviceselect = 'rs:tablename:_select';
  static restserviceinsert = 'rs:tablename:_insert';
  static restserviceupdate = 'rs:tablename:_update';
  static restservicedelete = 'rs:tablename:_delete';

  //SELECT OPERATIONS
  static SELECT_:TABLE: = 2;
  static SELECT_Siteusergroup = 100 + 0;
:repeatforeignkeys:
  static SELECT_:Uniquename: = 100 + :counter:;
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
  static SELECT_:Uniquename: = 100 + :counter:;
:ifforeignkey:
:repeatexternalforeignkeys:

  //UPDATE OPERATIONS
  static UPDATE_:TABLE: = 10;

  //INSERT OPERATIONS
  static INSERT_:TABLE: = 20;

  //DELETE OPERATIONS
:repeatforeignkeys:
  static DELETE_:Uniquename: = 100 + :counter:;
:repeatforeignkeys:
  static DELETE_:TABLE: = 30;

  static extractDataArray = (jsonarray): :Table:[] => {
    let :table:s: [] = [];
    for(let i = 0; i < jsonarray.length; i++) {
      :table:s.push(:Table:Json.fromJSON(jsonarray[i]));
    }
    return :table:s;
  }

  static extractDataObject = (jsonobject): :Table: => {
    return :Table:Json.fromJSON(jsonobject);
  }

  static getcount = async (user) => {
    const postdata = {
      auth: user===null ? null : user.auth,
      operation: super.SELECT_COUNT
    }
    return this.extractDataCount(await super.post(this.restserviceselect, postdata));
  }

  static getall = async (user) => {
    const postdata = {
      auth: user===null ? null : user.auth,
      operation: super.SELECT_ALL
    }
    return this.extractDataArray(await super.post(this.restserviceselect, postdata));
  }

  static getOne = async (user, :table:pk: :Table:pk): :Table: => {
    const postdata = {
      auth: user===null ? null : user.auth,
      operation: this.SELECT_:TABLE:,
      ":tablename:pk": :Table:Json.PKtoJSON(:table:pk)
    }
    return this.extractDataObject(await super.post(this.restserviceselect, postdata));
  }

:repeatforeignkeys:
  static load:Table:s4:uniquename: = async (user, :pktable:pk: :Table:pk): :Table:[] => {
    const postdata = {
      auth: user===null ? null : user.auth,
      operation: this.SELECT_:Uniquename:,
      ":pktable_o:pk": :Pktable:Json.PKtoJSON(:pktable:pk)
    }
    return this.extractDataArray(await super.post(this.restserviceselect, postdata));
  }

:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
  static load:Table:4:uniquename: = async (user, :uniquename:pk: :Extablename:pk): :Table:[] => {
    const postdata = {
      auth: user===null ? null : user.auth,
      operation: this.SELECT_:Uniquename:,
      ":extablename_o:pk": :Extablename:Json.PKtoJSON(:uniquename:pk)
    }
    return this.extractDataArray(await super.post(this.restserviceselect, postdata));
  }

:ifforeignkey:
:repeatexternalforeignkeys:
  static search = async (user, :table:searcher) => {
    const postdata = {
      auth: user===null ? null : user.auth,
      operation: this.SELECT_SEARCH,
      "search": :table:searcher.toJSON()
    }
    return this.extractDataArray(await super.post(this.restserviceselect, postdata));
  }

  static searchcount = async (user, :table:searcher) => {
    const postdata = {
      auth: user===null ? null : user.auth,
      operation: this.SELECT_SEARCHCOUNT,
      "search": :table:searcher.toJSON()
    }
    return this.extractDataCount(await super.post(this.restserviceselect, postdata));
  }

  static insert = async (user, :table:: :Table:) => {
    const postdata = {
      auth: user===null ? null : user.auth,
      operation: this.INSERT_:TABLE:,
      ":tablename:": :Table:Json.toJSON(:table:)
    }
    return await super.post(this.restserviceinsert, postdata);
  }

  static save = async (user, :table:: :Table:) => {
    const postdata = {
      auth: user===null ? null : user.auth,
      operation: this.UPDATE_:TABLE:,
      ":tablename:": :Table:Json.toJSON(:table:)
    }
    return await super.post(this.restserviceupdate, postdata);
  }

  static del = async (user, :table:: :Table:) => {
    const postdata = {
      auth: user===null ? null : user.auth,
      operation: this.DELETE_:TABLE:,
      ":tablename:": :Table:Json.toJSON(:table:)
    }
    return await super.post(this.restservicedelete, postdata);
  }

}

export default Rs:table:super;
