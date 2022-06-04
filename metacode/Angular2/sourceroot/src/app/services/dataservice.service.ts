//Change function definitions here, NOT in dataservice.service.ts
//Only changes made between the //Custom code tags will be kept, the rest is overwritten in the next code generator run.
/* 
    Created on : Jan 23, 2019, 08:59:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import { Injectable, Output, EventEmitter } from '@angular/core';

import { DataservicebaseService } from './dataservicebase.service';

:repeattables:
import { :Table:pk } from './:project:/data/:table:base';
:repeattables:
:repeattablesviews:
import { :Table: } from './:project:/data/:table:';
import { :Table:Service } from './:project:/server/:table:.service';
:repeattablesviews:

@Injectable({
  providedIn: 'root'
})
export class DataserviceService extends DataservicebaseService {

  constructor(
:repeattablesviews:
      protected :table:Service: :Table:Service:,:
:repeattablesviews:
    ) { 
	super(
:repeattablesviews:
        :table:Service:,:
:repeattablesviews:
    );
//Custom code, do not change this line
//add here custom operations
	this.tablelist = [ ];
	this.loadAlltables();
//Custom code, do not change this line   
  }

//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
}
