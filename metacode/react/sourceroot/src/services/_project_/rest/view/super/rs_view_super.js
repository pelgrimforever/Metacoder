//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Dec 16, 2018, 18:24:01
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import :Project:service from '../../../../:project:service.js';
import :Table: from '../../../../../data/:project:/view/:table:.js';
import :Table:Json from '../conversion/:table:json.js';

class Rs:table:super extends :Project:service {

	static restserviceselect = 'rs:tablename:_select';
	static restservice = 'rs:tablename:';

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

  static getall = async (user) => {
    const postdata = {
    	auth: user!=null ? user.auth : null,
      operation: super.SELECT_ALL
    }
    return this.extractDataArray(await super.post(this.restserviceselect, postdata));
  }

}

export default Rs:table:super;
