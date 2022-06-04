//Auto generated code
//don't change things here, it will be overwritten
//extend classes in dataservice.service.ts for additional functionality
/* 
    Created on : Jan 23, 2019, 08:59:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import { Injectable, Input, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';

import { Siteuser } from '../services/sitesecurity/data/siteuser';

:repeattablesviews:
import { :Table: } from './:project:/data/:table:';
import { :Table:Service } from './:project:/server/:table:.service';
:repeattablesviews:

export abstract class DataservicebaseService {

  @Output() siteuserchanged: EventEmitter<Siteuser> = new EventEmitter();
  siteuser: Siteuser = null;

  tablelist: string[] = [];

:repeattablesviews:
//:table:
  @Output() :table:allchanged: EventEmitter<:Table:[]> = new EventEmitter();
  protected :table:all: :Table:[] = [];
  @Output() :table:listchanged: EventEmitter<:Table:[]> = new EventEmitter();
  protected :table:list: :Table:[] = [];
  @Output() :table:changed: EventEmitter<:Table:> = new EventEmitter();
  protected :table:: :Table: = null;
  @Output() :table:inserted: EventEmitter<:Table:> = new EventEmitter();
  @Output() :table:saved: EventEmitter<:Table:> = new EventEmitter();
  @Output() :table:deleted: EventEmitter<:Table:> = new EventEmitter();
:repeattablesviews:

  constructor(
:repeattablesviews:
      protected :table:Service: :Table:Service:,:
:repeattablesviews:
    ) { 
  }

  public setSiteuser(siteuser: Siteuser) {
    this.siteuser = siteuser;
  	this.siteuserchanged.emit(siteuser);
  }

  protected loadAlltables() {
    let l = this.tablelist.length;
    for(let i=0; i<l; i++) {
      let tablename = this.tablelist[i];
      this.getAll(tablename);
    }
  };
  
  public getAll(tablename) {
    if(this[tablename + 'Service'] === undefined) {
      console.log("ALERT: " + tablename + "Service is not known.")
    } else {
  	  this[tablename + 'Service'].getAll(this.siteuser).subscribe(list => {
  	    this[tablename + 'all'] = list;
  	    this[tablename + 'allchanged'].emit(list);
  	  });    	
  	}
  };

:repeattablesviews:
  public get:Table:all(): :Table:[] { return this.:table:all; }
  public set:Table:all(list: :Table:[]) { this.:table:all = list; this.:table:allchanged.emit(list); }
  public get:Table:list(): :Table:[] { return this.:table:list; }
  public set:Table:list(list: :Table:[]) { this.:table:list = list; this.:table:listchanged.emit(list); }
  public get:Table:(): :Table: { return this.:table:; }
  public set:Table:(:table:: :Table:) { this.:table: = :table:; this.:table:changed.emit(:table:); }
:repeattablesviews:

  public getOne(tablename, pk) {
  	  this[tablename + 'Service'].getOne(this.siteuser, pk).subscribe(record => {
  	    this[tablename] = record;
  	    this[tablename + "changed"].emit(record);
  	  });
  }

  public insert(record) {
    if(this.siteuser!=null) {
  	  this[record.tablename + 'Service'].insert(this.siteuser, record).subscribe(dummy => {
  	    this[record.tablename + "inserted"].emit(record);
  	  });
  	} 
  }

  public save(record) {
    if(this.siteuser!=null) {
  	  this[record.tablename + 'Service'].save(this.siteuser, record).subscribe(dummy => {
  	    this[record.tablename + "saved"].emit(record);
  	  });
  	}
  }

  public del(record) {
    if(this.siteuser!=null) {
  	  this[record.tablename + 'Service'].del(this.siteuser, record).subscribe(dummy => {
  	    this[record.tablename + "deleted"].emit(record);
  	  });
  	}
  }
}
