export class License {
    id: number
    status: string
    type: string
    holder: string
    body: string

    constructor(
        id: number = -1,
        status: string = "Unknown",
        licenseType: string = "not provided",
        licenseHolder: string = "not provided",
        licensingBody: string = "not provided"
    ) {
        this.id = id
        this.status = status
        this.type = licenseType
        this.holder = licenseHolder
        this.body = licensingBody
    }
}