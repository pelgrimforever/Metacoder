//Metacoder: NO AUTHOMATIC UPDATE
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Ember from 'ember';
import Service:Table:Base from '../services/:project:-:table:-base';
import I:Table:Operation from '../models/:project:-entity-:table:-operation';
import :Table: from '../models/:project:-entity-:table:';

export default Service:Table:Base.extend({
	:Table:JSON: Ember.inject.service(':project:-:table:-json'),
});
