 package io.github.vibrantbyte.storage.datasource;

import java.util.HashMap;
import java.util.Map;

 /**
  * @author vibrant byte
  */
 public enum SlaveSelectPolicy {
     /**
      * 随机策略
      */
     RANDOM("random"),
     /**
      * 权重策略
      */
     WEIGHT("weight")
     ;

     private String policy;

     private static Map<String, SlaveSelectPolicy> policyMap = new HashMap<>();

     static {
         for (SlaveSelectPolicy selectPolicy : SlaveSelectPolicy.values()) {
             policyMap.put(selectPolicy.getPolicy(), selectPolicy);
         }
     }

     SlaveSelectPolicy(String policy) {
         this.policy = policy;
     }

     public String getPolicy() {
         return policy;
     }

     public static SlaveSelectPolicy getPolicy(String name) {
         return policyMap.get(name);
     }

 }
