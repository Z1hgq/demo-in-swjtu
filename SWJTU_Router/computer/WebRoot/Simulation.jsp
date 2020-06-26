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
          <b>步骤一</b>
          <h5>设置计算机的IP地址</h5>
        </div>
        </div>

      <div class="col-lg-6">
        <img src="images/NAT.png" style="width:100%;margin-top:100px;">
        <p style="float:left;">{{ip.pc1.ip1}}.{{ip.pc1.ip2}}.{{ip.pc1.ip3}}.{{ip.pc1.ip4}}</p>
        <p style="float:right;">{{ip.pc2.ip1}}.{{ip.pc2.ip2}}.{{ip.pc2.ip3}}.{{ip.pc2.ip4}}</p>
      </div>

      </div>
      <div id="PageSwitch">
      <ul class="pagination">
        <li><a class="active" href="Simulation.jsp">1</a></li>
        <li><a href="ConnectionPort.jsp">2</a></li>
        <li><a href="NAT_ACLsetting.jsp">3</a></li>
        <li><a href="ConnectionPort.jsp">下一步</a></li>
      </ul>
      </div>
      <div id="line1"></div>
      <div class="">
      <form method="post" name="form1" target="nm_iframe"  onsubmit="return tcheck();">
        <iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>
      <div class="col-lg-6" id="LeftBody">
      配置计算机IP：
      <p>PC1:<input v-model='ip.pc1.ip1' style="width:50px;">.
         <input v-model='ip.pc1.ip2' style="width:50px;">.
         <input v-model='ip.pc1.ip3' style="width:50px;">.
         <input v-model='ip.pc1.ip4' style="width:50px;">
      </p>
      <p>PC2:<input v-model='ip.pc2.ip1' style="width:50px;">.
         <input v-model='ip.pc2.ip2' style="width:50px;">.
         <input v-model='ip.pc2.ip3' style="width:50px;">.
         <input v-model='ip.pc2.ip4' style="width:50px;">
         </p>
         <p>RB_RA:<input v-model='rb_ip.rb_ra.ip1' style="width:50px;">.
         <input v-model='rb_ip.rb_ra.ip2' style="width:50px;">.
         <input v-model='rb_ip.rb_ra.ip3' style="width:50px;">.
         <input v-model='rb_ip.rb_ra.ip4' style="width:50px;">
         </p>
         <p>RB_PC2:<input v-model='rb_ip.rb_pc2.ip1' style="width:50px;">.
         <input v-model='rb_ip.rb_pc2.ip2' style="width:50px;">.
         <input v-model='rb_ip.rb_pc2.ip3' style="width:50px;">.
         <input v-model='rb_ip.rb_pc2.ip4' style="width:50px;">
         </p>
       </div>
       <div class="col-lg-6" id="RightBody">
       <div id="Right">
      子网掩码:
      <p>PC1:<input v-model='subnet_mask.pc1.mask1' style="width:50px;">.
         <input v-model='subnet_mask.pc1.mask2' style="width:50px;">.
         <input v-model='subnet_mask.pc1.mask3' style="width:50px;">.
         <input v-model='subnet_mask.pc1.mask4' style="width:50px;">
      </p>
      <p>PC2:<input v-model='subnet_mask.pc2.mask1' style="width:50px;">.
         <input v-model='subnet_mask.pc2.mask2' style="width:50px;">.
         <input v-model='subnet_mask.pc2.mask3' style="width:50px;">.
         <input v-model='subnet_mask.pc2.mask4' style="width:50px;">
         </p>
         <p>RB_RA:<input v-model='rb_mask.rb_ra.mask1' style="width:50px;">.
         <input v-model='rb_mask.rb_ra.mask2' style="width:50px;">.
         <input v-model='rb_mask.rb_ra.mask3' style="width:50px;">.
         <input v-model='rb_mask.rb_ra.mask4' style="width:50px;">
         </p>
         <p>RB_PC2:<input v-model='rb_mask.rb_pc2.mask1' style="width:50px;">.
         <input v-model='rb_mask.rb_pc2.mask2' style="width:50px;">.
         <input v-model='rb_mask.rb_pc2.mask3' style="width:50px;">.
         <input v-model='rb_mask.rb_pc2.mask4' style="width:50px;">
         </p>
         <button type="button" v-on:click='ip_mask_sub'>提交</button>
       </div>

       <div id="line2"></div>
       </div>
       </form>
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
      ip:{
        pc1:{
          ip1:0,
          ip2:0,
          ip3:0,
          ip4:0
        },
        pc2:{
          ip1:0,
          ip2:0,
          ip3:0,
          ip4:0
        }
      },
      subnet_mask:{
        pc1:{
          mask1:0,
          mask2:0,
          mask3:0,
          mask4:0
        },
        pc2:{
          mask1:0,
          mask2:0,
          mask3:0,
          mask4:0
        }
      },
      rb_ip:{
        rb_ra:{
          ip1:0,
          ip2:0,
          ip3:0,
          ip4:0
        },
        rb_pc2:{
          ip1:0,
          ip2:0,
          ip3:0,
          ip4:0
        }
      },
      rb_mask:{
        rb_ra:{
          mask1:0,
          mask2:0,
          mask3:0,
          mask4:0
        },
        rb_pc2:{
          mask1:0,
          mask2:0,
          mask3:0,
          mask4:0
        }
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
    ip_mask_sub:function(){
      var that = this;
      var pc1_ip=that.ip.pc1.ip1+'.'+that.ip.pc1.ip2+'.'+that.ip.pc1.ip3+'.'+that.ip.pc1.ip4;
      var pc2_ip=that.ip.pc2.ip1+'.'+that.ip.pc2.ip2+'.'+that.ip.pc2.ip3+'.'+that.ip.pc2.ip4;
      var pc1_mask=that.subnet_mask.pc1.mask1+'.'+that.subnet_mask.pc1.mask2+'.'+that.subnet_mask.pc1.mask3+'.'+that.subnet_mask.pc1.mask4;
      var pc2_mask=that.subnet_mask.pc2.mask1+'.'+that.subnet_mask.pc2.mask2+'.'+that.subnet_mask.pc2.mask3+'.'+that.subnet_mask.pc2.mask4;
      var rb_ra_ip = that.rb_ip.rb_ra.ip1+'.'+that.rb_ip.rb_ra.ip2+'.'+that.rb_ip.rb_ra.ip3+'.'+that.rb_ip.rb_ra.ip4;
      var rb_pc2_ip = that.rb_ip.rb_pc2.ip1+'.'+that.rb_ip.rb_pc2.ip2+'.'+that.rb_ip.rb_pc2.ip3+'.'+that.rb_ip.rb_pc2.ip4;
      var rb_ra_mask = that.rb_mask.rb_ra.mask1+'.'+that.rb_mask.rb_ra.mask2+'.'+that.rb_mask.rb_ra.mask3+'.'+that.rb_mask.rb_ra.mask4;
      var rb_pc2_mask = that.rb_mask.rb_pc2.mask1+'.'+that.rb_mask.rb_pc2.mask2+'.'+that.rb_mask.rb_pc2.mask3+'.'+that.rb_mask.rb_pc2.mask4;


      console.log(pc1_ip);
      console.log(pc2_ip);
      console.log(pc1_mask);
      console.log(pc2_mask);
      console.log(rb_ra_ip);
      console.log(rb_pc2_ip);
      console.log(rb_ra_mask);
      console.log(rb_pc2_mask);
      localStorage.setItem('pc1_ip',pc1_ip);//pc1和pc2的ip以及掩码
      localStorage.setItem('pc2_ip',pc2_ip);
      localStorage.setItem('pc1_mask',pc1_mask);
      localStorage.setItem('pc2_mask',pc2_mask);    
      localStorage.setItem('rb_ra_ip',rb_ra_ip);
      localStorage.setItem('rb_pc2_ip',rb_pc2_ip);
      localStorage.setItem('rb_ra_mask',rb_ra_mask);
      localStorage.setItem('rb_pc2_mask',rb_pc2_mask);
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
