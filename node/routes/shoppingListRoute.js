var express=require('express');
var request=require('request');
var bodyParser = require('body-parser');

var router=express.Router();
router.use(bodyParser.json());

router.post('/', function(req, res, next) {
	var body=req.body;
    var shoppingList=body.shoppingList;
    var storeID=body.storeID;

    var apiKey= '48f7d403';

for(var i = 0; i < shoppingList.length; i++) {
    var obj = shoppingList[i];
    var aisleNum=[];
    var cheapestAisle=[];
    var lookup={};
    var url='http://www.supermarketapi.com/api.asmx/COMMERCIAL_SearchForItem?APIKEY='+apiKey+'&StoreId='+storeID+'&ItemName='+obj;

        request(url, function (error, response, body) {
        	   
        	  var parseString = require('xml2js').parseString;

              parseString(response.body, function (err, result) {

                 var productArray=result.ArrayOfProduct_Commercial.Product_Commercial;
                 for(var j=0;j<productArray.length;j++)
                 {
                 	var aisle=productArray[j].AisleNumber;
                 	if (!(aisle in lookup)) {
                     lookup[aisle] = 1;
                       aisleNum.push(aisle);
                          }

                 }
              });

             console.log(aisleNum);
             aisleNum=[];
          });

 }


});
module.exports=router;
