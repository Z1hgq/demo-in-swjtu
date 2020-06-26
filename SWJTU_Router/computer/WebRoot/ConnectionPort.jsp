<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType = "text/html" %>  <!-- database  -->
<%@ page import = "java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


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
  <div class="" id="app">
    <div class="navigator">
      <div class="hello">
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
      <div id="Step" style="display:inline-block">
        <div class="col-lg-6">
          <div style="font-size:26;font-family:微软雅黑;padding-top:100;">
          <b>步骤二</b>
          <h5>连线并设置计算机端口</h5>
        </div>
        </div>

      <div class="col-lg-6">
        <img src="images/NAT.png" style="width:100%;margin-top:100px;">
      </div>

      </div>
      <div id="PageSwitch">
      <ul class="pagination">
        <li><a href="Simulation.jsp">上一步</a></li>
        <li><a href="Simulation.jsp">1</a></li>
        <li><a class="active" href="ConnectionPort.jsp">2</a></li>
        <li><a href="NAT_ACLsetting.jsp">3</a></li>
        <li><a href="NAT_ACLsetting.jsp">下一步</a></li>
      </ul>
      </div>
       <div class="col-lg-6">
         <div class="">
           RA连PC1的端口
           <select v-model='port.ra_pc1'>
             <option>0</option>
             <option>1</option>
           </select>
         </div>
         <div class="">
           RA连RB的端口
           <select v-model='port.ra_rb'>
             <option>0</option>
             <option>1</option>
           </select>
         </div>
       </div>
       <div class="col-lg-6">
		     <div class="">
           RB连RA的端口
           <select v-model='port.rb_ra'>
             <option>0</option>
             <option>1</option>
           </select>
         </div>
         <div class="">
           RB连PC2的端口
           <select v-model='port.rb_pc2'>
             <option>0</option>
             <option>1</option>
           </select>
         </div>
         <button type="button" v-on:click='port_sub'>提交</button>
       </div>
       <div style="width: inherit;display: -webkit-box;height:50px;margin-top:50px;text-align:center;position: absolute;bottom: 0;">
         <p style="width:100%;margin-top:20px;">西南交通大学 计算机网络工程实验室地址: 西南交通大学犀浦校区X9320</p>
       </div>
       </div>

  </div>
</body>
<script>
new Vue({
  el:'#app',
  data:{
      port:{
        ra_pc1:0,
        ra_rb:0,
        rb_ra:0,
        rb_pc2:0
      },
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
          'isActive':true,
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
          'isActive':false,
          'isDropdown':false,
          'dropdown':'',
          'menu':[],
        },
      ],
  },
  methods:{
    port_sub:function(){
      var that = this;
      console.log(that.port);
      var str = JSON.stringify(that.port)
      localStorage.setItem('port',str);//端口
      var obj = localStorage.getItem('port');
      console.log(JSON.parse(obj).ra_pc1);

    }
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
