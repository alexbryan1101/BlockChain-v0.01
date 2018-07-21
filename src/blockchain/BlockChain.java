
package blockchain;

import com.google.gson.*;
import java.util.ArrayList;

public class BlockChain {
    
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 0;

    public static void main(String[] args) {
        
        //Add some blocks to the blockchain array list
        blockchain.add(new Block("First Block Data","0"));
        System.out.println("Trying to mine block 1...");
        blockchain.get(0).mineBlock(difficulty);
        
        blockchain.add(new Block("Second Block Data",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine block 2...");
        blockchain.get(1).mineBlock(difficulty);
        
        blockchain.add(new Block("Third Block Data",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine block 3...");
        blockchain.get(2).mineBlock(difficulty);
        
        System.out.println("\nBlockchain is Valid: " + isChainValid());
        
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe Block Chain: ");
        System.out.println(blockchainJson);//camel case variables and using leading Chars!
    }
    
    public static Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        
        //loop through the chain and check hash codes
        for(int i = 1; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            
            //compare registered hash an calculated hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) )
            {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash))
            {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
    
}
