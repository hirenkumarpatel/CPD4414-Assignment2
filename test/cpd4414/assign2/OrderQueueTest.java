/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 * Updated 2015 Mark Russell <mark.russell@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cpd4414.assign2;

import cpd4414.assign2.OrderQueue;
import cpd4414.assign2.Purchase;
import cpd4414.assign2.Order;
import cpd4414.assign2.OrderQueue.CustomerException;
import cpd4414.assign2.OrderQueue.PurchaseException;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class OrderQueueTest {
    
    public OrderQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testWhenCustomerExistsAndPurchasesExistThenTimeReceivedIsNow() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Cafeteria");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        orderQueue.add(order);
        
        long expResult = new Date().getTime();
        long result = order.getTimeReceived().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
    }
    
    @Test
     public void testWhenNoCustomerExistsThenThrowAnException() throws PurchaseException {
        
        boolean isThrown=false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("", "");
        
        try
        {
            orderQueue.add(order);
        }
        catch(CustomerException ex)
        {
            System.out.println(ex.getMessage());
            isThrown=true;
        }
        assertTrue(isThrown);
        
        
    }
    
    @Test
     public void testWhenNoPurchaseThenThrowAnException() throws CustomerException {
        
        boolean isThrown=false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST001","ORDER");
       
        try
        {
            orderQueue.add(order);
        }
        catch(PurchaseException ex)
        {
            System.out.println(ex.getMessage());
            isThrown=true;
        }
        assertTrue(isThrown);
        
        
    }
     @Test
     public void testGetNextWhenAlreadyOrderThenGetNext() throws PurchaseException, CustomerException
     {
         OrderQueue orderQueue= new OrderQueue();
         Order order1= new Order("CustId","CustName");
         order1.addPurchase(new Purchase("ItemId",5));
         orderQueue.add(order1);
         
         Order order2= new Order("CustId","CustName");
         order2.addPurchase(new Purchase("ItemId",5));
         orderQueue.add(order2);
         
         Order result= orderQueue.next();
         assertEquals(result, order1);
         assertNull(result.getTimeProcessed());
         
         
         
     }
    
     @Test
     public void testGetNextWhenAlreadyOrderThenReturnNull() throws PurchaseException, CustomerException
     {
         OrderQueue orderQueue= new OrderQueue();
                  
         Order result= orderQueue.next();
         assertNull(result);
         
         
     }
     
     @Test
     
     public void testProcessWhenTimeReceivedIsSetThenTimeProcessedToNow() throws PurchaseException, CustomerException
     {
         
        OrderQueue orderQueue= new OrderQueue();
         Order order1= new Order("CustId","CustName");
         order1.addPurchase(new Purchase("ItemId",5));
         orderQueue.add(order1);
         
         Order order2= new Order("CustId","CustName");
         order2.addPurchase(new Purchase("ItemId",5));
         orderQueue.add(order2);
         
         Order nextOrder=orderQueue.next();
         orderQueue.process(nextOrder);
         
         long expResult = new Date().getTime();
        long result = nextOrder.getTimeReceived().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);

         
     }
     
    
}
