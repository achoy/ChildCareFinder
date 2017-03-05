import { Component, OnInit } from '@angular/core'

import { Caretakers } from '../caretakers/service'
import { Caretaker } from '../models/caretaker'

@Component({
    selector: '<dashboard>',
    templateUrl: 'app/dashboard/template.html',
    styleUrls: ['app/dashboard/style.css']
})
export class Dashboard implements OnInit {

    resultSet: Caretaker[]
    searchTerm: string = ''

    constructor(private caretakers: Caretakers) {}

    ngOnInit(): void {

    }

    findProviders() {
        console.log(this.searchTerm)
        this.caretakers.getAll()
            .then(cts => {
                this.resultSet = cts
            })
    }

    
}