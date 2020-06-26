<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType = "text/html" %>  <!-- database  -->
<%@ page import = "java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width,initial-scale=1.0">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link type="text/css" rel="stylesheet" href="./css/navigator.css">
  <!-- <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script> -->
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/vue.min.js"></script>
  <title>计算机网络工程实验室</title>
</head>
<body style="margin:0">
  <div class="navigator">
    <div class="hello" id="app">
      <nav class="navbar navbar-default" role="navigation">
    	<div class="container-fluid container">
    	<div class="navbar-header">
    		<button type="button" class="navbar-toggle" data-toggle="collapse"
    				data-target="#example-navbar-collapse">
    			<span class="sr-only">切换导航</span>
    			<span class="icon-bar"></span>
    			<span class="icon-bar"></span>
    			<span class="icon-bar"></span>
    		</button>
    		<a class="navbar-brand" href="#">计算机网络工程实验室</a>
    	</div>
    	<div class="collapse navbar-collapse" id="example-navbar-collapse">
    		<ul class="nav navbar-nav">
          <li v-for='item in navigators' :class='{active:item.isActive,dropdown:item.isDropdown}'>
            <a :href="item.link" :class="{'dropdown-toggle':item.isDropdown}" :data-toggle='item.dropdown'>{{item.name}}
              <b :class="{caret:item.isDropdown}"></b>
            </a>
            <ul :class="{'dropdown-menu':item.isDropdown}" v-if='item.isDropdown'>
              <div v-for='e in item.menu'>
                <li ><a :href="e.link">{{e.name}}</a></li>
                <li class="divider"></li>
              </div>
            </ul>
          </li>
    		</ul>
    	</div>
    	</div>
    </nav>
    </div>
  </div>

  <div class="container MainBody" id="MainBody">
  <div class="col-lg-6 LinkPage" id="firpage">
    <h2>指导老师：</h2>
    <p><a href="mailto:434991908@qq.com?subject=network_lab&cc=z11hgq@gmail.com">袁霞</a></p>
    <h2>小组成员：</h2>
    <p><a href="mailto:z11hgq@gmail.com?subject=network_lab&cc=z11hgq@gmail.com">张根齐</a></p></p>
    <p><a href="mailto:867245920@qq.com?subject=network_lab&cc=z11hgq@gmail.com">胡易律</a></p></p>
    <p><a href="mailto:940570020@qq.com?subject=network_lab&cc=z11hgq@gmail.com">张本贵</a></p></p>
    <p><a href="mailto:1044840141@qq.com?subject=network_lab&cc=z11hgq@gmail.com">秦浩翔</a></p></p>
    <p><a href="mailto:847983898@qq.com?subject=network_lab&cc=z11hgq@gmail.com">周维幸</a></p></p>
  </div>
  <div style="width: inherit;display: -webkit-box;height:50px;margin-top:50px;text-align:center;position: absolute;bottom: 0;">
         <p style="width:100%;margin-top:20px;">西南交通大学 计算机网络工程实验室地址: 西南交通大学犀浦校区X9320</p>
       </div>
  </div>
</body>
<script>
new Vue({
  el:'#app',
  data:{
      navigators:[
        {
          'name':'使用指南',
          'link':'index.jsp',
          'isActive':false,
          'isDropdown':false,
          'dropdown':'',
          'menu':[],
        },
        {
          'name':'实验模拟',
          'link':'#',
          'isActive':false,
          'isDropdown':true,
          'dropdown':'dropdown',
          'menu':[
            {
              'name':'IP配置',
              'link':'Simulation.jsp',
            },
            {
              'name':'接口',
              'link':'ConnectionPort.jsp',
            },
            {
              'name': 'NAT/ACL',
              'link': 'NAT_ACLsetting.jsp'
            }
          ]
        },
        {
          'name':'知识巩固',
          'link':'Research.jsp',
          'isActive':false,
          'isDropdown':false,
          'dropdown':'',
          'menu':[],
        },
        {
          'name':'联系我们',
          'link':'linked.jsp',
          'isActive':true,
          'isDropdown':false,
          'dropdown':'',
          'menu':[],
        },
      ],
  },
  methods:{
  }
})

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.MainBody{
  border: 1px solid #f5f5f5;
  height: auto;
  margin-top: 52px;
  box-shadow: 0 6px 12px rgba(0,0,0,.175);
  min-height: 800px;
}
.LinkPage p{
  font-family:方正兰亭刊黑;font-size:20px;
}
</style>

</html>
