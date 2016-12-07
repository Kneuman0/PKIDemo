package fun.personalAcademics.host;

import java.math.BigInteger;
import java.util.Random;

public class Client {
	
	private int privateKey;
	private BigInteger generator;
	private BigInteger mod;
	private Random rand;
	
	public Client(BigInteger generator, BigInteger mod){
		rand = new Random();
		privateKey = rand.nextInt(10000000);
		this.generator = generator;
		this.mod = mod;
				
	}
	
	public Packet getPacket(){
		// Raises the generator to the private key
		BigInteger leftSide = new BigInteger(generator.toByteArray()).pow(privateKey);
		/*
		 * creates a public key like so:
		 * (generator^privateKey) % modulus = public key
		 */
		BigInteger pubKey = leftSide.mod(mod);
		return new Packet(generator, mod, pubKey);		
	}
	
	public BigInteger getSharedEncryption(Packet packet){
		// raises the hosts public key to this private key
		BigInteger leftSide = packet.getPublicKey().pow(privateKey);
		/*
		 * Arrives at a shared secret value via:
		 * (hostPublicKey^thisPrivateKey) % modulus
		 */
		return leftSide.mod(mod);
	}
	
	
}
