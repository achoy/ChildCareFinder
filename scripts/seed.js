const fs = require('fs')
const mongo = require('mongojs')
var pw = process.argv[2]

const mongoConnString = `mongodb://childcareApp:${pw}@ds117830.mlab.com:17830/childcare`
console.log(mongoConnString)
if (!pw) {
    console.error("No password in commandline args")
    process.exit(1)
}
const db = mongo(mongoConnString, ['providers'])
const fieldMap = [
    "lng",
    "lat",
    "licenseNumber",
    "licenseType",
    "name",
    "address1",
    "address2",
    "address3",
    "city",
    "state",
    "zip", //10
    "county",
    "phone",
    "licenseStatus",
    "licenseHolder",
    "capacity",
    "licenseType", //16
    "restriction",
    "services", //18
    "licensingBody",
    "initialEffectiveDate", //20
    "currentEffectiveDate",
    "expiration"
]

function seed() {
    var providersString = fs.readFileSync('providers.csv', 'utf8')
    var providers = []
    var lines = providersString.split('\n')
    console.log('lines: ' + lines.length)
    lines.forEach((line, index) => {
        providers.push(parseCSVText(line))
    })
    console.log('Saving parsed providers ' + providers.length)
    var obj = {
        testFromCode: "testValue"
    }
    var bulk = db.providers.initializeOrderedBulkOp()
    var second = db.providers.initializeOrderedBulkOp()
    for (var i = 0; i < providers.length; i++) {
        if (i > 999) {
            second.insert(providers[i])
        } else {
            bulk.insert(providers[i])
        }

    }
    bulk.execute((err1, result1) => {
        if (err1) return console.error(err2)
        console.log(`First:`)
        console.log(result1)
        second.execute((err2, result2) => {
            if (err2) return console.log(err2)
            console.log(`Second:`)
            console.log(result2)
            process.exit()
        })
        
    })
}

function parseCSVText(text) {
    var ret = {
        loc: []
    }
    var columns = text.split(',')
    
    columns.forEach((col, i) => {
        if (i == 0) {
            ret.loc.push(Number.parseFloat(col))
        } else if (i == 1) {
            ret.loc.push(Number.parseFloat(col))
        } else if (i == 15) {
            ret[fieldMap[i]] = Number.parseInt(col)
        } else if (i >= 20) {
            ret[fieldMap[i]] = new Date(col)
        } else if (i < fieldMap.length) {
            ret[fieldMap[i]] = format(col)
        }
    })
    return ret
}

function format(text) {
    return text.replace('"', '').replace("  ", ", ").trim()
}

seed()