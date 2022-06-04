//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 18, 2015, 12:54:01 PM
    Generated on 29.3.2016 11:1
    Author     : Franky Laseure
*/

export default function casttoTime(timestring) {
    var parts = timestring.split(":");
    var i;
    var milliseconds = 0;
    for(i=0; i<parts.length; i++) {
        milliseconds *= 60;
        if(parts[i].length>0 && !isNaN(parts[i])) {
            parts[i] = parseInt(parts[i]); 
            milliseconds += parts[i];
        }
    }
    for(i=parts.length; i<3; i++) {
        milliseconds *= 60;
    }
    milliseconds *= 1000;
    var time = new Date(milliseconds);
    return time;
}
