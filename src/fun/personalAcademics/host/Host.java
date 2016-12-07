package fun.personalAcademics.host;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.commons.io.input.ReversedLinesFileReader;

public class Host {
	
	private int[] primes;
	private int privateKey;
	private BigInteger generator;
	private BigInteger mod;
	private Random rand;
	
	public Host(){
		rand = new Random();
		try(FileReader buffer = new FileReader(
				Host.class.getResource("/resources/primes.txt").toString().replace("file:/", ""));
				Scanner scanner = new Scanner(buffer);
				ReversedLinesFileReader reader = new ReversedLinesFileReader(new File(
						Host.class.getResource("/resources/primes.txt").toString().replace("file:/", "")),
						Charset.forName("UTF-8"))) {
			int volume = 0;
			int i = 0;
			while(reader.readLine() != null) volume++;
			privateKey = rand.nextInt(volume);
			primes = new int[volume];
			
			while(scanner.hasNext()){
				primes[i++] = scanner.nextInt();
			}
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// Generator and modulus are automatically co-prime because they are both prime (kind of cheating)
		generator = new BigInteger(Integer.toString(primes[rand.nextInt(primes.length)]), 10);
		mod = new BigInteger(Integer.toString(primes[rand.nextInt(primes.length)]), 10);
				
	}
	
	
	public Packet getPacket(){
		// raise the generator to the private key
		BigInteger leftSide = new BigInteger(generator.toByteArray()).pow(privateKey);
		// take the modulus like so: (generator^privateKey) % mod
		// this will create a public key
		BigInteger pubKey = leftSide.mod(mod);
		return new Packet(generator, mod, pubKey);		
	}
	
	public BigInteger getSharedEncryption(Packet packet){
		/* 
		 * raise the other clients public key to this private key
		 * like so: (clientPublicKey^PrivateKey) % modulus
		 */
		BigInteger leftSide = packet.getPublicKey().pow(privateKey);
		return leftSide.mod(mod);
	}

	/**
	 * @return the primes
	 */
	public int[] getPrimes() {
		return primes;
	}

	/**
	 * @param primes the primes to set
	 */
	public void setPrimes(int[] primes) {
		this.primes = primes;
	}

	/**
	 * @return the privateKey
	 */
	public int getPrivateKey() {
		return privateKey;
	}

	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(int privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * @return the generator
	 */
	public BigInteger getGenerator() {
		return generator;
	}

	/**
	 * @param generator the generator to set
	 */
	public void setGenerator(BigInteger generator) {
		this.generator = generator;
	}

	/**
	 * @return the mod
	 */
	public BigInteger getMod() {
		return mod;
	}

	/**
	 * @param mod the mod to set
	 */
	public void setMod(BigInteger mod) {
		this.mod = mod;
	}

	/**
	 * @return the rand
	 */
	public Random getRand() {
		return rand;
	}

	/**
	 * @param rand the rand to set
	 */
	public void setRand(Random rand) {
		this.rand = rand;
	}
}
