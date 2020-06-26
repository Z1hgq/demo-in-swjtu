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
	Label b[] = new Label[]{new Label("��"),new Label("һ"),new Label("��"),new Label("��"),new Label("��"),new Label("��"),new Label("��")};
	Label c[] = new Label[]{new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),
							new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),
							new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),
							new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),
							new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),
							new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label()};
	private Font f = new Font("΢���ź�",Font.LAYOUT_NO_LIMIT_CONTEXT,25);
	
	Button  forward = new Button("F"),backward = new Button("B");
	Date rightnow = new Date();//��ȡ��ǰ���ں�ʱ��
	int Year = rightnow.getYear()+1900,Month = rightnow.getMonth()+1;//��ǰ����
	public void init(){ //��ʼ������
		setLayout(null);
		setBackground(Color.white); //����Applet ����
		//setSize(400,300);
		//this.setBounds(500, 500, 1500, 800);//û��
		a=new Label("");d=new Label("");
		add(a);e=new Label("");
		add(d);
		add(e);n=new Label("");add(n);
		forward.setBounds(1080, 50, 50, 30);
		backward.setBounds(860, 50, 50, 30);
		add(forward);add(backward);
		forward.addActionListener(new ActionListener() {//ǰ��һ����

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(Month == 12) {
					Month = 1;Year++;
				}else {
					Month++;
				}
				for(int i = 0;i < c.length;i++) {//������ǰ���ڵ���ɫ
					c[i].setBackground(Color.white);
				}
			}
			
		});
		backward.addActionListener(new ActionListener() {//����һ����

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(Month == 1) {
					Month = 12;Year--;
				}else {
					Month--;
				}
				for(int i = 0;i < c.length;i++) {//������ǰ���ڵ���ɫ
					c[i].setBackground(Color.white);
				}
			}
			
		});
		n.setBounds(920, 40, 150, 50);//���������ϵ�ʱ��
		d.setBounds(920, 580, 150, 50);//����ʱ��
		e.setBounds(920, 620, 150, 50);//��������
		a.setBounds(400, 10, 200, 50);//��������
		a.setFont(f);d.setFont(f);e.setFont(f);n.setFont(f);forward.setFont(f);backward.setFont(f);
		d.setBackground(Color.white);a.setBackground(Color.white);e.setBackground(Color.white);
		n.setBackground(Color.white);
		int x = 600;
		for(int i = 0;i < b.length;i++) {//���������ϵ�����
			b[i].setBounds(x, 100, 100, 50);
			x += 120;
			b[i].setBackground(Color.white);
			b[i].setFont(f);
			add(b[i]);
		}
		x = 600;int y = 170;
		for(int i = 0;i < c.length;i++)//���������ϵĺ���
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
	public void paint(Graphics g)  //��ʾ���ֺ�ͼ��ʱ�ӵķ���
	{
		int xh, yh, xm, ym, xs, ys, s, m, h, xcenter, ycenter,r,year,month,day,date,currentMonthDay;
		double rad = Math.PI / 180;
		
		
		//Date rightnow = new Date(); //��ȡ��ǰ���ں�ʱ��
		//String today = rightnow.toLocaleString(); //ʱ���Ӧ���ַ���
		//a.setText(today); //��ʾ����ʱ��
		s = rightnow.getSeconds();
		m = rightnow.getMinutes();
		h = rightnow.getHours();
		year = rightnow.getYear()+1900;
		month = rightnow.getMonth()+1;
		day = rightnow.getDay();
		date = rightnow.getDate();
		
		
		//���������ϵ�����ʱ������
		String str1 = Integer.toString(year) + "��" + Integer.toString(month) + "��"+Integer.toString(date)+"��";
		a.setText(str1);
		String str2 = Integer.toString(h) + ":" + Integer.toString(m) + ":" +Integer.toString(s);
		d.setText(str2);
		String str3 = "����" + toChinese(Integer.toString(day));
		e.setText(str3);
		
		//Year = year;Month = month;
		//System.out.println(Year);
		//System.out.println(Month);
		currentMonthDay = getDaysByYearMonth(Year-1900,Month);
		//System.out.println(currentMonthDay);
		String Day1 = Integer.toString(Year)+"-"+Integer.toString(Month)+"-"+Integer.toString(1);
		//System.out.println(Day1);
		String Day1week = getDayOfWeekByDate(Day1);//��ǰ�µ�1�������ڼ�
		//System.out.println(Day1week);
		int Day1Week = 0;
		for(int i = 0;i < 7;i++) {
			if(Day1week.equals("����"+b[i].getText()))
				Day1Week = i;
		}
		//System.out.println(day1week);
		int k = 1;
		for(int i = Day1Week;i < currentMonthDay+Day1Week;i++) {//����Ϻ���
			c[i].setText(Integer.toString(k));k++;
		}	
		for(int i = 0;i < Day1Week;i++) {
			c[i].setText("");
		}
		for(int i = currentMonthDay+Day1Week;i < c.length;i++) {
			c[i].setText("");
		}
		String str4 = Integer.toString(Year)+"��"+Integer.toString(Month)+"��";
		n.setText(str4);
		
		if(Year == year && Month == month) {
			for(int i = Day1Week;i < currentMonthDay;i++) {//������ĺ����ı䱳��ɫ
				if(Integer.toString(date).equals(c[i].getText()))
					c[i].setBackground(Color.LIGHT_GRAY);
			}
		}
		
		
		//���������ϵľ������
		/*int CurrentMonthDay = getCurrentMonthDay();//��ǰ�µ�����
		String day1 = Integer.toString(year-1900)+"-"+Integer.toString(month)+"-"+Integer.toString(1);
		String day1week = getDayOfWeekByDate(day1);//��ǰ�µ�1�������ڼ�
		int day1Week = 0;
		for(int i = 0;i < 7;i++) {
			if(day1week.equals("����"+b[i].getText()))
				day1Week = i;
		}
		//System.out.println(day1week);
		int k = 1;
		for(int i = day1Week;i < CurrentMonthDay;i++) {//����Ϻ���
			c[i].setText(Integer.toString(k));k++;
		}		
		for(int i = day1Week;i < CurrentMonthDay;i++) {//������ĺ����ı䱳��ɫ
			if(Integer.toString(date).equals(c[i].getText()))
				c[i].setBackground(Color.BLUE);
		}
		*/
		
		
		xcenter=300; ycenter=300; //ͼ���ӵ�ԭ��
		r = 200;
		//���¼������롢���롢ʱ��λ��
		xs = (int)(Math.cos(s * 3.14f/30 - 3.14f/2) * (r-30) + xcenter);
		ys = (int)(Math.sin(s * 3.14f/30 - 3.14f/2) * (r-30) + ycenter);
		xm = (int)(Math.cos(m * 3.14f/30 - 3.14f/2) * (r-60) + xcenter);
		ym = (int)(Math.sin(m * 3.14f/30 - 3.14f/2) * (r-60) + ycenter);
		xh = (int)(Math.cos((h*30+m/2)*3.14f/180-3.14f/2)*(r-100)+xcenter);
		yh = (int)(Math.sin((h*30+m/2)*3.14f/180-3.14f/2)*(r-100)+ycenter);
		g.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		//g.setColor(Color.orange); //���ñ�����ɫ
		g.drawOval(xcenter-r+1,ycenter-r+2,2*r,2*r); //������
		/*g.setColor(Color.black); //���ñ���������ɫ
		g.drawString("9",xcenter-90,ycenter+5); //�������ϵ�����
		g.drawString("3",xcenter+80,ycenter+8);
		g.drawString("12",xcenter-12,ycenter-80);
		g.drawString("6",xcenter-7,ycenter+91);*/
		//ʱ��仯ʱ����Ҫ���»�����ָ�룬��������ԭ��ָ�룬Ȼ����ָ��
		g.setColor(Color.white); //�ñ�����ɫ���ߣ���������ԭ��������
		if (xs != lastxs || ys != lastys){ //����仯
		g.drawLine(xcenter, ycenter, lastxs, lastys); }
		if (xm != lastxm || ym != lastym) { //����仯
		g.drawLine(xcenter, ycenter-1, lastxm, lastym);
		g.drawLine(xcenter-1, ycenter, lastxm, lastym); }
		if (xh != lastxh || yh != lastyh) { //ʱ��仯
		g.drawLine(xcenter, ycenter-1, lastxh, lastyh);
		g.drawLine(xcenter-1, ycenter, lastxh, lastyh); }
		g.setColor(Color.red); //ʹ�ú�ɫ������ָ��
		g.drawLine(xcenter, ycenter, xs, ys);
		g.setColor(Color.green); //ʹ����ɫ���·�ָ��
		g.drawLine(xcenter, ycenter-1, xm, ym);
		g.drawLine(xcenter-1, ycenter, xm, ym);
		g.setColor(Color.blue); //ʹ����ɫ����ʱָ��
		g.drawLine(xcenter, ycenter-1, xh, yh);
		g.drawLine(xcenter-1, ycenter, xh, yh);
		//�������ϵĿ̶�
		// ��60����ɫ��СԲȦ����ʾС�̶�
		g.setColor(Color.BLACK);
		int d = 0;
		for (int i = 0; i < 60; i++) {
		int x1 = (int) ((r - 4) * Math.sin(rad * d));
		int y1 = (int) ((r - 4) * Math.cos(rad * d));
		//g.drawString(".", xcenter  + x1 - 1, xcenter  - y1 + 1 - 50);
		g.fillOval(xcenter  + x1 - 1, xcenter  - y1 + 1 , 4, 4);
		d += 6;
		}
		//��12����һ���ԲȦ��ʾ�����ٵ�λ�õĿ̶�
		d = 30;
		for (int i = 1; i < 13; i++) {
			int x1 = (int) ((r - 4) * Math.sin(rad * d));
			int y1 = (int) ((r - 4) * Math.cos(rad * d));
			//g.drawString( Integer.toString(i), xcenter  + x1 - 6, xcenter  - y1 + 1 - 45);
			g.fillOval(xcenter  + x1 - 1, xcenter  - y1 + 1 , 8, 8);
			d += 30;
		}
		//�������ϵ�ʮ��������
		d = 30;
		for (int i = 1; i < 13; i++) {
			int x1 = (int) ((r - 20) * Math.sin(rad * d));
			int y1 = (int) ((r - 20) * Math.cos(rad * d));
			g.drawString( Integer.toString(i), xcenter  + x1 - 6, xcenter  - y1 + 1 +10);
			d += 30;
		}
		

		
		lastxs=xs; lastys=ys; //����ָ��λ��
		lastxm=xm; lastym=ym;
		lastxh=xh; lastyh=
				yh; }
	public void start() { //�����̵߳ķ���
		if(timer == null) {
			timer = new Thread(this);
			timer.start(); 
		}
	}
	public void stop() //ֹͣ�̷߳���
	{
		timer = null; 
	}
	public void run(){ //ÿ��һ���ӣ�ˢ��һ�λ���ķ���
		while (timer != null) 
		{
			try { Thread.sleep(1000); repaint();}
			catch (InterruptedException e) {}
			//repaint(); //����paint()�����ػ�ʱ��
	    }
		timer = null; 
	}
	public void update(Graphics g) //��д�÷�����Ϊ��������������
	{
		paint(g); 
	}
	public String toChinese(String s) {//�����������ֵ����ڻ�������
		String[] s1 = {"һ","��","��","��","��","��","��"};
		String[] s2 = {"1","2","3","4","5","6","7"};
		String s3 = null;
		for(int i = 0;i < 7;i++) 
			if(s.equals(s2[i]))
				s3 = s1[i];
		return s3;
		
	}
	/** 
     * ��ȡ���µ� ���� 
     * */  
    public int getCurrentMonthDay() {  
          
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
  
    /** 
     * ������ �� ��ȡ��Ӧ���·� ���� 
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
     * �������� �ҵ���Ӧ���ڵ� ���� 
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
            System.out.println("����!");  
        }  
        return dayOfweek;  
    }  
}
