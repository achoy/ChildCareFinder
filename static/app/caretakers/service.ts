import { Injectable } from '@angular/core'
import { Http } from '@angular/http'
import { Observable } from 'rxjs'
import 'rxjs/add/operator/toPromise'

import { Location } from '../models/location'
import { LicenseStatus } from '../models/licenseStatus'
import { License } from '../models/license'
import { Caretaker } from '../models/caretaker'

import { Mocks } from './mocks'


@Injectable()
export class Caretakers {


    constructor(private http: Http) {}

    getAll(): Promise<Caretaker[]> {
        return this.http.get('http://childcarefinder.choyware.com:5000/api/providers')
            .toPromise()
            .then(response => {
                var json = response.json()
                return json.map(jsonCaretaker => {
                    return this.convert(jsonCaretaker)
                })
            }).catch(error => {
                return this.convert(Mocks.providersMocks)
            })
    }

    getSingle(id: string): Promise<Caretaker> {
        return this.http.get(`http://childcarefinder.choyware.com:5000/api/providers/${id}`)
            .toPromise()
            .then(response => {
                let json = response.json()
                return this.convert(json)
            }).catch(error => {
                var list = Mocks.providersMocks.filter(entry => {
                    if (entry.licenseNumber == id) {
                        return entry
                    }
                })
                if (list.length > 0) {
                    return this.convert(list[0])
                } else {
                    return error
                }
            })
    }

    private convert(jsonCaretaker: any): Caretaker {
        var name = jsonCaretaker.name
                    var loc = new Location(
                    jsonCaretaker.lat, 
                    jsonCaretaker.lng, 
                    jsonCaretaker.address1,
                    jsonCaretaker.address2,
                    jsonCaretaker.address3,
                    jsonCaretaker.city,
                    jsonCaretaker.state,
                    jsonCaretaker.zip,
                    jsonCaretaker.county
                    )
                    var license = new License(
                        jsonCaretaker.licenseNumber,
                        jsonCaretaker.licenseStatus,
                        jsonCaretaker.licenseStatus,
                        jsonCaretaker.licenseType,
                        
                    )
                    return new Caretaker(
                        jsonCaretaker._id, loc, name, jsonCaretaker.phone, license
                    )
    }
}