import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GUIClass extends Frame implements Runnable{
	int x=1;
	
	public GUIClass() {
		setBackground(new Color(0, 0, 0));
		setSize(400, 150);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose(); // 화면 프레임 종료
				System.exit(0); // 대상의 프로세스를 종료함
			}
			
		});
		
		
	} // GUIClass()

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 화면에 글자 그리기
			repaint(); // paint() 메서드를 명시적 호출
			x += 5;
		} // while(true)
	} // run()

	@Override
	public void paint(Graphics g) {
		Dimension d; // 프레임의 폭, 높이를 저장하는 클래스
		d = getSize();
		
		g.setColor(Color.yellow);
		g.drawString("SHANGHAI DRAGONS, THE FIRST VICTORY ON LEAGUES!", x, d.height /2);
		
		if(x > d.width){
			x=0;
		}
	}
	
	
	
} // Class

public class Test02 {

	public static void main(String[] args) {
		// GUI + 쓰레드
		
		// Frame 상속, Runnable 인터페이스 구현
		GUIClass gui = new GUIClass();
		
		Thread t = new Thread(gui);
		
		t.start();
	}

}
