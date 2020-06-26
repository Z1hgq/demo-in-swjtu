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
      <div class="">
        <div class="col-lg-6">
          <div style="font-size:26;font-family:微软雅黑;padding-top:100;">
          <b>提示:</b>
          <h5>输入指令‘ping’然后回车，检测连通性</h5>
          <br>
          <br>
          <img src="images/NAT.png" alt="NAT配置拓扑图" style="width:90%">
          </div>
        </div>
        <div class="col-lg-6">
          <div style="width:70%;margin-left:15%;background:black;color:white;min-height:600px;margin-top:100px;">
            <div v-for='item in cmds' style="width:100%">
              <div style="height:20px;color:white;font-size:17px;padding-left:5px;">
                {{item}}
              </div>
            </div>
            <input id="cmd_in" v-model='cmd_in' placeholder="commond here" style="margin-top: 5px;height:20px;color:white;font-size:17px;background:black;border:none;width:100%;padding-left:5px;padding-top:2px;" @keyup.enter='cmd_input' v-focus>
          </div>
        </div>
      </div>


       <div style="width: 100%;display: -webkit-box;height:50px;margin-top:50px;text-align:center;">
         <p style="width:100%;margin-top:20px;">西南交通大学 计算机网络工程实验室地址: 西南交通大学犀浦校区X9320</p>
       </div>
       </div>

  </div>
</body>
<script>
  function getSon(son_netNum){
    var son_net=son_netNum.split(".");
    var s=0;
    if(son_net[0]==0){//判断该子网掩码是否为反掩码
      for(;s<=3;s++){//调换
        if(son_net[s]==0){
          son_net[s]=255;
        }
        else{
          son_net[s]=0;
        }
      }
      s=0;
    }
    var true_son=0;//记录子网掩码大区间
    for(;son_net[s]!=0;s++){
      true_son++;
      if(s>=3)//防止数组越界
        break;
    }
    return true_son;
  }
  
  function getSon_net(ip_str,son_netNum){
    var Son=getSon(son_netNum);//得到子网掩码相与位数
    var IP_str=ip_str.split(".");//将ip分割
    for(var s=0;s<=3;s++){
      if((s+1)>Son){//将大于子网掩码位数位置的IP值置0
        IP_str[s]=0;
      }
    }
    var true_IP=IP_str[0]+"."+IP_str[1]+"."+IP_str[2]+"."+IP_str[3];
    return true_IP;
  }
new Vue({
  el:'#app',
  data:{
      cmd_in:'',
      cmds:[
      ],
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
    cmd_input:function(){
      var that = this;
      if(that.cmd_in == 'ping'){
        that.cmds.push(that.cmd_in);
        //code here
        var pc1_ip = localStorage.getItem('pc1_ip');
        var pc1_mask = localStorage.getItem('pc1_mask');
        var ra_pc1_ip = localStorage.getItem('ra_pc1_ip');
        var ra_pc1_mask = localStorage.getItem('ra_pc1_mask');
        var ra_rb_ip = localStorage.getItem('ra_rb_ip');
        var ra_rb_mask = localStorage.getItem('ra_rb_mask');
        var rb_ra_ip = localStorage.getItem('rb_ra_ip');
        var rb_ra_mask = localStorage.getItem('rb_ra_mask');
        var rb_pc2_ip = localStorage.getItem('rb_pc2_ip');
        var rb_pc2_mask = localStorage.getItem('rb_pc2_mask');
        var pc2_ip = localStorage.getItem('pc2_ip');
        var pc2_mask = localStorage.getItem('pc2_mask');

        var subnet_pc1_ra = getSon_net(pc1_ip,pc1_mask);
        var subnet_ra_pc1 = getSon_net(ra_pc1_ip,ra_pc1_mask);
        var subnet_ra_rb = getSon_net(ra_rb_ip,ra_rb_mask);
        var subnet_rb_ra = getSon_net(rb_ra_ip,rb_ra_mask);
        var subnet_rb_pc2 = getSon_net(rb_pc2_ip,rb_pc2_mask);
        var subnet_pc2_rb = getSon_net(pc2_ip,pc2_mask);

        var flag = true;
        if(subnet_pc1_ra == subnet_ra_pc1){
          that.cmds.push('PC1到RA连通!');
        }else{
          that.cmds.push('PC1到RA不连通!');
          flag = false;
        }
        if(subnet_ra_rb == subnet_rb_ra){
          that.cmds.push('RA到RB连通!');
        }else{
          that.cmds.push('RA到RB不连通!');
          flag = false;
        }
        if(subnet_rb_pc2 == subnet_pc2_rb){
          that.cmds.push('RB到PC2连通!');
        }else{
          that.cmds.push('RB到PC2不连通!');
          flag = false;
        }
        if(flag){
          that.cmds.push('整条线路连通!');
        }else{
          that.cmds.push('整条线路不连通!');
        }
        that.cmd_in='';
      }else if(that.cmd_in != '' && that.cmd_in != 'ping'){
        that.cmds.push('invalid commond!');
        that.cmd_in='';
      }else{
        that.cmd_in='';
      }
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
