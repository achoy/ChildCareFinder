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
        return new Promise<Caretaker[]>((resolve, reject) => {
        // })
        // return this.http.get('/mock/route')
        //     .toPromise()
        //     .then(response => {
        //         var json = response.json()
            var json = JSON.parse(Mocks.providers)
                var mapped = json.map(jsonCaretaker => {
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
                        this.convertStatus(jsonCaretaker.licenseStatus),
                        jsonCaretaker.licenseStatus,
                        jsonCaretaker.licenseType,
                        
                    )
                    return new Caretaker(
                        jsonCaretaker._id, loc, name, jsonCaretaker.phone, license
                    )
                })
                resolve(mapped)
            })
    }

    private convertStatus(statusString: string): LicenseStatus {
        switch (statusString) {
            case "Active":
                return LicenseStatus.active
            case "Conditional":
                return LicenseStatus.conditional
            case "Closed":
                return LicenseStatus.closed
            case "Revoked":
                return LicenseStatus.revoked
            case "Denied":
                return LicenseStatus.denied
            default:
                if (statusString.includes("May Operate")) {
                    return LicenseStatus.revokedMayOperate
                }
                if (statusString.includes("Under Appeal")) {
                    return LicenseStatus.revokedUnderAppeal
                }
                return LicenseStatus.unknown
        }
    }
    
}