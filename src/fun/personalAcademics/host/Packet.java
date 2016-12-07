package fun.personalAcademics.host;

import java.math.BigInteger;

public class Packet {
	
	private BigInteger generator;
	private BigInteger modulus;
	private BigInteger publicKey;
	
	public Packet(BigInteger gen, BigInteger mod, BigInteger pubKey){
		this.generator = gen;
		this.modulus = mod;
		this.publicKey = pubKey;
	}
	
	public Packet(){
		generator = new BigInteger(Integer.toString(0));
		modulus = new BigInteger(Integer.toString(0));
		publicKey = new BigInteger(Integer.toString(0));
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
	 * @return the modulus
	 */
	public BigInteger getModulus() {
		return modulus;
	}

	/**
	 * @param modulus the modulus to set
	 */
	public void setModulus(BigInteger modulus) {
		this.modulus = modulus;
	}

	/**
	 * @return the publicKey
	 */
	public BigInteger getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(BigInteger publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public String toString(){
		return String.format("Generator: %s\nModulus: %s\nPublicKey: %s", 
				generator.toString(), modulus.toString(), publicKey.toString());
	}

}
