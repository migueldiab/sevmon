function showHint(val) {
  alert(val);
  var xmlHttp;  //a variable to store xml http objectfunction showHint(str){if (str.length==0){document.getElementById("txtHint").innerHTML=""return}

  xmlHttp=GetXmlHttpObject()

  if (xmlHttp==null) {
    alert ("Browser does not support HTTP Request");
  } 

  var url="test.php";
  xmlHttp.onreadystatechange=stateChanged;
    //stateChanged function called on changing the state
  xmlHttp.open("GET",url,true);
    //open the url with the GET method
  xmlHttp.send(null); 

  function stateChanged() {
    if (xmlHttp.readyState==4)
      //state 4 means completed state
    {
      document.getElementById("txtHint").innerHTML=xmlHttp.responseText
      //put the response value inside div
    }
  } 

  function GetXmlHttpObject() {
    //function to creat xmlhttp object
    var objXMLHttp=null
    if (window.XMLHttpRequest)
    {
      objXMLHttp=new XMLHttpRequest() //for firefox, opera etc
    }
    else if (window.ActiveXObject)
    {
      objXMLHttp=new ActiveXObject("Microsoft.XMLHTTP") //for IE
    }
    return objXMLHttp
  } 

}