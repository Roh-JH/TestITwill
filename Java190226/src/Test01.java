import java.util.Random;

class VoteThread implements Runnable {
	
	int targetNum = 100;
	int sum = 0;
	Random r = new Random();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		while(true){
			sum += r.nextInt(10); // 0~10 까지의 랜덤값
			sb.delete(0, sb.toString().length());
			
			if(sum >= targetNum){
				sum = 100;
				for(int i=0;i<sum;i++){
					sb.append('*');
				}
				System.out.println(Thread.currentThread().getName()+" 개표율 : "+sum+"\t: "+sb);
				break;
			}else{
				for(int i=0;i<sum;i++){
					sb.append('*');
				}
				System.out.println(Thread.currentThread().getName()+" 개표율 : "+sum+"%\t: "+sb);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}

public class Test01 {

	public static void main(String[] args) {
		// 개표방송 -> 개표율 표시
		VoteThread vt1 = new VoteThread();
		VoteThread vt2 = new VoteThread();
		VoteThread vt3 = new VoteThread();
		
		Thread t1 = new Thread(vt1, "서울");
		Thread t2 = new Thread(new VoteThread(), "부산");
		Thread t3 = new Thread(vt3, "광주");
		
		t1.start();
		t2.start();
		t3.start();
	}

}
