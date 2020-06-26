package JavaClassDesign2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class GUIDesign
{  
    JPanel panel; 
    /*
     *设置登录界面
     */
    JFrame jf;
    JLabel nameLabel,pwLabel;
    JTextField nameTextField;
    JPasswordField pwTextField;
    JButton loginButton,registerButton;  
     
    Statement stmt;
    String registerName,registerPw,registerTel,registerEmail;//用户注册的信息
    String addNo,addLocation,addCapacity,addBusy;
    String currentName,currentPw;//当前输入的名字密码
    Font f = new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,25);	
    int userId = 1;
    int classroomId = 1;
    GUIDesign(){  
        init();
        jf.setBounds(800,500,800,500);  
        jf.setVisible(true);  
        jf.setResizable(false);//程序窗口不可改变大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }  
    public void init(){
    	/*
    	 * 连接数据库
    	 */
    	 try { 
      	   Class.forName("com.mysql.jdbc.Driver");   //加载MYSQL JDBC驱动程序  
      	   //Class.forName("org.gjt.mm.mysql.Driver"); 
      	   System.out.println("Success loading Mysql Driver!"); 
      	  } 
      	  catch (Exception e) { 
      	   System.out.print("Error loading Mysql Driver!"); 
      	   e.printStackTrace(); 
      	  } 
      	  try { 
      	   Connection connect = DriverManager.getConnection( 
      	     "jdbc:mysql://localhost:3306/classroom?&useSSL=true","root","Z1hgq260717"); 
      	      //连接URL为  jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码 
      	  
      	   System.out.println("Success connect Mysql server!"); 
      	   stmt = connect.createStatement(); 
      	  }
      	  catch (Exception e) { 
      		System.out.print("get data error!"); 
      		e.printStackTrace(); 
      	}
      	/*
      	 * 界面设置 
      	 */
      	jf = new JFrame("教室借用和查询系统");
        panel = new JPanel(); 
        panel.setBackground(Color.white);
        panel.setLayout(null);
        jf.add(panel);
        
        nameLabel = new JLabel("姓名");nameLabel.setFont(f);nameLabel.setBounds(230, 50, 100, 50);panel.add(nameLabel);
        pwLabel = new JLabel("密码");pwLabel.setFont(f);pwLabel.setBounds(230, 150, 100, 50);panel.add(pwLabel);
        nameTextField = new JTextField();nameTextField.setFont(f);nameTextField.setBounds(310, 50, 200, 50);panel.add(nameTextField);
        pwTextField = new JPasswordField();pwTextField.setFont(f);pwTextField.setBounds(310, 150, 200, 50);panel.add(pwTextField);
        loginButton = new JButton("登录");loginButton.setFont(f);loginButton.setBackground(Color.LIGHT_GRAY);loginButton.setBounds(280, 250, 100, 50);panel.add(loginButton);
        registerButton = new JButton("注册");registerButton.setFont(f);registerButton.setBackground(Color.LIGHT_GRAY);registerButton.setBounds(420, 250, 100, 50);panel.add(registerButton);
        
        registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new RegisterDialog().jd1.setVisible(true);
			}
        	
        });
        loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				currentName = nameTextField.getText();
				currentPw = pwTextField.getText();
				boolean flag = false;
				if(currentPw.equals("")) {
					new LackOfPwDialog().jd1.setVisible(true);
					flag = true;
				}
				try {
					ResultSet login = stmt.executeQuery("select * from user");
					
					while ( login.next()) {//检测原来表中有多少项数据，方便确定id的值 
						if(login.getString("name").equals(currentName) && login.getString("pw").equals(currentPw))//登陆成功
						{
							flag = true;
							panel.removeAll();//清除界面上原有的内容
							/*
							 * 设置操作面板上的内容
							 */
							JButton queryUserInfoButton,queryClassRoomInfoButton,deleteUserInfoButton,deleteClassRoomInfoButton,addClassRoomInfoButton,
							BorrowClassRoomButton,returnClassRoomButton;
							JLabel UserInfoLabel,ClassRoomInfoLabel;
							JTextArea UserInfoArea,ClassRoomInfoArea;
							UserInfoLabel = new JLabel("用户信息");UserInfoLabel.setFont(f);UserInfoLabel.setBounds(20, 0, 100, 50);panel.add(UserInfoLabel);
							ClassRoomInfoLabel = new JLabel("教室信息");ClassRoomInfoLabel.setFont(f);ClassRoomInfoLabel.setBounds(20, 200, 100, 50);panel.add(ClassRoomInfoLabel);
							//设置用户信息显示区
							UserInfoArea = new JTextArea();
							UserInfoArea.setFont(f);
							UserInfoArea.setBackground(Color.cyan);
							//UserInfoArea.setBounds(20, 50, 500, 150);
							JScrollPane js=new JScrollPane(UserInfoArea);
							js.setBounds(20, 50, 500, 150);
							//分别设置水平和垂直滚动条自动出现
							js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
							panel.add(js);
							//设置教室信息显示区
							ClassRoomInfoArea = new JTextArea();
							ClassRoomInfoArea.setFont(f);
							ClassRoomInfoArea.setBackground(Color.cyan);
							//UserInfoArea.setBounds(20, 50, 500, 150);
							JScrollPane js1=new JScrollPane(ClassRoomInfoArea);
							js1.setBounds(20, 250, 500, 150);
							//分别设置水平和垂直滚动条自动出现
							js1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							js1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
							panel.add(js1);
							//设置不可编辑
							UserInfoArea.setEditable(false);
							ClassRoomInfoArea.setEditable(false);
							//操作按钮设置
							queryUserInfoButton = new JButton("查询用户信息");queryUserInfoButton.setFont(f);
							queryUserInfoButton.setBounds(550, 20,200, 50);queryUserInfoButton.setBackground(Color.LIGHT_GRAY);
							panel.add(queryUserInfoButton);
							queryClassRoomInfoButton= new JButton("查询教室信息");queryClassRoomInfoButton.setFont(f);
							queryClassRoomInfoButton.setBounds(550, 80,200, 50);queryClassRoomInfoButton.setBackground(Color.LIGHT_GRAY);
							panel.add(queryClassRoomInfoButton);
							deleteUserInfoButton= new JButton("删除用户信息");deleteUserInfoButton.setFont(f);
							deleteUserInfoButton.setBounds(550, 140,200, 50);deleteUserInfoButton.setBackground(Color.LIGHT_GRAY);
							panel.add(deleteUserInfoButton);
							deleteClassRoomInfoButton= new JButton("删除教室信息");deleteClassRoomInfoButton.setFont(f);
							deleteClassRoomInfoButton.setBounds(550, 200,200, 50);deleteClassRoomInfoButton.setBackground(Color.LIGHT_GRAY);
							panel.add(deleteClassRoomInfoButton);
							addClassRoomInfoButton= new JButton("添加教室信息");addClassRoomInfoButton.setFont(f);
							addClassRoomInfoButton.setBounds(550, 260,200, 50);addClassRoomInfoButton.setBackground(Color.LIGHT_GRAY);
							panel.add(addClassRoomInfoButton);
							BorrowClassRoomButton= new JButton("借教室");BorrowClassRoomButton.setFont(f);
							BorrowClassRoomButton.setBounds(550, 320,200, 50);BorrowClassRoomButton.setBackground(Color.LIGHT_GRAY);
							panel.add(BorrowClassRoomButton);
							returnClassRoomButton= new JButton("还教室");returnClassRoomButton.setFont(f);
							returnClassRoomButton.setBounds(550, 380,200, 50);returnClassRoomButton.setBackground(Color.LIGHT_GRAY);
							panel.add(returnClassRoomButton);
							
							//设置操作按钮响应函数
							//查询用户信息
							queryUserInfoButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									UserInfoArea.setText("");
									try {
									ResultSet UserInfo = stmt.executeQuery("select * from user"); 
					                //user 为你表的名称 
									while (UserInfo.next()) {//检测原来表中有多少项数据，方便确定id的值 
										String userinfo = UserInfo.getString("id")+"  "+UserInfo.getString("name")+"  "+UserInfo.getString("pw")+"  "+UserInfo.getString("email")+"  "+UserInfo.getString("tel")+"\n";
										//System.out.println(UserInfo.getString("name"));
										UserInfoArea.append(userinfo);				
									}
									} catch (SQLException e0) {
										// TODO Auto-generated catch block
										e0.printStackTrace();
									}
								}					
							});
							//查询教室信息
							queryClassRoomInfoButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									ClassRoomInfoArea.setText("");
									try {
										ResultSet ClassRoomInfo = stmt.executeQuery("select * from rooms"); 
						                //user 为你表的名称 
										while (ClassRoomInfo.next()) {//检测原来表中有多少项数据，方便确定id的值 
											String classroominfo = ClassRoomInfo.getString("id")+"  "+ClassRoomInfo.getString("no")+"  "+ClassRoomInfo.getString("location")+"  "+ClassRoomInfo.getString("capacity")+"  "+ClassRoomInfo.getString("busy")+"\n";
											//System.out.println(UserInfo.getString("name"));
											ClassRoomInfoArea.append(classroominfo);				
										}
										} catch (SQLException e0) {
											// TODO Auto-generated catch block
											e0.printStackTrace();
										}
								}					
							});
							//删除用户信息
							deleteUserInfoButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									new DeleteUserInfoDialog().jd1.setVisible(true);
								}					
							});
							//删除教室信息
							deleteClassRoomInfoButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									new DeleteClassRoomInfoDialog().jd1.setVisible(true);
								}					
							});
							//添加教室信息
							addClassRoomInfoButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									new AddClassRoomDialog().jd1.setVisible(true);
								}					
							});
							//借教室
							BorrowClassRoomButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									new BorrowClassRoomInfoDialog().jd1.setVisible(true);
								}					
							});
							//换教室
							returnClassRoomButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									new ReturnClassRoomInfoDialog().jd1.setVisible(true);
								}					
							});
						}
					
						else if(login.getString("name").equals(currentName) && !login.getString("pw").equals(currentPw)) {
							flag = true;
							new IncorrectPwDialog().jd1.setVisible(true);
						}
						
					    //遍历完了人没找到用户的话说明没找到
						}
					if(!flag) new UserNotRegisterDialog().jd1.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				panel.validate();
				jf.repaint();//重绘
			} 	
        });
    }
    //没输入密码提示窗口
    class LackOfPwDialog{
		//初始化弹出式对话框	
		JDialog jd1 = new JDialog(jf,"提示消息",true);
		LackOfPwDialog(){
		jd1.setBounds(1000,600,400,300);
		jd1.setVisible(false);
		jd1.setLayout(null);
		//设置对话框内容
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 400, 300);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);		
		JLabel jl4 = new JLabel("请输入密码！");
		jl4.setBounds(130, 0, 300, 150);
		jl4.setFont(f);
		jp1.add(jl4);
		JButton jb3 = new JButton("确定");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(150,150,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jd1.dispose();
			}		
		});
		jd1.add(jp1);	
		jd1.add(jb3);
		jd1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
    //用户未注册提示窗口
    class UserNotRegisterDialog{
		//初始化弹出式对话框	
		JDialog jd1 = new JDialog(jf,"提示消息",true);
		UserNotRegisterDialog(){
		jd1.setBounds(1000,600,400,300);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//设置对话框内容
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 400, 300);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);		
		JLabel jl4 = new JLabel("该用户尚未注册！");
		jl4.setBounds(100, 0, 300, 150);
		jl4.setFont(f);
		jp1.add(jl4);
		JButton jb3 = new JButton("确定");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(150,150,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jd1.dispose();
			}		
		});
		jd1.add(jp1);	
		jd1.add(jb3);
		jd1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
    //密码错误提示窗口
    class IncorrectPwDialog{
		//初始化弹出式对话框	
		JDialog jd1 = new JDialog(jf,"提示消息",true);
		IncorrectPwDialog(){
		jd1.setBounds(1000,600,400,300);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//设置对话框内容
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 400, 300);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);		
		JLabel jl4 = new JLabel("密码错误！");
		jl4.setBounds(140, 0, 300, 150);
		jl4.setFont(f);
		jp1.add(jl4);
		JButton jb3 = new JButton("确定");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(150,150,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jd1.dispose();
			}		
		});
		jd1.add(jp1);	
		jd1.add(jb3);
		jd1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
    //用户注册窗口
    class RegisterDialog{
		//初始化弹出式对话框	
		JDialog jd1 = new JDialog(jf,"用户注册",true);
		RegisterDialog(){
		//对话框基本属性
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//设置对话框内容
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("姓名");jl4.setBounds(220, 50, 100, 50);jl4.setFont(f);jp1.add(jl4);
		JLabel jl5 = new JLabel("密码");jl5.setBounds(220, 130, 100, 50);jl5.setFont(f);jp1.add(jl5);
		JLabel jl6 = new JLabel("邮箱");jl6.setBounds(220, 210, 100, 50);jl6.setFont(f);jp1.add(jl6);
		JLabel jl7 = new JLabel("电话");jl7.setBounds(220, 290, 100, 50);jl7.setFont(f);jp1.add(jl7);
		//注册信息输入
		JTextField jt1 = new JTextField();jt1.setBounds(300, 50, 200, 50);jt1.setFont(f);jp1.add(jt1);
		JTextField jt2 = new JTextField();jt2.setBounds(300, 130, 200, 50);jt2.setFont(f);jp1.add(jt2);
		JTextField jt3 = new JTextField();jt3.setBounds(300, 210, 200, 50);jt3.setFont(f);jp1.add(jt3);
		JTextField jt4 = new JTextField();jt4.setBounds(300, 290, 200, 50);jt4.setFont(f);jp1.add(jt4);
		
		//按下确定键执行注册程序
		JButton jb3 = new JButton("确定");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(280,360,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registerName = jt1.getText();
				registerPw = jt2.getText();
				registerEmail = jt3.getText();
				registerTel = jt4.getText();
				//userId++;
				try {
				ResultSet rs = stmt.executeQuery("select * from user"); 
                //user 为你表的名称 
				while (rs.next()) {//检测原来表中有多少项数据，方便确定id的值 
					System.out.println(rs.getString("name"));
					userId++;
				} 
				String sql = "insert into user(id,name,pw,email,tel) values("+Integer.toString(userId)+",'"+registerName+"','"+registerPw+"','"+registerEmail+"','"+registerTel+"')";				
				stmt.executeUpdate(sql);//数据库执行添加语句
				System.out.println(sql);
				System.out.println(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//取消注册用户，直接关闭用户窗口
		JButton jb4 = new JButton("取消");
		jb4.setBackground(Color.LIGHT_GRAY);
		jb4.setBounds(420,360,100,50);
		jb4.setFont(f);
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd1.dispose();
			}			
		});
		jd1.add(jb3);
		jd1.add(jb4);
		jd1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
    //删除用户窗口
    class DeleteUserInfoDialog{
		//初始化弹出式对话框	
		JDialog jd1 = new JDialog(jf,"删除用户",true);
		DeleteUserInfoDialog(){
		//对话框基本属性
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//设置对话框内容
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("请输入要删除的用户的姓名");jl4.setBounds(250, 50, 300, 50);jl4.setFont(f);jp1.add(jl4);
		//删除信息输入
		JTextField jt1 = new JTextField();jt1.setBounds(300, 120, 200, 50);jt1.setFont(f);jp1.add(jt1);
		
		//按下确定键执行删除程序
		JButton jb3 = new JButton("确定");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(280,200,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
				ResultSet rs = stmt.executeQuery("select * from user"); 
                //user 为你表的名称
				String deleteName = jt1.getText();
				String deleteId = null;
				while (rs.next()) {//检测原来表中有多少项数据，方便确定id的值 
					if(rs.getString("name").equals(deleteName))
					deleteId = rs.getString("id");
				} 				
				String sql = "delete from user where id = " + deleteId;
				System.out.println(sql);
				stmt.executeUpdate(sql);//数据库执行添加语句
				//System.out.println(sql);
				//System.out.println(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//取消删除用户，直接关闭用户窗口
		JButton jb4 = new JButton("取消");
		jb4.setBackground(Color.LIGHT_GRAY);
		jb4.setBounds(420,200,100,50);
		jb4.setFont(f);
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd1.dispose();
			}			
		});
		jd1.add(jb3);
		jd1.add(jb4);
		jd1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
  //删除用户窗口
    class DeleteClassRoomInfoDialog{
		//初始化弹出式对话框	
		JDialog jd1 = new JDialog(jf,"删除用户",true);
		DeleteClassRoomInfoDialog(){
		//对话框基本属性
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//设置对话框内容
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("请输入要删除的教室的名字");jl4.setBounds(250, 50, 300, 50);jl4.setFont(f);jp1.add(jl4);
		//删除信息输入
		JTextField jt1 = new JTextField();jt1.setBounds(300, 120, 200, 50);jt1.setFont(f);jp1.add(jt1);
		
		//按下确定键执行删除程序
		JButton jb3 = new JButton("确定");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(280,200,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
				ResultSet rs = stmt.executeQuery("select * from rooms"); 
                //user 为你表的名称
				String deleteName = jt1.getText();
				String deleteId = null;
				while (rs.next()) {//检测原来表中有多少项数据，方便确定id的值 
					if(rs.getString("no").equals(deleteName))
					deleteId = rs.getString("id");
				} 				
				String sql = "delete from rooms where id = " + deleteId;
				System.out.println(sql);
				stmt.executeUpdate(sql);//数据库执行添加语句
				//System.out.println(sql);
				//System.out.println(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//取消删除用户，直接关闭用户窗口
		JButton jb4 = new JButton("取消");
		jb4.setBackground(Color.LIGHT_GRAY);
		jb4.setBounds(420,200,100,50);
		jb4.setFont(f);
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd1.dispose();
			}			
		});
		jd1.add(jb3);
		jd1.add(jb4);
		jd1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
  //添加教室窗口
    class AddClassRoomDialog{
		//初始化弹出式对话框	
		JDialog jd1 = new JDialog(jf,"添加教室",true);
		AddClassRoomDialog(){
		//对话框基本属性
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//设置对话框内容
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("名字");jl4.setBounds(220, 50, 100, 50);jl4.setFont(f);jp1.add(jl4);
		JLabel jl5 = new JLabel("位置");jl5.setBounds(220, 130, 100, 50);jl5.setFont(f);jp1.add(jl5);
		JLabel jl6 = new JLabel("容量");jl6.setBounds(220, 210, 100, 50);jl6.setFont(f);jp1.add(jl6);
		JLabel jl7 = new JLabel("备注");jl7.setBounds(220, 290, 100, 50);jl7.setFont(f);jp1.add(jl7);
		//教室信息输入
		JTextField jt1 = new JTextField();jt1.setBounds(300, 50, 200, 50);jt1.setFont(f);jp1.add(jt1);
		JTextField jt2 = new JTextField();jt2.setBounds(300, 130, 200, 50);jt2.setFont(f);jp1.add(jt2);
		JTextField jt3 = new JTextField();jt3.setBounds(300, 210, 200, 50);jt3.setFont(f);jp1.add(jt3);
		JTextField jt4 = new JTextField();jt4.setBounds(300, 290, 200, 50);jt4.setFont(f);jp1.add(jt4);
		
		//按下确定键执行添加程序
		JButton jb3 = new JButton("确定");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(280,360,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNo = jt1.getText();
				addLocation = jt2.getText();
				addCapacity = jt3.getText();
				addBusy = jt4.getText();
				//userId++;
				try {
				ResultSet rs = stmt.executeQuery("select * from rooms"); 
                //user 为你表的名称 
				while (rs.next()) {//检测原来表中有多少项数据，方便确定id的值 
					System.out.println(rs.getString("no"));
					classroomId++;
				} 
				String sql = "insert into rooms(id,no,location,capacity,busy) values("+Integer.toString(classroomId)+",'"+addNo+"','"+addLocation+"',"+addCapacity+",'"+addBusy+"')";				
				stmt.executeUpdate(sql);//数据库执行添加语句
				System.out.println(sql);
				System.out.println(classroomId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//取消添加教室，直接关闭用户窗口
		JButton jb4 = new JButton("取消");
		jb4.setBackground(Color.LIGHT_GRAY);
		jb4.setBounds(420,360,100,50);
		jb4.setFont(f);
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd1.dispose();
			}			
		});
		jd1.add(jb3);
		jd1.add(jb4);
		jd1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
    //借教室窗口
    class BorrowClassRoomInfoDialog{
		//初始化弹出式对话框	
		JDialog jd1 = new JDialog(jf,"借教室",true);
		BorrowClassRoomInfoDialog(){
		//对话框基本属性
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//设置对话框内容
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("请输入要借的教室的名字");jl4.setBounds(250, 50, 300, 50);jl4.setFont(f);jp1.add(jl4);
		//教室信息输入
		JTextField jt1 = new JTextField();jt1.setBounds(300, 120, 200, 50);jt1.setFont(f);jp1.add(jt1);
		
		//按下确定键执行修改程序
		JButton jb3 = new JButton("确定");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(280,200,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
				ResultSet rs = stmt.executeQuery("select * from rooms"); 
                //user 为你表的名称
				String borrowName = jt1.getText();
				String borrowId = null;
				while (rs.next()) {//检测原来表中有多少项数据，方便确定id的值 
					if(rs.getString("no").equals(borrowName))
						borrowId = rs.getString("id");
				} 				
				String sql = "update rooms set busy = "+"'非空闲'"+" where id = " + borrowId;
				System.out.println(sql);
				stmt.executeUpdate(sql);//数据库执行添加语句
				//System.out.println(sql);
				//System.out.println(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//取消修改信息，直接关闭用户窗口
		JButton jb4 = new JButton("取消");
		jb4.setBackground(Color.LIGHT_GRAY);
		jb4.setBounds(420,200,100,50);
		jb4.setFont(f);
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd1.dispose();
			}			
		});
		jd1.add(jb3);
		jd1.add(jb4);
		jd1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
  //还教室窗口
    class ReturnClassRoomInfoDialog{
		//初始化弹出式对话框	
		JDialog jd1 = new JDialog(jf,"还教室",true);
		ReturnClassRoomInfoDialog(){
		//对话框基本属性
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//设置对话框内容
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("请输入要还的教室的名字");jl4.setBounds(250, 50, 300, 50);jl4.setFont(f);jp1.add(jl4);
		//教室信息输入
		JTextField jt1 = new JTextField();jt1.setBounds(300, 120, 200, 50);jt1.setFont(f);jp1.add(jt1);
		
		//按下确定键执行修改程序
		JButton jb3 = new JButton("确定");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(280,200,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
				ResultSet rs = stmt.executeQuery("select * from rooms"); 
                //user 为你表的名称
				String returnName = jt1.getText();
				String returnId = null;
				while (rs.next()) {//检测原来表中有多少项数据，方便确定id的值 
					if(rs.getString("no").equals(returnName))
						returnId = rs.getString("id");
				} 				
				String sql = "update rooms set busy = "+"'空闲'"+" where id = " + returnId;
				System.out.println(sql);
				stmt.executeUpdate(sql);//数据库执行添加语句
				//System.out.println(sql);
				//System.out.println(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//取消修改信息，直接关闭用户窗口
		JButton jb4 = new JButton("取消");
		jb4.setBackground(Color.LIGHT_GRAY);
		jb4.setBounds(420,200,100,50);
		jb4.setFont(f);
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd1.dispose();
			}			
		});
		jd1.add(jb3);
		jd1.add(jb4);
		jd1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}   
}  


