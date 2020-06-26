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
       <div class="col-lg-6">
        <h3>NAT</h3>
        <p>R20A>enable 14</p>
        <p>Password:</p>
        <p>R20A#configure terminal</p>
        <p>Enter configuration commands, one per line.  End with CNTL/Z.</p>
        <p>R20A(config)#interface serial 2/0</p>
        <p>R20A(config-if-Serial 2/0)#ip address 11.0.1.1 255.255.255.0</p>
        <p>R20A(config-if-Serial 2/0)#exit</p>
        <p>R20A(config)#interface gigabitEthernet 0/0</p>
        <p>R20A(config-if-GigabitEthernet 0/0)#ip address 10.0.1.1 255.255.255.0</p>
        <p>R20A(config-if-GigabitEthernet 0/0)#exit</p>
        <p>R20A(config)#ip route 11.0.2.0 255.255.255.0 serial 2/0</p>

        <p>R20A(config)#interface gigabitEthernet 0/0</p>
        <p>R20A(config-if-GigabitEthernet 0/0)#ip nat inside</p>
        <p>R20A(config-if-GigabitEthernet 0/0)#exit</p>
        <p>R20A(config)#interface serial 2/0</p>
        <p>R20A(config-if-Serial 2/0)#ip nat outside</p>
        <p>R20A(config-if-Serial 2/0)#access-list 10 permit 10.0.1.0 0.0.0.255</p>
        <p>R20A(config)#ip nat pool abc 11.0.1.1 11.0.1.1 netmask 255.255.255.0</p>
        <p>R20A(config)#ip nat inside source list 10 pool abc overload</p>

        <p>R20A(config)#access-list name3 deny 10.0.1.0 0.0.0.255</p>
        <p>R20A(config-if-GigabitEthernet 0/0)#ip access-group name3 out</p>


        <p>R20A(config)#show ip route</p>

        <p>Codes:  C - connected, S - static, R - RIP, B - BGP</p>
        <p>        O - OSPF, IA - OSPF inter area</p>
        <p>        N1 - OSPF NSSA external type 1, N2 - OSPF NSSA external type 2</p>
        <p>        E1 - OSPF external type 1, E2 - OSPF external type 2</p>
        <p>        i - IS-IS, su - IS-IS summary, L1 - IS-IS level-1, L2 - IS-IS level-2</p>
        <p>        ia - IS-IS inter area, * - candidate default</p>

        <p>Gateway of last resort is no set</p>
        <p>C    10.0.1.0/24 is directly connected, GigabitEthernet 0/0</p>
        <p>C    10.0.1.1/32 is local host.</p>
        <p>C    11.0.1.0/24 is directly connected, Serial 2/0</p>
        <p>C    11.0.1.1/32 is local host.</p>
        <p>S    11.0.2.0/24 is directly connected, Serial 2/0</p>

        <p>R20A(config)#show ip nat translations</p>
        <p>Pro Inside global      Inside local       Outside local      Outside glob</p>
        <p>icmp11.0.1.1:1         10.0.1.2:1         11.0.2.2           11.0.2.2</p>

        <p>R20A(config)#ip nat inside sourse list 10 pool abc overload</p>
        <p>% Invalid input detected at '^' marker.</p>
        <p>R20A(config)#ip nat inside source</p>
        <p>% Incomplete command.</p>
       </div>
       <div class="col-lg-6">
          <h3>ACL</h3>
          <p>R20A>enable 14</p>
          <p>Password:</p>
          <p>R20A#configure terminal</p>
          <p>Enter configuration commands, one per line.  End with CNTL/Z.</p>
          <p>R20A(config)#interface serial 2/0</p>
          <p>R20A(config-if-Serial 2/0)#ip address 11.0.1.1 255.255.255.0</p>
          <p>R20A(config-if-Serial 2/0)#exit</p>
          <p>R20A(config)#interface gigabitEthernet 0/0</p>
          <p>R20A(config-if-GigabitEthernet 0/0)#ip address 11.0.3.1 255.255.255.0</p>
          <p>R20A(config-if-GigabitEthernet 0/0)#exit</p>
          <p>R20A(config)#ip route 11.0.2.0 255.255.255.0 serial 2/0</p>
          <p>R20A(config)#ip route 11.0.2.0 255.255.255.0 11.0.1.2</p>


          <p>R20A>enable 14</p>

          <p>Password:</p>
          <p>Password:</p>
          <p>Password:</p>

          <p>% Access denied</p>



          <p>C:\Users\dell>ping 11.0.2.2</p>

          <p>正在 Ping 11.0.2.2 具有 32 字节的数据:</p>
          <p>来自 11.0.2.2 的回复: 字节=32 时间=37ms TTL=126</p>
          <p>来自 11.0.2.2 的回复: 字节=32 时间=38ms TTL=126</p>
          <p>来自 11.0.2.2 的回复: 字节=32 时间=40ms TTL=126</p>
          <p>来自 11.0.2.2 的回复: 字节=32 时间=38ms TTL=126</p>

          <p>11.0.2.2 的 Ping 统计信息:</p>
          <p>   数据包: 已发送 = 4，已接收 = 4，丢失 = 0 (0% 丢失)，</p>
          <p>往返行程的估计时间(以毫秒为单位):</p>
          <p>    最短 = 37ms，最长 = 40ms，平均 = 38ms</p>

          <p>C:\Users\dell>ping 11.0.2.2</p>

          <p>正在 Ping 11.0.2.2 具有 32 字节的数据:</p>
          <p>请求超时。</p>
          <p>请求超时。</p>
          <p>请求超时。</p>
          <p>来自11.0.2.2:无法访问目标网</p>

          <p>11.0.2.2 的 Ping 统计信息:</p>
          <p>    数据包: 已发送 = 4，已接收 = 0，丢失 = 4 (100% 丢失)，</p>

          <p>C:\Users\dell></p>




       </div>
       <div style="width: inherit;display: -webkit-box;height:50px;margin-top:50px;text-align:center;bottom: 0;">
         <p style="width:100%;margin-top:20px;">西南交通大学 计算机网络工程实验室地址: 西南交通大学犀浦校区X9320</p>
       </div>
       </div>

  </div>
</body>
<script>
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
          'isActive':true,
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
