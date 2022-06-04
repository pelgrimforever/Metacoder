//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Jan 30, 2019, 18:10:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor(private http: HttpClient) { }

  uploadFile(url: string, formData: FormData): Observable<HttpEvent<any>> {

    let params = new HttpParams();

    const options = {
      params: params,
      reportProgress: true,
    };

    const req = new HttpRequest('POST', url, formData, options);
    return this.http.request(req);
  }
}
