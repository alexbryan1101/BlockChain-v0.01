
package blockchain;

import java.util.Date;


public class Block {
    //basic block class, limited to attributes immiediatly needed by the block
    public String hash;
    public String previousHash;
    private String data; //simple message to store in block
    private long timestamp; // as # of milliseconds since 1970
    private int nonce;
    
    //Constructor
    public Block(String data,String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        
        this.hash = calculateHash();
    }
    
    //methods
    public String calculateHash(){
        //calculate a hash by using all parts of the block
        //hash of previous block, timestamp and the data in the block
        //**See StringUtility class for details!
        String calculatedHash = StringUtility.applySha256(previousHash + Long.toString(timestamp) + data);
        return calculatedHash;
    }
    
    public void mineBlock(int difficulty){
        //The blocks must be "minable" given the difficulty and range, this serves
        //as another form of validation
        //difficulty has been set as a variable in the main class
        String target = new String(new char[difficulty]).replace('\0','0');
        while(!hash.substring(0, difficulty).equals(target))
        {
            nonce ++;
            hash = calculateHash();
        }
        //hash = calculateHas (see above)
        System.out.println("Block Mined!!!: " + hash);
    }
    
}
