package block;

import java.util.Date;

public class Block
{
	public String hash;
	public String prevHash;
	private String data;
	private long timeStamp;
	private int nonce;
	
	public Block(String data, String prevHash)
	{
		this.data = data;
		this.prevHash = prevHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String calculateHash() {
		String calculatehash = StringUtil.applySha256( 
				prevHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) +
				data 
				);
		return calculatehash;
	}
	
	public void mineBlock(int difficulty)
	{
		String target = new String(new char[difficulty]).replace('\0', '0'); 
		//Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}