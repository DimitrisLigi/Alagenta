package models

data class Client(val clientID: Int,
                  var address: String,
                  var area: String,
                  var name: String,
                  var ringName: String, var phone: Int)
