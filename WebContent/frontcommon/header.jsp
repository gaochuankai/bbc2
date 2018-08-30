<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="security" uri="/tld/security.tld" %> --%>

<style type="text/css">
#controlpanel ul li { float:left;} 
/* #controlpanel ul li a { text-align:center; display:block; } */
#controlpanel ul li ul { position:absolute; display:none;} 
/* #controlpanel ul li ul li { float:left;} */
#controlpanel ul li:hover ul{ 
	margin-top:31px;
	display:block; 
	float:left;
	width: 100px;
	z-index: 99999999;
}

#controlpanel ul li:hover ul li a{ 
/* 	background-color: #0; */
}
</style>

<div id="header" class="png_bg">
    <div id="head_wrap" class="container_12">
        <!-- start of logo - you could replace this with an image of your logo -->
        <div id="logo" class="grid_8">
          <a href="${dynamicURL}/front/main">
          	<img alt="" src="${staticURL }/style/front/images/front_logo.png" height="50px">
          </a>
<!-- 		  <h1>SMS<span>安全信息管理</span></h1> -->
        </div>
        <!-- end logo -->
        
        <!-- start control panel -->
    	<div id="controlpanel" class="grid_4 person_menu" >
            <ul>
    			<li><p><strong>你好，${_user_nick_name}</strong></p></li>
                <li>
                	<a href="${dynamicURL}/front/user/setting" class="first">个人设置</a>
                </li>
                <c:if test="${not empty _user_resources }">
               		<li><a href="${dynamicURL}/admin/main">后台管理</a></li>
                </c:if>
                <li><a href="#" class="last" onclick="LogOut();">退出登录</a></li>
            </ul>
        </div>
        <!-- end control panel -->
        
        <!-- start navigation -->
      	<div id="navigation" class=" grid_12">
            <ul>
            </ul>
        </div>
        <!-- end navigation -->
    </div><!-- end headwarp  -->
</div><!-- end header -->


<!-- staqrt subnav -->
<div id="sub_nav" >
	<div id="subnav_wrap" class="container_12">
	    <!-- start sub nav list -->
		<div id="subnav" class=" grid_12">
	        <ul>
	        </ul>
	    </div>
	    <!-- end subnavigation list -->	
	</div>
</div>
<!-- end sub_nav -->

<!-- <div id="sub_nav2" class="container_12"> -->
<!-- 	<!-- staqrt subnav --> 
<!-- 	<div id="subnav2" class="icondock grid_12"> -->
<!-- 		<ul> -->
<!-- 		</ul> -->
<!-- 	</div> -->
<!-- </div> -->
<!-- end sub_nav -->

<script type="text/javascript">
<!--
$(function () {
	if(window.location.pathname.indexOf("/front/main")>0){
	 	$.cookie("oneMenuId",null,{ path:'/sms' }); 
	 	$.cookie('twoMenuId',null,{ path:'/sms' }); 
	}
	///加载一级菜单
	$.ajax({
	    type: "POST",
	    url: "${dynamicURL}/front/main/menu/one",
	    async: false,
	    dataType:"json",
	    success: function (data) {
	        var strHtml = "";
	        var str = "";//一级菜单div集合
	        var oneMenuId=$.cookie("oneMenuId");
	        var twoMenuId=$.cookie("twoMenuId");
	        $.each(data, function (i, item) {
	            i++;
	            if(item.url != ""){
// 	            	var menuOneId = '?menuOneId='+item.id+'';
// 	            	if(item.url.indexOf('?') > -1){
// 	            		menuOneId = '&menuOneId='+item.id+'';
// 	            	}
// 	            	if(oneMenuId==item.id){// 选中的/存cookkie
// 	            		str += "<li><a class='active' href='${dynamicURL}/"+item.url+""+menuOneId+"\' id=\"first_"+i+"\" onmouseover=\"openMenu(" + item.id + ")\"  onclick='setMenuToCookie("+item.id+",0);'>"+item.name+"</a></li>";
// 	            	}else{
// 	            		str += "<li><a href='${dynamicURL}/"+item.url+""+menuOneId+"\' id=\"first_"+i+"\" onmouseover=\"openMenu(" + item.id + ")\"  onclick='setMenuToCookie("+item.id+",0);'>"+item.name+"</a></li>";
// 	            	}
	            	if(oneMenuId==item.id){
	            		str += "<li><a class='active' href='${dynamicURL}/"+item.url+"\' id=\"first_"+i+"\" onmouseover=\"openMenu(" + item.id + ")\"  onclick='setMenuToCookie("+item.id+",0);'>"+item.name+"</a></li>";
	            	}else{
	            		str += "<li><a href='${dynamicURL}/"+item.url+"\' id=\"first_"+i+"\" onmouseover=\"openMenu(" + item.id + ")\"  onclick='setMenuToCookie("+item.id+",0);'>"+item.name+"</a></li>";
	            	}
	            }else{
	            	if(oneMenuId==item.id){
	            		str += "<li><a class='active'  href='#' id=\"first_"+i+"\" onmouseover=\"openMenu(" + item.id + ")\">"+item.name+"</a></li>";
	            	}else{
	            		str += "<li><a  href='#' id=\"first_"+i+"\" onmouseover=\"openMenu(" + item.id + ")\">"+item.name+"</a></li>";
	            	}
	            }
	        });
	        $("#navigation ul").html("");//一级菜单加载
	        $("#navigation ul").append(str);//一级菜单加载
	        if(twoMenuId>0){
	        	openMenu(oneMenuId);
	        }
	    }
	});
	$("#sub_nav").hover(
		function(){
		},
		function(){
	
		}
	);
});
//二级菜单
function openMenu(id) {
	var twoMenuId=$.cookie("twoMenuId");
	//ajax加载二级菜单
	$.post('${dynamicURL}/front/main/menu/two',{parentId:id},function(data, status){
		var second = "";
		$.each(data, function (i, item) {
			i++;
			if(item.url){
				if(item.id==twoMenuId){
					second +="<li><a class='active' href='${dynamicURL}/"+item.url+"\' id=\"second_"+i+"\" onclick='setMenuToCookie("+id+","+item.id+");'>"+item.name+"</a></li>";
				}else{
					second +="<li><a  href='${dynamicURL}/"+item.url+"\' id=\"second_"+i+"\" onclick='setMenuToCookie("+id+","+item.id+");'>"+item.name+"</a></li>";
				}
			}else{
				if(item.id==twoMenuId){
					second +="<li><a class='active' href='#' id=\"second_"+i+"\">"+item.name+"</a></li>";
				}else{
					second +="<li><a href='#' id=\"second_"+i+"\">"+item.name+"</a></li>";
				}
			}
		});
	    $("#subnav ul").html("");//二级菜单加载
        $("#subnav ul").append(second);//二级菜单加载
		$("#subnav").fadeIn();
	},'json');
}
function LogOut() {
	layer.confirm('确实退出登录吗?', {
		  btn: ['是','否'] //按钮
		}, function(){
		  $.ajax({
                type: "POST", //提交数据的类型 分为POST和GET
                async: false,
                url: "${dynamicURL}/login/logout",  //提交url 注意url必须小写
                data: {},
                success: function (data) {
                 	$.cookie("oneMenuId",null,{ path:'/sms' }); 
                    $.cookie('twoMenuId',null,{ path:'/sms' }); 
                    window.location.href = '${dynamicURL}/login/userLogin';
                }
            });
		}, function(){}
	);
}

function setMenuToCookie(oneMenuId,twoMenuId){
 	$.cookie("oneMenuId",null,{ path:'/sms' }); 
    $.cookie('twoMenuId',null,{ path:'/sms' }); 
    var date = new Date();
    date.setTime(date.getTime()+5*60*1000);
    if(oneMenuId && oneMenuId!=0){
    	$.cookie('oneMenuId', oneMenuId,{ expires:date,path:'/sms' });
    }
    if(twoMenuId && twoMenuId!=0){
    	$.cookie('twoMenuId', twoMenuId,{ expires:date,path:'/sms' });
    }
}

//三级菜单
// function openMenuThird(id) {
// 	//ajax加载二级菜单
// 	$.post('${dynamicURL}/main/menu/two',{parentId:id},function(data, status){
// 		var third = "";
// 		$.each(data, function (i, item) {
// 			i++;
// 			third +="<li><a href=\"${dynamicURL}/"+item.url+"\"><i class=\"fa "+item.icon+"\" style=\"font-size:25px;\"></i><p>"+item.name+"</p></a></li>"
// 		})
//         $("#subnav2 ul").html("");//三级菜单加载
//         $("#subnav2 ul").append(third);//三级菜单加载
//         $("#subnav2").fadeIn();
// 	},'json');
// }
//-->

// 三级菜单
function searchThreeMenu(){
	$.ajax({
		url : "${dynamicURL}/front/main/searchThreeMenu",
		data : {
			menuTwoId : menuTwoId,
			menuThreeId : menuThreeId
		},
		dataType : "json",
		success : function(response) {
			var threeMenu = '';
			$.each(response.thirdMenuEntities, function(index,item){
				threeMenu += '<ul class="navContent" style="display:block;">';
				if(item.menuActive == 1){
					threeMenu += '	<li><a href="${dynamicURL}/'+item.url+'" class="active">'+item.name+'</a></li>';
				}else{
					threeMenu += '	<li><a href="${dynamicURL}/'+item.url+'">'+item.name+'</a></li>';
				}
				threeMenu += '</ul>';
			});
			$('.subNavBox').html(threeMenu);
		}
	});
}
</script>