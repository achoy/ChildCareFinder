import { Component, OnInit } from '@angular/core'
import { Router } from '@angular/router'


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

    constructor(private caretakers: Caretakers,
                private router: Router) {}

    ngOnInit(): void {

    }

    findProviders() {
        console.log(this.searchTerm)
        this.caretakers.getAll()
            .then(cts => {
                this.resultSet = cts
            })
    }

    goTo(id: string) {
        this.router.navigate(['api', 'providers', id])
    }
    
}