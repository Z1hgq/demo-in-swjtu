package JavaClassDesign2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class GUIDesign
{  
    JPanel panel; 
    /*
     *���õ�¼����
     */
    JFrame jf;
    JLabel nameLabel,pwLabel;
    JTextField nameTextField;
    JPasswordField pwTextField;
    JButton loginButton,registerButton;  
     
    Statement stmt;
    String registerName,registerPw,registerTel,registerEmail;//�û�ע�����Ϣ
    String addNo,addLocation,addCapacity,addBusy;
    String currentName,currentPw;//��ǰ�������������
    Font f = new Font("΢���ź�",Font.LAYOUT_NO_LIMIT_CONTEXT,25);	
    int userId = 1;
    int classroomId = 1;
    GUIDesign(){  
        init();
        jf.setBounds(800,500,800,500);  
        jf.setVisible(true);  
        jf.setResizable(false);//���򴰿ڲ��ɸı��С
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }  
    public void init(){
    	/*
    	 * �������ݿ�
    	 */
    	 try { 
      	   Class.forName("com.mysql.jdbc.Driver");   //����MYSQL JDBC��������  
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
      	      //����URLΪ  jdbc:mysql//��������ַ/���ݿ��� �������2�������ֱ��ǵ�½�û��������� 
      	  
      	   System.out.println("Success connect Mysql server!"); 
      	   stmt = connect.createStatement(); 
      	  }
      	  catch (Exception e) { 
      		System.out.print("get data error!"); 
      		e.printStackTrace(); 
      	}
      	/*
      	 * �������� 
      	 */
      	jf = new JFrame("���ҽ��úͲ�ѯϵͳ");
        panel = new JPanel(); 
        panel.setBackground(Color.white);
        panel.setLayout(null);
        jf.add(panel);
        
        nameLabel = new JLabel("����");nameLabel.setFont(f);nameLabel.setBounds(230, 50, 100, 50);panel.add(nameLabel);
        pwLabel = new JLabel("����");pwLabel.setFont(f);pwLabel.setBounds(230, 150, 100, 50);panel.add(pwLabel);
        nameTextField = new JTextField();nameTextField.setFont(f);nameTextField.setBounds(310, 50, 200, 50);panel.add(nameTextField);
        pwTextField = new JPasswordField();pwTextField.setFont(f);pwTextField.setBounds(310, 150, 200, 50);panel.add(pwTextField);
        loginButton = new JButton("��¼");loginButton.setFont(f);loginButton.setBackground(Color.LIGHT_GRAY);loginButton.setBounds(280, 250, 100, 50);panel.add(loginButton);
        registerButton = new JButton("ע��");registerButton.setFont(f);registerButton.setBackground(Color.LIGHT_GRAY);registerButton.setBounds(420, 250, 100, 50);panel.add(registerButton);
        
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
					
					while ( login.next()) {//���ԭ�������ж��������ݣ�����ȷ��id��ֵ 
						if(login.getString("name").equals(currentName) && login.getString("pw").equals(currentPw))//��½�ɹ�
						{
							flag = true;
							panel.removeAll();//���������ԭ�е�����
							/*
							 * ���ò�������ϵ�����
							 */
							JButton queryUserInfoButton,queryClassRoomInfoButton,deleteUserInfoButton,deleteClassRoomInfoButton,addClassRoomInfoButton,
							BorrowClassRoomButton,returnClassRoomButton;
							JLabel UserInfoLabel,ClassRoomInfoLabel;
							JTextArea UserInfoArea,ClassRoomInfoArea;
							UserInfoLabel = new JLabel("�û���Ϣ");UserInfoLabel.setFont(f);UserInfoLabel.setBounds(20, 0, 100, 50);panel.add(UserInfoLabel);
							ClassRoomInfoLabel = new JLabel("������Ϣ");ClassRoomInfoLabel.setFont(f);ClassRoomInfoLabel.setBounds(20, 200, 100, 50);panel.add(ClassRoomInfoLabel);
							//�����û���Ϣ��ʾ��
							UserInfoArea = new JTextArea();
							UserInfoArea.setFont(f);
							UserInfoArea.setBackground(Color.cyan);
							//UserInfoArea.setBounds(20, 50, 500, 150);
							JScrollPane js=new JScrollPane(UserInfoArea);
							js.setBounds(20, 50, 500, 150);
							//�ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
							js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
							panel.add(js);
							//���ý�����Ϣ��ʾ��
							ClassRoomInfoArea = new JTextArea();
							ClassRoomInfoArea.setFont(f);
							ClassRoomInfoArea.setBackground(Color.cyan);
							//UserInfoArea.setBounds(20, 50, 500, 150);
							JScrollPane js1=new JScrollPane(ClassRoomInfoArea);
							js1.setBounds(20, 250, 500, 150);
							//�ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
							js1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							js1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
							panel.add(js1);
							//���ò��ɱ༭
							UserInfoArea.setEditable(false);
							ClassRoomInfoArea.setEditable(false);
							//������ť����
							queryUserInfoButton = new JButton("��ѯ�û���Ϣ");queryUserInfoButton.setFont(f);
							queryUserInfoButton.setBounds(550, 20,200, 50);queryUserInfoButton.setBackground(Color.LIGHT_GRAY);
							panel.add(queryUserInfoButton);
							queryClassRoomInfoButton= new JButton("��ѯ������Ϣ");queryClassRoomInfoButton.setFont(f);
							queryClassRoomInfoButton.setBounds(550, 80,200, 50);queryClassRoomInfoButton.setBackground(Color.LIGHT_GRAY);
							panel.add(queryClassRoomInfoButton);
							deleteUserInfoButton= new JButton("ɾ���û���Ϣ");deleteUserInfoButton.setFont(f);
							deleteUserInfoButton.setBounds(550, 140,200, 50);deleteUserInfoButton.setBackground(Color.LIGHT_GRAY);
							panel.add(deleteUserInfoButton);
							deleteClassRoomInfoButton= new JButton("ɾ��������Ϣ");deleteClassRoomInfoButton.setFont(f);
							deleteClassRoomInfoButton.setBounds(550, 200,200, 50);deleteClassRoomInfoButton.setBackground(Color.LIGHT_GRAY);
							panel.add(deleteClassRoomInfoButton);
							addClassRoomInfoButton= new JButton("��ӽ�����Ϣ");addClassRoomInfoButton.setFont(f);
							addClassRoomInfoButton.setBounds(550, 260,200, 50);addClassRoomInfoButton.setBackground(Color.LIGHT_GRAY);
							panel.add(addClassRoomInfoButton);
							BorrowClassRoomButton= new JButton("�����");BorrowClassRoomButton.setFont(f);
							BorrowClassRoomButton.setBounds(550, 320,200, 50);BorrowClassRoomButton.setBackground(Color.LIGHT_GRAY);
							panel.add(BorrowClassRoomButton);
							returnClassRoomButton= new JButton("������");returnClassRoomButton.setFont(f);
							returnClassRoomButton.setBounds(550, 380,200, 50);returnClassRoomButton.setBackground(Color.LIGHT_GRAY);
							panel.add(returnClassRoomButton);
							
							//���ò�����ť��Ӧ����
							//��ѯ�û���Ϣ
							queryUserInfoButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									UserInfoArea.setText("");
									try {
									ResultSet UserInfo = stmt.executeQuery("select * from user"); 
					                //user Ϊ�������� 
									while (UserInfo.next()) {//���ԭ�������ж��������ݣ�����ȷ��id��ֵ 
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
							//��ѯ������Ϣ
							queryClassRoomInfoButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									ClassRoomInfoArea.setText("");
									try {
										ResultSet ClassRoomInfo = stmt.executeQuery("select * from rooms"); 
						                //user Ϊ�������� 
										while (ClassRoomInfo.next()) {//���ԭ�������ж��������ݣ�����ȷ��id��ֵ 
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
							//ɾ���û���Ϣ
							deleteUserInfoButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									new DeleteUserInfoDialog().jd1.setVisible(true);
								}					
							});
							//ɾ��������Ϣ
							deleteClassRoomInfoButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									new DeleteClassRoomInfoDialog().jd1.setVisible(true);
								}					
							});
							//��ӽ�����Ϣ
							addClassRoomInfoButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									new AddClassRoomDialog().jd1.setVisible(true);
								}					
							});
							//�����
							BorrowClassRoomButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									new BorrowClassRoomInfoDialog().jd1.setVisible(true);
								}					
							});
							//������
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
						
					    //����������û�ҵ��û��Ļ�˵��û�ҵ�
						}
					if(!flag) new UserNotRegisterDialog().jd1.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				panel.validate();
				jf.repaint();//�ػ�
			} 	
        });
    }
    //û����������ʾ����
    class LackOfPwDialog{
		//��ʼ������ʽ�Ի���	
		JDialog jd1 = new JDialog(jf,"��ʾ��Ϣ",true);
		LackOfPwDialog(){
		jd1.setBounds(1000,600,400,300);
		jd1.setVisible(false);
		jd1.setLayout(null);
		//���öԻ�������
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 400, 300);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);		
		JLabel jl4 = new JLabel("���������룡");
		jl4.setBounds(130, 0, 300, 150);
		jl4.setFont(f);
		jp1.add(jl4);
		JButton jb3 = new JButton("ȷ��");
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
    //�û�δע����ʾ����
    class UserNotRegisterDialog{
		//��ʼ������ʽ�Ի���	
		JDialog jd1 = new JDialog(jf,"��ʾ��Ϣ",true);
		UserNotRegisterDialog(){
		jd1.setBounds(1000,600,400,300);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//���öԻ�������
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 400, 300);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);		
		JLabel jl4 = new JLabel("���û���δע�ᣡ");
		jl4.setBounds(100, 0, 300, 150);
		jl4.setFont(f);
		jp1.add(jl4);
		JButton jb3 = new JButton("ȷ��");
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
    //���������ʾ����
    class IncorrectPwDialog{
		//��ʼ������ʽ�Ի���	
		JDialog jd1 = new JDialog(jf,"��ʾ��Ϣ",true);
		IncorrectPwDialog(){
		jd1.setBounds(1000,600,400,300);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//���öԻ�������
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 400, 300);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);		
		JLabel jl4 = new JLabel("�������");
		jl4.setBounds(140, 0, 300, 150);
		jl4.setFont(f);
		jp1.add(jl4);
		JButton jb3 = new JButton("ȷ��");
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
    //�û�ע�ᴰ��
    class RegisterDialog{
		//��ʼ������ʽ�Ի���	
		JDialog jd1 = new JDialog(jf,"�û�ע��",true);
		RegisterDialog(){
		//�Ի����������
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//���öԻ�������
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("����");jl4.setBounds(220, 50, 100, 50);jl4.setFont(f);jp1.add(jl4);
		JLabel jl5 = new JLabel("����");jl5.setBounds(220, 130, 100, 50);jl5.setFont(f);jp1.add(jl5);
		JLabel jl6 = new JLabel("����");jl6.setBounds(220, 210, 100, 50);jl6.setFont(f);jp1.add(jl6);
		JLabel jl7 = new JLabel("�绰");jl7.setBounds(220, 290, 100, 50);jl7.setFont(f);jp1.add(jl7);
		//ע����Ϣ����
		JTextField jt1 = new JTextField();jt1.setBounds(300, 50, 200, 50);jt1.setFont(f);jp1.add(jt1);
		JTextField jt2 = new JTextField();jt2.setBounds(300, 130, 200, 50);jt2.setFont(f);jp1.add(jt2);
		JTextField jt3 = new JTextField();jt3.setBounds(300, 210, 200, 50);jt3.setFont(f);jp1.add(jt3);
		JTextField jt4 = new JTextField();jt4.setBounds(300, 290, 200, 50);jt4.setFont(f);jp1.add(jt4);
		
		//����ȷ����ִ��ע�����
		JButton jb3 = new JButton("ȷ��");
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
                //user Ϊ�������� 
				while (rs.next()) {//���ԭ�������ж��������ݣ�����ȷ��id��ֵ 
					System.out.println(rs.getString("name"));
					userId++;
				} 
				String sql = "insert into user(id,name,pw,email,tel) values("+Integer.toString(userId)+",'"+registerName+"','"+registerPw+"','"+registerEmail+"','"+registerTel+"')";				
				stmt.executeUpdate(sql);//���ݿ�ִ��������
				System.out.println(sql);
				System.out.println(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//ȡ��ע���û���ֱ�ӹر��û�����
		JButton jb4 = new JButton("ȡ��");
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
    //ɾ���û�����
    class DeleteUserInfoDialog{
		//��ʼ������ʽ�Ի���	
		JDialog jd1 = new JDialog(jf,"ɾ���û�",true);
		DeleteUserInfoDialog(){
		//�Ի����������
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//���öԻ�������
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("������Ҫɾ�����û�������");jl4.setBounds(250, 50, 300, 50);jl4.setFont(f);jp1.add(jl4);
		//ɾ����Ϣ����
		JTextField jt1 = new JTextField();jt1.setBounds(300, 120, 200, 50);jt1.setFont(f);jp1.add(jt1);
		
		//����ȷ����ִ��ɾ������
		JButton jb3 = new JButton("ȷ��");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(280,200,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
				ResultSet rs = stmt.executeQuery("select * from user"); 
                //user Ϊ��������
				String deleteName = jt1.getText();
				String deleteId = null;
				while (rs.next()) {//���ԭ�������ж��������ݣ�����ȷ��id��ֵ 
					if(rs.getString("name").equals(deleteName))
					deleteId = rs.getString("id");
				} 				
				String sql = "delete from user where id = " + deleteId;
				System.out.println(sql);
				stmt.executeUpdate(sql);//���ݿ�ִ��������
				//System.out.println(sql);
				//System.out.println(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//ȡ��ɾ���û���ֱ�ӹر��û�����
		JButton jb4 = new JButton("ȡ��");
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
  //ɾ���û�����
    class DeleteClassRoomInfoDialog{
		//��ʼ������ʽ�Ի���	
		JDialog jd1 = new JDialog(jf,"ɾ���û�",true);
		DeleteClassRoomInfoDialog(){
		//�Ի����������
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//���öԻ�������
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("������Ҫɾ���Ľ��ҵ�����");jl4.setBounds(250, 50, 300, 50);jl4.setFont(f);jp1.add(jl4);
		//ɾ����Ϣ����
		JTextField jt1 = new JTextField();jt1.setBounds(300, 120, 200, 50);jt1.setFont(f);jp1.add(jt1);
		
		//����ȷ����ִ��ɾ������
		JButton jb3 = new JButton("ȷ��");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(280,200,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
				ResultSet rs = stmt.executeQuery("select * from rooms"); 
                //user Ϊ��������
				String deleteName = jt1.getText();
				String deleteId = null;
				while (rs.next()) {//���ԭ�������ж��������ݣ�����ȷ��id��ֵ 
					if(rs.getString("no").equals(deleteName))
					deleteId = rs.getString("id");
				} 				
				String sql = "delete from rooms where id = " + deleteId;
				System.out.println(sql);
				stmt.executeUpdate(sql);//���ݿ�ִ��������
				//System.out.println(sql);
				//System.out.println(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//ȡ��ɾ���û���ֱ�ӹر��û�����
		JButton jb4 = new JButton("ȡ��");
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
  //��ӽ��Ҵ���
    class AddClassRoomDialog{
		//��ʼ������ʽ�Ի���	
		JDialog jd1 = new JDialog(jf,"��ӽ���",true);
		AddClassRoomDialog(){
		//�Ի����������
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//���öԻ�������
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("����");jl4.setBounds(220, 50, 100, 50);jl4.setFont(f);jp1.add(jl4);
		JLabel jl5 = new JLabel("λ��");jl5.setBounds(220, 130, 100, 50);jl5.setFont(f);jp1.add(jl5);
		JLabel jl6 = new JLabel("����");jl6.setBounds(220, 210, 100, 50);jl6.setFont(f);jp1.add(jl6);
		JLabel jl7 = new JLabel("��ע");jl7.setBounds(220, 290, 100, 50);jl7.setFont(f);jp1.add(jl7);
		//������Ϣ����
		JTextField jt1 = new JTextField();jt1.setBounds(300, 50, 200, 50);jt1.setFont(f);jp1.add(jt1);
		JTextField jt2 = new JTextField();jt2.setBounds(300, 130, 200, 50);jt2.setFont(f);jp1.add(jt2);
		JTextField jt3 = new JTextField();jt3.setBounds(300, 210, 200, 50);jt3.setFont(f);jp1.add(jt3);
		JTextField jt4 = new JTextField();jt4.setBounds(300, 290, 200, 50);jt4.setFont(f);jp1.add(jt4);
		
		//����ȷ����ִ����ӳ���
		JButton jb3 = new JButton("ȷ��");
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
                //user Ϊ�������� 
				while (rs.next()) {//���ԭ�������ж��������ݣ�����ȷ��id��ֵ 
					System.out.println(rs.getString("no"));
					classroomId++;
				} 
				String sql = "insert into rooms(id,no,location,capacity,busy) values("+Integer.toString(classroomId)+",'"+addNo+"','"+addLocation+"',"+addCapacity+",'"+addBusy+"')";				
				stmt.executeUpdate(sql);//���ݿ�ִ��������
				System.out.println(sql);
				System.out.println(classroomId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//ȡ����ӽ��ң�ֱ�ӹر��û�����
		JButton jb4 = new JButton("ȡ��");
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
    //����Ҵ���
    class BorrowClassRoomInfoDialog{
		//��ʼ������ʽ�Ի���	
		JDialog jd1 = new JDialog(jf,"�����",true);
		BorrowClassRoomInfoDialog(){
		//�Ի����������
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//���öԻ�������
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("������Ҫ��Ľ��ҵ�����");jl4.setBounds(250, 50, 300, 50);jl4.setFont(f);jp1.add(jl4);
		//������Ϣ����
		JTextField jt1 = new JTextField();jt1.setBounds(300, 120, 200, 50);jt1.setFont(f);jp1.add(jt1);
		
		//����ȷ����ִ���޸ĳ���
		JButton jb3 = new JButton("ȷ��");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(280,200,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
				ResultSet rs = stmt.executeQuery("select * from rooms"); 
                //user Ϊ��������
				String borrowName = jt1.getText();
				String borrowId = null;
				while (rs.next()) {//���ԭ�������ж��������ݣ�����ȷ��id��ֵ 
					if(rs.getString("no").equals(borrowName))
						borrowId = rs.getString("id");
				} 				
				String sql = "update rooms set busy = "+"'�ǿ���'"+" where id = " + borrowId;
				System.out.println(sql);
				stmt.executeUpdate(sql);//���ݿ�ִ��������
				//System.out.println(sql);
				//System.out.println(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//ȡ���޸���Ϣ��ֱ�ӹر��û�����
		JButton jb4 = new JButton("ȡ��");
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
  //�����Ҵ���
    class ReturnClassRoomInfoDialog{
		//��ʼ������ʽ�Ի���	
		JDialog jd1 = new JDialog(jf,"������",true);
		ReturnClassRoomInfoDialog(){
		//�Ի����������
		jd1.setBounds(800,500,800,500);
		jd1.setVisible(false);
		jd1.setLayout(null);
		
		//���öԻ�������
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 800, 500);
		jp1.setBackground(Color.white);
		jp1.setLayout(null);	
		jd1.add(jp1);
		JLabel jl4 = new JLabel("������Ҫ���Ľ��ҵ�����");jl4.setBounds(250, 50, 300, 50);jl4.setFont(f);jp1.add(jl4);
		//������Ϣ����
		JTextField jt1 = new JTextField();jt1.setBounds(300, 120, 200, 50);jt1.setFont(f);jp1.add(jt1);
		
		//����ȷ����ִ���޸ĳ���
		JButton jb3 = new JButton("ȷ��");
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setBounds(280,200,100,50);
		jb3.setFont(f);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
				ResultSet rs = stmt.executeQuery("select * from rooms"); 
                //user Ϊ��������
				String returnName = jt1.getText();
				String returnId = null;
				while (rs.next()) {//���ԭ�������ж��������ݣ�����ȷ��id��ֵ 
					if(rs.getString("no").equals(returnName))
						returnId = rs.getString("id");
				} 				
				String sql = "update rooms set busy = "+"'����'"+" where id = " + returnId;
				System.out.println(sql);
				stmt.executeUpdate(sql);//���ݿ�ִ��������
				//System.out.println(sql);
				//System.out.println(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jd1.dispose();
			}		
		});
		//ȡ���޸���Ϣ��ֱ�ӹر��û�����
		JButton jb4 = new JButton("ȡ��");
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


