package JavaClassDesign;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
public class DrawPanel { 
public static void main(String args[]){ 
draw d=new draw(); 

} 
} 
class draw extends JFrame 
{ 
		//整个框架所需要的构件
		private JPanel jp1,jp2,jp3;
		//private MyCanvas canvas;
		private Graphics2D g;
		//下拉选择列表
		private Choice ShapeChoice,SizeChoice,ColorChoice;
		//一些控件的名字
		private String controlName[] = {"画笔类型:","自由绘画","直线","圆","矩形","画笔大小:","画笔颜色:","椭圆","圆角矩形","圆弧线"};
		//可供选择的颜色
		private String ColorChoose[] = {"black","blue","cyan","darkGray","gray","green","lightGray",
										"magenta","orange","pink","red","white","yellow"};
		private String BrushSize[] = {"1","2","3","4","5","6","7","8","9","10","15","20","30","40"};
		private JLabel jb1,jb2,jb3,jb4,jb5;//程序中使用到的文字提示标签
		private JButton ClearButton;//清除画板按钮
		private JButton FreeDrawButton,DrawLineButton,DrawCircleButton,
		DrawEllipseButton,DrawRectangleButton,DrawRoundRectButton,DrawArcButton,clearButton;//图形选择按钮
		Menu file,help;//菜单栏
		MenuItem exit,about;//菜单栏选项
		//从框架获取的数据
		public String DrawWhat = "FreeDraw";//绘制图形类型,默认自由绘制
		public Color CurrentColor = Color.BLACK;//画笔颜色，默认黑色
		public int X0,Y0,X1,Y1,X2,Y2;//按下鼠标的位置和松开鼠标的位置,鼠标当前位置
		public float brushSize = 1f;//画笔的大小,默认大小为1f
		
		
		
		
		private Font f = new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,30);//设置字体1
		private Font f1 = new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,20);//设置字体2
		private Font f2 = new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,16);//设置字体3
		
		class JDialog1{
			//初始化弹出式对话框	
			JDialog jd1 = new JDialog(draw.this,"about",true);
			JDialog1(){
			jd1.setBounds(1100,600,400,300);
			jd1.setVisible(false);
			jd1.setLayout(null);

			//设置第一个对话框内容
			JPanel jp1 = new JPanel();
			jp1.setBounds(0, 0, 400, 300);
			jp1.setLayout(null);		
			JLabel jl4 = new JLabel("          "
					+ "计科2班 2015111926 张根齐");
			jl4.setBounds(0, 0, 400, 150);
			jl4.setFont(f1);
			jp1.add(jl4);
			JButton jb3 = new JButton("确定");
			jb3.setBounds(150,150,100,50);
			jb3.setBackground(Color.LIGHT_GRAY);
			jb3.setFont(f1);
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
		public draw() {
			super("DrawPanel"); 
			this.show(); 
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE ); 
			//this.setTitle("DrawPanel");//程序的标题叫“DrawPanel”
			this.setBounds(500, 300, 1500, 920);//设置主程序窗口的位置和大小
			this.setResizable(false);//程序窗口不可改变大小
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//点击关闭后，退出程序
			jp1 = new JPanel();jp2 = new JPanel();jp3 = new JPanel();//初始化面板，面板一存放操作控件,面板二放画板,jp3放绘制图形选择按钮
			jp1.setBackground(Color.LIGHT_GRAY);//放操作按钮的面板背景色设为灰色
			jp2.setBackground(Color.white);//画板背景色为白色
			jp3.setBackground(Color.LIGHT_GRAY);//图形选择栏背景色为灰色
			//canvas = new MyCanvas();//初始化画板，作为绘图的容器
			//菜单栏设置
			MenuBar mb=new MenuBar();
			this.setMenuBar(mb);
			file = new Menu("File");help = new Menu("Help");
			mb.add(file);mb.add(help);
			exit = new MenuItem("Exit");about = new MenuItem("about");
			file.add(exit);help.add(about);
			file.setFont(f1);help.setFont(f1);
			exit.setFont(f1);about.setFont(f1);
			exit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//点击exit后，退出程序
					System.exit(0);
				}
				
			});
			about.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new JDialog1().jd1.setVisible(true);
				}
				
			});
			//关于操作栏的设置
			this.add(jp1);
			this.add(jp2);
			this.add(jp3);
			jp1.setBounds(0, 0, 1500, 50);//操作栏在整个窗口的最上方
			jp2.setBounds(100, 50, 1400, 810);//中间是绘图区，信息显示栏在整个窗口的最下方
			jp3.setBounds(0, 50, 100, 800);//图形选择栏在最左边
			jp1.setLayout(null);
			jp2.setLayout(null);
			jp3.setLayout(null);
			//初始化控件
			jb1 = new JLabel();jb2 = new JLabel();jb3 = new JLabel();ShapeChoice = new Choice();SizeChoice = new Choice();
			ColorChoice = new Choice();jb4 = new JLabel("正在自由绘制");jb5 = new JLabel("当前鼠标位置:"); ClearButton = new JButton("清除");
			FreeDrawButton = new JButton("自由绘制");DrawLineButton = new JButton("直线");DrawCircleButton = new JButton("圆");
			DrawEllipseButton = new JButton("椭圆");DrawRectangleButton = new JButton("矩形");
			DrawRoundRectButton = new JButton("圆角矩形");DrawArcButton = new JButton("圆弧");
			clearButton = new JButton("橡皮擦");
			//设置按钮的背景
			ClearButton.setBackground(Color.LIGHT_GRAY);clearButton.setBackground(Color.LIGHT_GRAY);
			FreeDrawButton.setBackground(Color.LIGHT_GRAY);DrawLineButton.setBackground(Color.LIGHT_GRAY);
			DrawCircleButton.setBackground(Color.LIGHT_GRAY);DrawEllipseButton.setBackground(Color.LIGHT_GRAY);
			DrawRectangleButton.setBackground(Color.LIGHT_GRAY);DrawRoundRectButton.setBackground(Color.LIGHT_GRAY);
			DrawArcButton.setBackground(Color.LIGHT_GRAY);
			//设置控件上的文本内容
			jb1.setText(controlName[0]);jb2.setText(controlName[5]);jb3.setText(controlName[6]);
			//设置下拉列表的内容
			/*ShapeChoice.addItem("实线");ShapeChoice.addItem("虚线");ShapeChoice.addItem("橡皮擦");*/
			ColorChoice.addItem(ColorChoose[0]);ColorChoice.addItem(ColorChoose[1]);ColorChoice.addItem(ColorChoose[2]);
			ColorChoice.addItem(ColorChoose[3]);ColorChoice.addItem(ColorChoose[4]);ColorChoice.addItem(ColorChoose[5]);
			ColorChoice.addItem(ColorChoose[6]);ColorChoice.addItem(ColorChoose[7]);ColorChoice.addItem(ColorChoose[8]);
			ColorChoice.addItem(ColorChoose[9]);ColorChoice.addItem(ColorChoose[10]);ColorChoice.addItem(ColorChoose[11]);
			ColorChoice.addItem(ColorChoose[12]);
			SizeChoice.addItem(BrushSize[0]);SizeChoice.addItem(BrushSize[1]);SizeChoice.addItem(BrushSize[2]);
			SizeChoice.addItem(BrushSize[3]);SizeChoice.addItem(BrushSize[4]);SizeChoice.addItem(BrushSize[5]);
			SizeChoice.addItem(BrushSize[6]);SizeChoice.addItem(BrushSize[7]);SizeChoice.addItem(BrushSize[8]);
			SizeChoice.addItem(BrushSize[9]);SizeChoice.addItem(BrushSize[10]);SizeChoice.addItem(BrushSize[11]);
			SizeChoice.addItem(BrushSize[12]);SizeChoice.addItem(BrushSize[13]);
			//设置控件字体
			jb1.setFont(f);ShapeChoice.setFont(f);jb2.setFont(f);jb3.setFont(f);SizeChoice.setFont(f);ColorChoice.setFont(f);
			jb4.setFont(f1);jb5.setFont(f1);ClearButton.setFont(f);clearButton.setFont(f1);
			
			FreeDrawButton.setFont(f2);DrawLineButton.setFont(f1);DrawCircleButton.setFont(f1);
			DrawEllipseButton.setFont(f1);DrawRectangleButton.setFont(f1);DrawRoundRectButton.setFont(f2);
			DrawArcButton.setFont(f1);
			//设置控件位置
			/*jb1.setBounds(20, 0, 320, 50);ShapeChoice.setBounds(350, 0, 200, 50);*/jb2.setBounds(20,0,140,50);
			SizeChoice.setBounds(170, 0, 80, 50);jb3.setBounds(270, 0, 140, 50);ColorChoice.setBounds(420, 0, 150, 50);
			jb4.setBounds(20, 750, 680, 50);jb5.setBounds(720, 750, 680, 50);ClearButton.setBounds(590, 0, 100, 50);
			
			
			//图形选择按钮位置
			FreeDrawButton.setBounds(0, 50, 100, 50);DrawLineButton.setBounds(0, 100, 100, 50);
			DrawCircleButton.setBounds(0, 150, 100, 50);
			DrawEllipseButton.setBounds(0, 200, 100, 50);DrawRectangleButton.setBounds(0, 250, 100, 50);
			DrawRoundRectButton.setBounds(0, 300, 100, 50);DrawArcButton.setBounds(0, 350, 100, 50);
			clearButton.setBounds(0, 400, 100, 50);
			//添加控件
			/*jp1.add(jb1);jp1.add(ShapeChoice);*/jp1.add(jb2);jp1.add(jb3);jp1.add(SizeChoice);jp1.add(jb3);jp1.add(ColorChoice);
			jp2.add(jb4);jp2.add(jb5);jp1.add(ClearButton);
			jp3.add(FreeDrawButton);jp3.add(DrawCircleButton);jp3.add(DrawEllipseButton);jp3.add(DrawRectangleButton);
			jp3.add(DrawRoundRectButton);jp3.add(DrawArcButton);jp3.add(DrawLineButton);jp3.add(clearButton);
			//图形选择按钮注册监听器
			FreeDrawButton.addActionListener(new ActionListener() {//自由绘制

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DrawWhat = "FreeDraw";
					jb4.setText("正在自由绘制");
					FreeDrawButton.setBackground(Color.WHITE);//按下一个按钮之后，这个按钮背景色设置为白色，其他按钮背景色恢复为淡灰色
					DrawLineButton.setBackground(Color.LIGHT_GRAY);
					DrawCircleButton.setBackground(Color.LIGHT_GRAY);
					DrawRectangleButton.setBackground(Color.LIGHT_GRAY);
					DrawRoundRectButton.setBackground(Color.LIGHT_GRAY);
					DrawEllipseButton.setBackground(Color.LIGHT_GRAY);
					DrawArcButton.setBackground(Color.LIGHT_GRAY);	
					clearButton.setBackground(Color.LIGHT_GRAY);
				}
				
			});
			DrawLineButton.addActionListener(new ActionListener() {//直线

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DrawWhat = "DrawLine";
					jb4.setText("正在画直线");
					FreeDrawButton.setBackground(Color.LIGHT_GRAY);//按下一个按钮之后，这个按钮背景色设置为白色，其他按钮背景色恢复为淡灰色
					DrawLineButton.setBackground(Color.white);
					DrawCircleButton.setBackground(Color.LIGHT_GRAY);
					DrawRectangleButton.setBackground(Color.LIGHT_GRAY);
					DrawRoundRectButton.setBackground(Color.LIGHT_GRAY);
					DrawEllipseButton.setBackground(Color.LIGHT_GRAY);
					DrawArcButton.setBackground(Color.LIGHT_GRAY);	
					clearButton.setBackground(Color.LIGHT_GRAY);
				}
				
			});
			DrawCircleButton.addActionListener(new ActionListener() {//圆

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DrawWhat = "DrawCircle";
					jb4.setText("正在画圆");
					FreeDrawButton.setBackground(Color.LIGHT_GRAY);//按下一个按钮之后，这个按钮背景色设置为白色，其他按钮背景色恢复为淡灰色
					DrawLineButton.setBackground(Color.LIGHT_GRAY);
					DrawCircleButton.setBackground(Color.WHITE);
					DrawRectangleButton.setBackground(Color.LIGHT_GRAY);
					DrawRoundRectButton.setBackground(Color.LIGHT_GRAY);
					DrawEllipseButton.setBackground(Color.LIGHT_GRAY);
					DrawArcButton.setBackground(Color.LIGHT_GRAY);	
					clearButton.setBackground(Color.LIGHT_GRAY);
				}
				
			});
			DrawRectangleButton.addActionListener(new ActionListener() {//矩形

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DrawWhat = "DrawRectangle";
					jb4.setText("正在画矩形");
					FreeDrawButton.setBackground(Color.LIGHT_GRAY);//按下一个按钮之后，这个按钮背景色设置为白色，其他按钮背景色恢复为淡灰色
					DrawLineButton.setBackground(Color.LIGHT_GRAY);
					DrawCircleButton.setBackground(Color.LIGHT_GRAY);
					DrawRectangleButton.setBackground(Color.WHITE);
					DrawRoundRectButton.setBackground(Color.LIGHT_GRAY);
					DrawEllipseButton.setBackground(Color.LIGHT_GRAY);
					DrawArcButton.setBackground(Color.LIGHT_GRAY);
					clearButton.setBackground(Color.LIGHT_GRAY);
				}
				
			});
			DrawRoundRectButton.addActionListener(new ActionListener() {//圆角矩形

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DrawWhat = "DrawRoundRect";
					jb4.setText("正在画圆角矩形");
					FreeDrawButton.setBackground(Color.LIGHT_GRAY);//按下一个按钮之后，这个按钮背景色设置为白色，其他按钮背景色恢复为淡灰色
					DrawLineButton.setBackground(Color.LIGHT_GRAY);
					DrawCircleButton.setBackground(Color.LIGHT_GRAY);
					DrawRectangleButton.setBackground(Color.LIGHT_GRAY);
					DrawRoundRectButton.setBackground(Color.WHITE);
					DrawEllipseButton.setBackground(Color.LIGHT_GRAY);
					DrawArcButton.setBackground(Color.LIGHT_GRAY);	
					clearButton.setBackground(Color.LIGHT_GRAY);
				}
				
			});
			DrawEllipseButton.addActionListener(new ActionListener() {//椭圆

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DrawWhat = "DrawEllipse";
					jb4.setText("正在画椭圆");
					FreeDrawButton.setBackground(Color.LIGHT_GRAY);//按下一个按钮之后，这个按钮背景色设置为白色，其他按钮背景色恢复为淡灰色
					DrawLineButton.setBackground(Color.LIGHT_GRAY);
					DrawCircleButton.setBackground(Color.LIGHT_GRAY);
					DrawRectangleButton.setBackground(Color.LIGHT_GRAY);
					DrawRoundRectButton.setBackground(Color.LIGHT_GRAY);
					DrawEllipseButton.setBackground(Color.WHITE);
					DrawArcButton.setBackground(Color.LIGHT_GRAY);
					clearButton.setBackground(Color.LIGHT_GRAY);
				}
				
			});
			DrawArcButton.addActionListener(new ActionListener() {//圆弧

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DrawWhat = "DrawArc";
					jb4.setText("正在画圆弧");
					FreeDrawButton.setBackground(Color.LIGHT_GRAY);//按下一个按钮之后，这个按钮背景色设置为白色，其他按钮背景色恢复为淡灰色
					DrawLineButton.setBackground(Color.LIGHT_GRAY);
					DrawCircleButton.setBackground(Color.LIGHT_GRAY);
					DrawRectangleButton.setBackground(Color.LIGHT_GRAY);
					DrawRoundRectButton.setBackground(Color.LIGHT_GRAY);
					DrawEllipseButton.setBackground(Color.LIGHT_GRAY);
					DrawArcButton.setBackground(Color.WHITE);	
					clearButton.setBackground(Color.LIGHT_GRAY);
				}
				
			});
			clearButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DrawWhat = "Clear";	
					jb4.setText("正在使用橡皮擦");
					FreeDrawButton.setBackground(Color.LIGHT_GRAY);//按下一个按钮之后，这个按钮背景色设置为白色，其他按钮背景色恢复为淡灰色
					DrawLineButton.setBackground(Color.LIGHT_GRAY);
					DrawCircleButton.setBackground(Color.LIGHT_GRAY);
					DrawRectangleButton.setBackground(Color.LIGHT_GRAY);
					DrawRoundRectButton.setBackground(Color.LIGHT_GRAY);
					DrawEllipseButton.setBackground(Color.LIGHT_GRAY);
					DrawArcButton.setBackground(Color.LIGHT_GRAY);	
					clearButton.setBackground(Color.white);
				}
				
			});
			
			SizeChoice.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// TODO Auto-generated method stub
					if(SizeChoice.getSelectedIndex() == 0) brushSize = 1f;
					else if(SizeChoice.getSelectedIndex() == 1) brushSize = 2f;
					else if(SizeChoice.getSelectedIndex() == 2) brushSize = 3f;
					else if(SizeChoice.getSelectedIndex() == 3) brushSize = 4f;
					else if(SizeChoice.getSelectedIndex() == 4) brushSize = 5f;
					else if(SizeChoice.getSelectedIndex() == 5) brushSize = 6f;
					else if(SizeChoice.getSelectedIndex() == 6) brushSize = 7f;
					else if(SizeChoice.getSelectedIndex() == 7) brushSize = 8f;
					else if(SizeChoice.getSelectedIndex() == 8) brushSize = 9f;
					else if(SizeChoice.getSelectedIndex() == 9) brushSize = 10f;
					else if(SizeChoice.getSelectedIndex() == 10) brushSize = 15f;
					else if(SizeChoice.getSelectedIndex() == 11) brushSize = 20f;
					else if(SizeChoice.getSelectedIndex() == 12) brushSize = 30f;
					else if(SizeChoice.getSelectedIndex() == 13) brushSize = 40f;
					
				}
				
			});

			/*//画笔选择注册监听器，设置当前绘制的图形
			ShapeChoice.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if(ShapeChoice.getSelectedIndex() == 0) {
						dash = new BasicStroke(brushSize);
					}
					else if(ShapeChoice.getSelectedIndex() == 1) {
						dash = new BasicStroke(brushSize, BasicStroke.CAP_BUTT,
				                BasicStroke.JOIN_ROUND,brushSize, new float[] { 15, 10, },
				                0f);			
					}
					else if(ShapeChoice.getSelectedIndex() == 2) {
						CurrentColor = Color.WHITE;
					}
					
					
				}
				
			});*/
			//画笔颜色选择注册监听器，设置当前画笔颜色
			ColorChoice.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if(ColorChoice.getSelectedIndex() == 0) CurrentColor = Color.black;//黑色
					else if(ColorChoice.getSelectedIndex() == 1) CurrentColor = Color.blue;//蓝色
					else if(ColorChoice.getSelectedIndex() == 2) CurrentColor = Color.cyan;//青色
					else if(ColorChoice.getSelectedIndex() == 3) CurrentColor = Color.darkGray;//深灰色
					else if(ColorChoice.getSelectedIndex() == 4) CurrentColor = Color.gray;//灰色
					else if(ColorChoice.getSelectedIndex() == 5) CurrentColor = Color.green;//绿色
					else if(ColorChoice.getSelectedIndex() == 6) CurrentColor = Color.lightGray;//浅灰色
					else if(ColorChoice.getSelectedIndex() == 7) CurrentColor = Color.magenta;//晶红色
					else if(ColorChoice.getSelectedIndex() == 8) CurrentColor = Color.orange;//桔黄色
					else if(ColorChoice.getSelectedIndex() == 9) CurrentColor = Color.pink;//粉红色
					else if(ColorChoice.getSelectedIndex() == 10) CurrentColor = Color.red;//红色
					else if(ColorChoice.getSelectedIndex() == 11) CurrentColor = Color.white;//白色
					else if(ColorChoice.getSelectedIndex() == 12) CurrentColor = Color.yellow;//黄色
					System.out.println(CurrentColor.toString());
					
				}
				
			});
			
			ClearButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					g.setColor(Color.WHITE); 
					g.fillRect(100,100,1500,770); 
				}

				
				
			});
			//设置一个绘画区
			g=(Graphics2D) this.getGraphics(); 
			g.setClip(110,120,1500,750); 
			/*//g.clipRect(0,100,1500,850);//设置不了画板背景色
			g.setColor(Color.WHITE);
			g.fillRect(0,0,1500,750);*/
			addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void mousePressed(MouseEvent e) {//鼠标按下去的时候获取开始的鼠标坐标  
					X0=e.getX(); 
					Y0=e.getY(); 
				} 
				public void mouseReleased(MouseEvent e) {//松开的时候获取结束坐标，根据两个坐标的关系绘制不同的图形 
					X1 = e.getX();Y1 = e.getY();
					g.setStroke(new BasicStroke(brushSize));
					g.setColor(CurrentColor ); 
					
					if(DrawWhat == "DrawLine") {//画直线
						g.drawLine(X0,Y0,X1,Y1); 
					}
					else if(DrawWhat == "DrawCircle") {//画圆
						g.drawOval(X0, Y0, X1-X0, X1-X0);
					}
					else if(DrawWhat == "DrawRectangle") {//画矩形
						g.drawRect(X0, Y0, X1-X0, Y1-Y0);
					}
					else if(DrawWhat == "DrawEllipse") {//画椭圆
						g.drawOval(X0, Y0, X1-X0, Y1-Y0);
					}
					else if(DrawWhat == "DrawRoundRect") {//画圆角矩形
						g.drawRoundRect(X0, Y0, X1-X0, Y1-Y0,40,25);
					}
					else if(DrawWhat == "DrawArc") {//画圆弧线
						g.drawArc(X0, Y0, X1-X0, Y1-Y0,0,180);
					}
				} 
				
			});  
			addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e) {//鼠标拖动的时候一直在获取鼠标的坐标
					// TODO Auto-generated method stub
					X1 = e.getX();Y1 = e.getY();
					jb5.setText("当前鼠标位置:"+X1+" "+Y1);//鼠标在拖动过程中显示位置
					g.setStroke(new BasicStroke(brushSize));
					g.setColor(CurrentColor); 
					if(DrawWhat == "FreeDraw") {//一直绘制当前点，结果就是鼠标拖到哪里就画到哪里
						g.setColor(CurrentColor); 
						g.drawLine(X0,Y0,X1,Y1); 
						X0=e.getX(); 
						Y0=e.getY(); 						
					}
					else if(DrawWhat == "Clear") {//擦除其实和自由绘制差不多的
						g.setColor(Color.white); 
						g.drawLine(X0,Y0,X1,Y1); 
						X0=e.getX(); 
						Y0=e.getY(); 						
					}
					/*
					 * 本来想让图形一边画一边显示的，但是这个方法会把原来绘图区上已经有了的图形擦掉
					 */
					/*else if(DrawWhat == "DrawRectangle") {//画矩形
						g.drawRect(X0, Y0, X1-X0, Y1-Y0);
						g.setColor(Color.white);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block   
							e1.printStackTrace();
						}
						g.drawRect(X0, Y0, X1-X0, Y1-Y0);
					}*/
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub
					X2 = e.getX();Y2 = e.getY();
					jb5.setText("当前鼠标位置:"+X2+" "+Y2);//鼠标在移动过程中显示位置
				}
				
			});
		}
}
// 

