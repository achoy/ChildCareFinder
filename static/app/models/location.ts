export class Location {
    lat: number
    lng: number
    addressLine1: string
    addressLine2: string
    addressLine3: string
    city: string
    state: string
    zipCode: string
    county: string
    
    constructor(
        lat: number = 0,
        lng: number = 0,
        addressLine1: string = 'Not provided',
        addressLine2: string = '',
        addressLine3: string = '',
        city: string = 'Not Provided',
        state: string = 'MN',
        zipCode: string = '55404',
        county: string = "Hennepin"
    ) {
        this.lat = lat
        this.lng = lng
        this.addressLine1 = addressLine1
        this.addressLine2 = addressLine2
        this.addressLine3 = addressLine3
        this.city = city
        this.state = state
        this.zipCode = zipCode
        this.county = county
    }
}