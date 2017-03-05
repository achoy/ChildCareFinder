import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'
import { MaterialModule } from '@angular/material'
import { HttpModule } from '@angular/http'
import { FormsModule } from '@angular/forms'

import { AppComponent } from './component'
import { Router } from './router/module'

import { Dashboard } from './dashboard/component'

import { Caretakers } from './caretakers/service'

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        MaterialModule.forRoot(),
        FormsModule,
        Router
    ],
    providers: [
        Caretakers
    ],
    declarations: [
        AppComponent,
        Dashboard
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule {}