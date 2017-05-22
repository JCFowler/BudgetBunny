/*************************** Generic JS ***********************************/

      
/*
 * Send an ajax call.
 * 
 * @param data - Intended to be a Json object.
 * @param url - url for ajax call;
 */
function ajaxCall(data, destUrl)
{
	$.ajax({
	    type:"POST",
	    cache:false,
	    url: destUrl,
	    data: data,    // multiple data sent using ajax
	    success: function (html) {
	    	
	      //TODO: finish ajax;
//	    	alert("Ajax Success: \n\t" + html);
	    }
	  });
}

/*
 * Returns the calling functions name.
 */
function getFunctionName() {
    var re = /function (.*?)\(/
    var s = getFunctionName.caller.toString();
    var m = re.exec( s )
    return m[1];
}
