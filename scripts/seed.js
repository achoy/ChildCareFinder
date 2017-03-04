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
    "licenseType",
    "restriction",
    "services",
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
    var ret = {}
    var columns = text.split(',')
    
    columns.forEach((col, i) => {
        var value
        if (i == 16) {
            ret[fieldMap[i]] = format(col)
        } else if (i > 19) {
            
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