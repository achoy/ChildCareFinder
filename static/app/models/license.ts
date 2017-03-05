import { LicenseStatus } from './licenseStatus'

export class License {
    id: number
    status: LicenseStatus
    type: string
    holder: string
    body: string

    constructor(
        id: number = -1,
        status: LicenseStatus = LicenseStatus.unknown,
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