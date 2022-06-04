//Auto generated code
//don't change things here; it will be overwritten
//Super class for database services
/* 
    Created on : Nov 20; 2018; 12:17:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import axios from 'axios';

class :Project:service {
  static test = false;
  static cors = 'http://localhost:8090/';
  static serverurl = 'http://localhost:8080';
  static WEBSERVICE = "/:Project:Webservices/webresources/";
  static SERVLETWEBSERVICE = "/:Project:Webservices/";

  //OPERATIONS TYPES
  static VAR_OPERATIONTYPE = "OPERATIONTYPE";
  static VAR_OPERATION = "OPERATION";

  static OPERATIONTYPE_INSERT = 100;
  static OPERATIONTYPE_UPDATE = 101;
  static OPERATIONTYPE_DELETE = 102;
  static OPERATIONTYPE_SELECT = 103;
  static OPERATIONTYPE_BACKUP = 104;

  static OPERATIONTYPE_SECUREINSERT = 105;
  static OPERATIONTYPE_SECUREUPDATE = 106;
  static OPERATIONTYPE_SECUREDELETE = 107;
  static OPERATIONTYPE_SECURESELECT = 108;
  static OPERATIONTYPE_SECUREBACKUP = 109;
    
  static OPERATIONTYPE_AUTHENTICATE = -1;

  //SELECT OPERATIONS
  static SELECT_COUNT = 0;
  static SELECT_ALL = 1;
  static SELECT_SEARCH = 3;
  static SELECT_SEARCHCOUNT = 4;

  static post = async (restservice, data) => {
    //const istest = props != undefined && props.test != undefined ? props.test : false;
    const urlprefix = :Project:service.test ? :Project:service.cors : '';
    let responsedata = null;
    try {
      const resp = await axios.post(urlprefix + :Project:service.serverurl + :Project:service.WEBSERVICE + restservice, data);
      responsedata = resp.data;
    } catch(err) {
      console.log(err);
    }
    return responsedata;
  }

  static postServlet = async (restservice, data) => {
    const urlprefix = :Project:service.test ? :Project:service.cors : '';
    let responsedata = null;
    try {
      const resp = await axios({
      	method: 'post',
      	url: urlprefix + :Project:service.serverurl + :Project:service.SERVLETWEBSERVICE + restservice,
      	data: data,
      	responseType: 'arraybuffer'
      });
      responsedata = resp.data;
    } catch(err) {
      console.log(err);
    }
    return responsedata;
  }

  static extractDataCount = (res: Response): number => {
    let count: number = 0;
    //check if res.status exists, this means no data has returned
    if (typeof res.status == 'undefined') {
      const jsoncount = JSON.parse(JSON.stringify(res))
      count = jsoncount.recordcount;
    } else {
      console.log("Webservice return status: " + res.status);
    }
    return count;
  }

  static extractImage = (arrayBuffer) => {
    let u8 = new Uint8Array(arrayBuffer);
    let b64encoded = btoa([].reduce.call(new Uint8Array(arrayBuffer),function(p,c){return p+String.fromCharCode(c)},''));
    let mimetype="image/jpeg"
    return "data:"+mimetype+";base64,"+b64encoded;
  }
}

export default :Project:service;
