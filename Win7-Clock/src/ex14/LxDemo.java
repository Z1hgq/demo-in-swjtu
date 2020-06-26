package ex14;



import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.applet.*;
public class LxDemo
extends Applet implements Runnable
{
	Thread timer = null;
	Label a,d,e,n;
	int lastxs=50, lastys=30, lastxm=50, lastym=30, lastxh=50, lastyh=30;
	Label b[] = new Label[]{new Label("日"),new Label("一"),new Label("二"),new Label("三"),new Label("四"),new Label("五"),new Label("六")};
	Label c[] = new Label[]{new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),
							new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),
							new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),
							new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),
							new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),
							new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label()};
	private Font f = new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,25);
	
	Button  forward = new Button("F"),backward = new Button("B");
	Date rightnow = new Date();//获取当前日期和时间
	int Year = rightnow.getYear()+1900,Month = rightnow.getMonth()+1;//当前年月
	public void init(){ //初始化方法
		setLayout(null);
		setBackground(Color.white); //设置Applet 背景
		//setSize(400,300);
		//this.setBounds(500, 500, 1500, 800);//没用
		a=new Label("");d=new Label("");
		add(a);e=new Label("");
		add(d);
		add(e);n=new Label("");add(n);
		forward.setBounds(1080, 50, 50, 30);
		backward.setBounds(860, 50, 50, 30);
		add(forward);add(backward);
		forward.addActionListener(new ActionListener() {//前进一个月

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(Month == 12) {
					Month = 1;Year++;
				}else {
					Month++;
				}
				for(int i = 0;i < c.length;i++) {//消除当前日期的颜色
					c[i].setBackground(Color.white);
				}
			}
			
		});
		backward.addActionListener(new ActionListener() {//后退一个月

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(Month == 1) {
					Month = 12;Year--;
				}else {
					Month--;
				}
				for(int i = 0;i < c.length;i++) {//消除当前日期的颜色
					c[i].setBackground(Color.white);
				}
			}
			
		});
		n.setBounds(920, 40, 150, 50);//设置日历上的时间
		d.setBounds(920, 580, 150, 50);//设置时间
		e.setBounds(920, 620, 150, 50);//设置日期
		a.setBounds(400, 10, 200, 50);//设置年月
		a.setFont(f);d.setFont(f);e.setFont(f);n.setFont(f);forward.setFont(f);backward.setFont(f);
		d.setBackground(Color.white);a.setBackground(Color.white);e.setBackground(Color.white);
		n.setBackground(Color.white);
		int x = 600;
		for(int i = 0;i < b.length;i++) {//设置日历上的日期
			b[i].setBounds(x, 100, 100, 50);
			x += 120;
			b[i].setBackground(Color.white);
			b[i].setFont(f);
			add(b[i]);
		}
		x = 600;int y = 170;
		for(int i = 0;i < c.length;i++)//设置日历上的号数
		{
			c[i].setBounds(x, y, 50, 50);
			c[i].setBackground(Color.white);
			c[i].setFont(f);
			add(c[i]);
			x += 120; 
			if((i+1)%7 == 0) {
				y += 70;x = 600;
			}
			
		}
		}
	public void paint(Graphics g)  //显示数字和图形时钟的方法
	{
		int xh, yh, xm, ym, xs, ys, s, m, h, xcenter, ycenter,r,year,month,day,date,currentMonthDay;
		double rad = Math.PI / 180;
		
		
		//Date rightnow = new Date(); //获取当前日期和时间
		//String today = rightnow.toLocaleString(); //时间对应的字符串
		//a.setText(today); //显示数字时钟
		s = rightnow.getSeconds();
		m = rightnow.getMinutes();
		h = rightnow.getHours();
		year = rightnow.getYear()+1900;
		month = rightnow.getMonth()+1;
		day = rightnow.getDay();
		date = rightnow.getDate();
		
		
		//设置日历上的年月时间日期
		String str1 = Integer.toString(year) + "年" + Integer.toString(month) + "月"+Integer.toString(date)+"日";
		a.setText(str1);
		String str2 = Integer.toString(h) + ":" + Integer.toString(m) + ":" +Integer.toString(s);
		d.setText(str2);
		String str3 = "星期" + toChinese(Integer.toString(day));
		e.setText(str3);
		
		//Year = year;Month = month;
		//System.out.println(Year);
		//System.out.println(Month);
		currentMonthDay = getDaysByYearMonth(Year-1900,Month);
		//System.out.println(currentMonthDay);
		String Day1 = Integer.toString(Year)+"-"+Integer.toString(Month)+"-"+Integer.toString(1);
		//System.out.println(Day1);
		String Day1week = getDayOfWeekByDate(Day1);//当前月的1号是星期几
		//System.out.println(Day1week);
		int Day1Week = 0;
		for(int i = 0;i < 7;i++) {
			if(Day1week.equals("星期"+b[i].getText()))
				Day1Week = i;
		}
		//System.out.println(day1week);
		int k = 1;
		for(int i = Day1Week;i < currentMonthDay+Day1Week;i++) {//填充上号数
			c[i].setText(Integer.toString(k));k++;
		}	
		for(int i = 0;i < Day1Week;i++) {
			c[i].setText("");
		}
		for(int i = currentMonthDay+Day1Week;i < c.length;i++) {
			c[i].setText("");
		}
		String str4 = Integer.toString(Year)+"年"+Integer.toString(Month)+"月";
		n.setText(str4);
		
		if(Year == year && Month == month) {
			for(int i = Day1Week;i < currentMonthDay;i++) {//给今天的号数改变背景色
				if(Integer.toString(date).equals(c[i].getText()))
					c[i].setBackground(Color.LIGHT_GRAY);
			}
		}
		
		
		//设置日历上的具体号数
		/*int CurrentMonthDay = getCurrentMonthDay();//当前月的天数
		String day1 = Integer.toString(year-1900)+"-"+Integer.toString(month)+"-"+Integer.toString(1);
		String day1week = getDayOfWeekByDate(day1);//当前月的1号是星期几
		int day1Week = 0;
		for(int i = 0;i < 7;i++) {
			if(day1week.equals("星期"+b[i].getText()))
				day1Week = i;
		}
		//System.out.println(day1week);
		int k = 1;
		for(int i = day1Week;i < CurrentMonthDay;i++) {//填充上号数
			c[i].setText(Integer.toString(k));k++;
		}		
		for(int i = day1Week;i < CurrentMonthDay;i++) {//给今天的号数改变背景色
			if(Integer.toString(date).equals(c[i].getText()))
				c[i].setBackground(Color.BLUE);
		}
		*/
		
		
		xcenter=300; ycenter=300; //图形钟的原点
		r = 200;
		//以下计算秒针、分针、时针位置
		xs = (int)(Math.cos(s * 3.14f/30 - 3.14f/2) * (r-30) + xcenter);
		ys = (int)(Math.sin(s * 3.14f/30 - 3.14f/2) * (r-30) + ycenter);
		xm = (int)(Math.cos(m * 3.14f/30 - 3.14f/2) * (r-60) + xcenter);
		ym = (int)(Math.sin(m * 3.14f/30 - 3.14f/2) * (r-60) + ycenter);
		xh = (int)(Math.cos((h*30+m/2)*3.14f/180-3.14f/2)*(r-100)+xcenter);
		yh = (int)(Math.sin((h*30+m/2)*3.14f/180-3.14f/2)*(r-100)+ycenter);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		//g.setColor(Color.orange); //设置表盘颜色
		g.drawOval(xcenter-r+1,ycenter-r+2,2*r,2*r); //画表盘
		/*g.setColor(Color.black); //设置表盘数字颜色
		g.drawString("9",xcenter-90,ycenter+5); //画表盘上的数字
		g.drawString("3",xcenter+80,ycenter+8);
		g.drawString("12",xcenter-12,ycenter-80);
		g.drawString("6",xcenter-7,ycenter+91);*/
		//时间变化时，需要重新画各个指针，即先消除原有指针，然后画新指针
		g.setColor(Color.white); //用表的填充色画线，可以消除原来画的线
		if (xs != lastxs || ys != lastys){ //秒针变化
		g.drawLine(xcenter, ycenter, lastxs, lastys); }
		if (xm != lastxm || ym != lastym) { //分针变化
		g.drawLine(xcenter, ycenter-1, lastxm, lastym);
		g.drawLine(xcenter-1, ycenter, lastxm, lastym); }
		if (xh != lastxh || yh != lastyh) { //时针变化
		g.drawLine(xcenter, ycenter-1, lastxh, lastyh);
		g.drawLine(xcenter-1, ycenter, lastxh, lastyh); }
		g.setColor(Color.red); //使用红色画新秒指针
		g.drawLine(xcenter, ycenter, xs, ys);
		g.setColor(Color.green); //使用绿色画新分指针
		g.drawLine(xcenter, ycenter-1, xm, ym);
		g.drawLine(xcenter-1, ycenter, xm, ym);
		g.setColor(Color.blue); //使用蓝色画新时指针
		g.drawLine(xcenter, ycenter-1, xh, yh);
		g.drawLine(xcenter-1, ycenter, xh, yh);
		//画表盘上的刻度
		// 画60个黑色的小圆圈，表示小刻度
		g.setColor(Color.BLACK);
		int d = 0;
		for (int i = 0; i < 60; i++) {
		int x1 = (int) ((r - 4) * Math.sin(rad * d));
		int y1 = (int) ((r - 4) * Math.cos(rad * d));
		//g.drawString(".", xcenter  + x1 - 1, xcenter  - y1 + 1 - 50);
		g.fillOval(xcenter  + x1 - 1, xcenter  - y1 + 1 , 4, 4);
		d += 6;
		}
		//画12个大一点的圆圈表示数字再得位置的刻度
		d = 30;
		for (int i = 1; i < 13; i++) {
			int x1 = (int) ((r - 4) * Math.sin(rad * d));
			int y1 = (int) ((r - 4) * Math.cos(rad * d));
			//g.drawString( Integer.toString(i), xcenter  + x1 - 6, xcenter  - y1 + 1 - 45);
			g.fillOval(xcenter  + x1 - 1, xcenter  - y1 + 1 , 8, 8);
			d += 30;
		}
		//画表盘上的十二个数字
		d = 30;
		for (int i = 1; i < 13; i++) {
			int x1 = (int) ((r - 20) * Math.sin(rad * d));
			int y1 = (int) ((r - 20) * Math.cos(rad * d));
			g.drawString( Integer.toString(i), xcenter  + x1 - 6, xcenter  - y1 + 1 +10);
			d += 30;
		}
		

		
		lastxs=xs; lastys=ys; //保存指针位置
		lastxm=xm; lastym=ym;
		lastxh=xh; lastyh=
				yh; }
	public void start() { //启动线程的方法
		if(timer == null) {
			timer = new Thread(this);
			timer.start(); 
		}
	}
	public void stop() //停止线程方法
	{
		timer = null; 
	}
	public void run(){ //每隔一秒钟，刷新一次画面的方法
		while (timer != null) 
		{
			try { Thread.sleep(1000); repaint();}
			catch (InterruptedException e) {}
			//repaint(); //调用paint()方法重画时钟
	    }
		timer = null; 
	}
	public void update(Graphics g) //重写该方法是为了消除抖动现象
	{
		paint(g); 
	}
	public String toChinese(String s) {//将阿拉伯数字的日期换成中文
		String[] s1 = {"一","二","三","四","五","六","日"};
		String[] s2 = {"1","2","3","4","5","6","7"};
		String s3 = null;
		for(int i = 0;i < 7;i++) 
			if(s.equals(s2[i]))
				s3 = s1[i];
		return s3;
		
	}
	/** 
     * 获取当月的 天数 
     * */  
    public int getCurrentMonthDay() {  
          
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
  
    /** 
     * 根据年 月 获取对应的月份 天数 
     * */  
    public int getDaysByYearMonth(int year, int month) {  
          
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
      
    /** 
     * 根据日期 找到对应日期的 星期 
     */  
    public String getDayOfWeekByDate(String date) {  
        String dayOfweek = "-1";  
        try {  
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
            Date myDate = myFormatter.parse(date);  
            SimpleDateFormat formatter = new SimpleDateFormat("E");  
            String str = formatter.format(myDate);  
            dayOfweek = str;  
              
        } catch (Exception e) {  
            System.out.println("错误!");  
        }  
        return dayOfweek;  
    }  
}
