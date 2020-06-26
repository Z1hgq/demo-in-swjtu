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
      <div class="">

        <div class="col-lg-6">
          <div style="font-size:26;font-family:微软雅黑;padding-top:100;">
          <b>NAT</b>
          <br>
          <br>
          <img src="images/NAT.png" alt="NAT配置拓扑图" style="width:90%">
          <button type="button" name="button" style="margin-top:10px" v-on:click='store_ra_ip_mask'><a href="cmd.jsp" style="font-size:17px;text-decoration:none;">ping</a></button>
          </div>
          <div style="font-size:26;font-family:微软雅黑;padding-top:100;">
          <b>ACL</b>
          <br>
          <br>
          <img src="images/ACL.png" alt="ACL配置拓扑图" style="width:90%">
          <button type="button" name="button" style="margin-top:10px" v-on:click='store_ra_ip_mask'><a href="cmd.jsp" style="font-size:17px;text-decoration:none;">ping</a></button>
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
        <div id="PageSwitch" style="display:inline-block">
        <ul class="pagination">
          <li><a href="ConnectionPort.jsp">上一步</a></li>
          <li><a href="Simulation.jsp">1</a></li>
          <li><a href="ConnectionPort.jsp">2</a></li>
          <li><a class="active" href="Vlansetting.jsp">3</a></li>
        </ul>
        </div>
      </div>


       <div style="width: 100%;display: -webkit-box;height:50px;margin-top:50px;text-align:center;">
         <p style="width:100%;margin-top:20px;">西南交通大学 计算机网络工程实验室地址: 西南交通大学犀浦校区X9320</p>
       </div>
       </div>

  </div>
</body>
<script>
new Vue({
  el:'#app',
  data:{
      cmd_in:'R20A>',
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
      state_num:-1,
      front:'R20A>',
      ra_pc1_ip:'0.0.0.0',//ra_pc1
      ra_pc1_mask:'0.0.0.0',//ra_pc1
      ra_rb_ip:'0.0.0.0',//ra_rb
      ra_rb_mask:'0.0.0.0',//ra_rb
      denylist_name:'',
      permlist_name:'',
      pool_name:'',
  },
  methods:{
    cmd_input:function(){
      var that = this;
      //获取用户输入数据
      var pc1_ip=localStorage.getItem("pc1_ip");
      var pc2_ip=localStorage.getItem("pc2_ip");
      var pc1_mask=localStorage.getItem("pc1_mask");
      var pc2_mask=localStorage.getItem("pc2_mask");
      var obj=localStorage.getItem("port");
      var port=JSON.parse(obj);
      //对用户输入进行处理
      var pc1_submask;//pc1所属子网
      var pc2_submask;//pc2所属子网
      var ra_pc1_submask;//rb_pc1所属子网
      var rb_pc2_submask;//ra_pc2所属子网
      var ra_rb_submask;//所属子网
      var rb_ra_submask;//所属子网
      
      var state_0=that.front+'enable 14' ;
      var state_1=that.front+'student' ;
      var state_2=that.front+'configure terminal' ;
      var state_3=that.front+'interface serial '+port.ra_rb+'/0' ;
      var state_4=that.front+'interface gigabitEthernet 0/'+port.ra_pc1;
      var state_5=that.front+'ip route 11.0.2.0 255.255.255.0 serial 2/0' ;
      var state_6=that.front+'ip nat pool abc 11.0.1.1 11.0.1.1 netmask 255.255.255.0';
      var state_7=that.front+'ip nat inside source list 10 pool abc overload';//10应被提取出来保存 
      var state_8=that.front+'access-list name3 deny 10.0.1.0 0.0.0.255';//name3应被保存 IP和反掩码也要被保存
      var state_9=that.front+'show ip nat translations';

      if(that.cmd_in != ''){
        that.cmds.push(that.cmd_in);
        var ping_str=that.cmd_in;
        console.log('输入指令：'+ping_str);
        console.log('对比指令：'+state_4);
        if(ping_str==state_0){//判断指令是不是上述0
          if(that.state_num==-1){
            that.state_num=0;
            that.front='password:';
          }
        }   
        
        else if(ping_str==state_1){//判断指令是不是上述1
          if(that.state_num==0){
            that.state_num++;
            that.front='R20A#'; 
            var index = that.cmds.length;
            if (index > -1) {
              that.cmds.splice(index-1, 1);
              that.cmds.push('password：');
              }
            }else { //可以改成统一的指令错误提示
              that.cmds.push('%invaild command');//输出 invaild command
            }//密码错误
          }
        
        else if(ping_str==state_2){//判断指令是不是上述2
          if(that.state_num==1){
            that.state_num=2;
            that.front='R20A(config)#';
            }
          }else if(that.state_num==1&&ping_str!=state_2){
            that.cmds.push('invaild command');//输出 invaild command
          }//路由器配置指令错误  

          else if(ping_str==state_3){ //'interface serial 2/0' ; 此指令在状态2可以生效
          serial=ping_str.split(" ");//分割指令 此指令选定的端口必须是配置的端口 所以需要提取指令中输入的端口
          serial_num=serial[2]; //例提取到端口是 2/0     //根据serial_num显示具体哪个接口信息
          that.front='R20A(config-if-Serial '+serial_num+')#';
          that.state_num=3; 
        }
          else if(that.state_num==3){//进入s口配置之后的操作ip address 10.0.1.1 255.255.255.0 或 access-list name3 deny 10.0.1.0 0.0.0.255 
            con=ping_str.split(" ");
            if((con[0]==that.front+'ip')&&con[1]=='address')
            {
              that.ra_rb_ip=con[2];that.ra_rb_mask=con[3];    
            }
            else if(con[0]==that.front+'access-list')
            {
              if(con[2]=='permit'){
                if(con[3]==that.ra_rb_ip) that.permlist_name=con[1];//此处还有判断con[4]是否为相应反掩码*****************
              }
            }
          }
          
          else if(ping_str==state_4){ //interface gigabitEthernet 0/2;
          giga=ping_str.split(" ");//分割指令 此指令选定的端口必须是配置的端口 所以需要提取指令中输入的端口
          giga_num=giga[2]; //例提取到端口是 0/2
          //根据serial_num显示具体哪个接口信息
          that.front='R20A(config-if-GigabitEthernet '+giga_num+')#';
          that.state_num=4; 
        }
          else if(that.state_num==4){//进入g口配置之后的操作
            con=ping_str.split(" ");//分割指令 此指令选定的端口必须是配置的端口 所以需要提取指令中输入的端口
            if((con[0]==that.front+'ip')&&con[1]=='address')
            {
              that.ra_pc1_ip=con[2];that.ra_pc1_mask=con[3];    
            }
          } 
        else if(that.state_num==2){//进入路由配置之后需要重新判别输入的指令类别
          con=ping_str.split(" ");
          var ping=con.length; //利用foreach遍历ping_str看有多少个元素
          if(ping==4){//show ip nat translations
            //判断con数组中四个元素是否是show ip nat translations
            //并在控制台显示ip nat信息
            if(ping_str==state_9)
            {
              that.cmds.push('Pro Inside global      Inside local       Outside local      Outside globl');
              that.cmds.push('icmp '+that.ra_pc1_ip+':1'+' '+that.ra_pc1_ip+':1 '+' '+pc2_ip+' '+pc2_ip);
            }
          }else if(ping==5){//access-list name3 deny 10.0.1.0 0.0.0.255 
            //con[1]是定义的name3，需要保存 
            if(con[0]==that.front+'access-list'){
              if(con[2]=='deny'){           
                that.denylist_name=con[1];
              }
            }
          }else if(ping==6){//ip route 11.0.2.0 255.255.255.0 serial 2/0
            //同上
            if(con[0]==(that.front+'ip')&&con[1]=='route')
            {
              if(con[4]==+port.ra_rb+'/0')   //输入是不是等于配置的 端口号/0
              {
                route_ip=con[2];
                route_mask=con[3];
                that.state_num=5;
              }
            }
          }else if(ping==8){//ip nat pool abc 11.0.1.1 11.0.1.1 netmask 255.255.255.0
            //同上
            if(con[0]==that.front+'ip'){
              if(con[2]=='nat'&&con[3]=='pool'&&con[6]=='netmask')
              {
                pool_name6=con[3];//保存abc   此处abc也为任意字符串*****************************
              }
            that.state_num=6;}
          }else if(ping==9){//ip nat inside source list 10 pool abc overload
            //同上
            if(ping_str==state_7){
              if(pool_name6==con[7]&&list_name8==con[5])  ;//判断指令中的list编号和pool编号是否正确   编号应为permlist_name
              that.state_num=7;
              }
          }
          }
        
        console.log('statenum:'+that.state_num);
        console.log('front:'+that.front);
        that.cmd_in=that.front;
      }
    },
    store_ra_ip_mask:function(){
      var that = this;
      localStorage.setItem('ra_rb_ip',that.ra_rb_ip);
      localStorage.setItem('ra_rb_mask',that.ra_rb_mask);
      localStorage.setItem('ra_pc1_ip',that.ra_pc1_ip);
      localStorage.setItem('ra_pc1_mask',that.ra_pc1_mask);
      console.log('store_ra_ip_mask success');
    },
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
