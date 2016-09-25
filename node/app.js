var express = require('express');
var path = require('path');
var mongoose = require('mongoose');

var shoppingListRoute = require('./routes/shoppingListRoute.js');
// var mapRoute = require('./routes/mapRoute.js');
// var billingRoute = require('./routes/billingRoute.js');
var storesRoute = require('./routes/storesRoute.js');

var app = express();

var ipAddress = "localhost:27017";
var dbName = "shoptimizer";
var port = 1399;

//mongoose.connect('mongodb://'+ ipAddress +'/'+ dbName +'');
// app.use('/shoppinglist', shoppingListRoute);
// app.use('/map', mapRoute);
// app.use('/billing', billingRoute);
app.use('/store', storesRoute);
app.use('/shoppingListRoute',shoppingListRoute);

app.listen(port);
console.log("Starting on port " + port);
