import { Location } from '../models/location'
import { License } from './license'
export class Caretaker {
    _id: string
    location: Location
    name: string
    phone: string
    license: License
    constructor(
        id: string = "-1",
        location: Location = new Location(),
        name: string = "Unknown",
        phone: string = "(612) 555 -5555",
        license: License = new License() 
    ) {
        this._id = id
        this.location = location
        this.name = name
        this.phone = phone
        this.license = license
    }
}