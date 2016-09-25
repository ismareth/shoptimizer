var express = require('express');
var fs = require('fs'),
    PNG = require('pngjs').PNG;
    var zeros = require("zeros");
    var savePixels = require("save-pixels");


var router = express.Router();

router.get('/', function(request, response){

    response.send("hello");

});

router.get('/img', function(request, response){

var wstream = fs.createWriteStream('myBinaryFile.png');

//Create an image
var x = zeros([64, 64]);
x.set(16, 16, 255);
x.set(17, 17, 255);
x.set(18, 18, 255);

//Save to a file
savePixels(x, "png").pipe(wstream);

//console.log(s);

});


module.exports = router;
