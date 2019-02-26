class ATM {
	private int money;
	
	public ATM(int m){
		money = m;
	}
	
	// 입금
	// deposit => 입금자, 금액
	// ㅇㅇㅇ 가 0000원을 입금
	synchronized public void deposit(String name, int a){
		money += a;
		System.out.println(name+"님이 "+a+"원을 입금하였습니다.");
	}
	
	
	// 출금
	// withdraw -> 출금자, 금액
	// ㅇㅇㅇ가 0000원을 출금
	// 잔액이 있을 경우에만 출금, 없으면 메시지 출력
	synchronized public void withdraw(String name, int a){
		if((money-a) >= 0){
			// 출금이 가능하다면
			money -= a;
			System.out.println(name+"님이 "+a+"원을 출금하였습니다.");
		} else {
			System.out.println(name+"님이 "+money+"원 출금 시도 // 잔액이 부족합니다");
		}
	}
	
	// 잔액 조회
	// 계좌 잔액은 00000원 입니다 메시지 출력
	public void bbalance(){
		System.out.println("남은 잔액은 "+money+"원 입니다.");
	}
}

// 사용자
class ATMUser extends Thread{
	
	boolean flag = false; // 입금(true), 출금(false)
	ATM atm;
	
	// 사용자의 이름 생성, 계좌정보를 저장함
	public ATMUser(String name, ATM atm){
		super(name);
		this.atm = atm;
	}

	@Override
	public void run() {
		for(int i=0;i<5;i++){
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag) {
				atm.deposit(getName(), ((int)(Math.random()*10+2))*100);
				atm.bbalance();
			} else {
				atm.withdraw(getName(), ((int)(Math.random()*10+2))*100);
				atm.bbalance();
			}
			
			flag = !flag;
		}
	}
	
}



public class Test03 {

	public static void main(String[] args) {
		/* 멀티 쓰레드 프로그램(대부분의 프로그램)은 하나의 자원(프로그램)을 여러 개의 쓰레드가 동시에 접근하여 사용 가능함
		 * = 자원 공유
		 * => 동시에 같은 자원에 접근할 수도 있다
		 */
		
		// 동기화 -> 임계 영역(Critical Section)을 생성하여 하나의 쓰레드만 작업을 처리할 수 있도록 만들어주는 공간(영역)
		// -> 하나의 쓰레드가 작동하는 동안에 다른 쓰레드들은 락으로 상태 변경하여 다음 동작을 수행하기 전까지 대기
		
		// 동기화 블럭(주로 메서드 안에서 만들어짐)
		// public void Method(){
		// 		synchronized(동기화 객체 or 클래스 명){
		//
		// 		}
		// }
		
		// 메서드를 통한 동기화
		// public synchronized void Method(){
		//		임계영역에서의 처리 구문
		// }
		
		ATM KB = new ATM(1000);
		
		ATMUser user1 = new ATMUser("가나다", KB);
		ATMUser user2 = new ATMUser("라마바", KB);
		ATMUser user3 = new ATMUser("사바하", KB);
		
		user1.start();
		user2.start();
		user3.start();
		

	}

}
