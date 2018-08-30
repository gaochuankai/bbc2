<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="security" uri="/tld/security.tld" %> --%>

<link href="css/mycss.css" type="text/css" rel="stylesheet" />

<!-- meta -->
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width" />

<!-- <title>gck的BBC</title> -->

<script type="text/javascript">
	var headerHost = "${header.Host }";
    var dynamicURL = "${dynamicURL}/";
    var staticURL = "${staticURL}/";
    var waitTime = 400;
    /* $(document).ajaxError(function(event, jqXHR, options, errorMsg){
    	if(jqXHR.responseText=="{'statusCode':301}"){
    		layer.alert('会话超时,请重新登录！', function(){
    				window.parent.location.href="${dynamicURL}/login/userLogin";
    			}
    		);
    	}
    });
    $(function () {
   	 	var errorMsg = '${errorMsg}';
   		if(errorMsg){
   			layer.open({
   				icon: 2,
   				title: '提示',
   				content: errorMsg
   			}); 
   		}
    }); */
</script>