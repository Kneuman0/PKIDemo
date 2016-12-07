package fun.personalAcademics.host;

import java.math.BigInteger;

public class TestMe {

	public static void main(String[] args) {
		Host host = new Host();
//		testEncryption();
	}
	
	private static void testEncryption(){
		Host alice = new Host();
		Client bob = new Client(alice.getGenerator(), alice.getMod());
		
		BigInteger aliceEncry = alice.getSharedEncryption(bob.getPacket());
		BigInteger BobEncry = bob.getSharedEncryption(alice.getPacket());
		System.out.printf("%s\n%s", aliceEncry.toString(), BobEncry.toString());
	}
	

}
