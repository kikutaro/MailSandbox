package tech.kikutaro.senderauthenticationsandbox;

import org.apache.james.jspf.executor.SPFResult;
import org.apache.james.jspf.impl.DefaultSPF;

/**
 * jSPFによる送信元ドメイン認証.
 * 
 * @author kikuta
 */
public class SenderAuthMain {
    
    public static void main(String[] args) {
        DefaultSPF spf = new DefaultSPF();
        SPFResult res = spf.checkSPF("101.102.213.65", "from@kke.co.jp", "kke.co.jp");
        System.out.println(res.getResult());
    }
}
