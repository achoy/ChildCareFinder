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
        return this.http.get('/api/providers')
            .toPromise()
            .then(response => {
                var json = response.json()
                return json.map(jsonCaretaker => {
                    return this.convert(jsonCaretaker)
                })
            })
    }

    getSingle(id: string): Promise<Caretaker> {
        return this.http.get(`/api/provider/${id}`)
            .toPromise()
            .then(response => {
                let json = response.json()
                return this.convert(json)
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