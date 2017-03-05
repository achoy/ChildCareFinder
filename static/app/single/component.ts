import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Params, Router } from '@angular/router'

import { Caretakers } from '../caretakers/service'
import { Caretaker } from '../models/caretaker'

@Component({
    selector: "<single></single>",
    templateUrl: "app/single/template.html",
    styleUrls: ['app/single/style.css']
})
export class Single implements OnInit {

    caretaker: Caretaker = undefined

    constructor(private caretakers: Caretakers,
                private route: ActivatedRoute,
                private router: Router) {}

    ngOnInit() {
        var id
        this.route.params.forEach(param => {
            if (param['id']) {
                id = param['id']
            }
        })
        this.caretakers.getSingle(id)
            .then(caretaker => {
                this.caretaker = caretaker
            })
    }
}