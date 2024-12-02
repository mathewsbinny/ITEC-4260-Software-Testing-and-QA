import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class OrderManagerTest2 {

    private OrderManager orderManager;

    //use @Mock annotation to create a ProcessOrders mock object
    @Mock
    private ProcessOrders processOrders;

    //use @Spy annotation to create a OrderChecker spy object
    @Spy
    private OrderChecker orderChecker;

    private Order validOrder, invalidOrder, blackListedOrder;

    @Before
    public void setup() {
        //GIVEN - setup mock and other necessary objects

        //create orderManager instance with mock object
        orderManager = new OrderManager(processOrders);

        //instantiate validOrder and invalidOrder objects
        validOrder = new Order(1,"dr.im","1000 university center lane", 1000000, false);
        invalidOrder = new Order(2, "dr.doom", "new york city", 0, false);
        blackListedOrder = new Order(3, "dr.evil", "evil corp headquarters", 10000000, false);
    }


    @Test
    public void testCheckInvalidOrder() {
        //WHEN - have the mock object do something (that you want to test)
        boolean result = orderChecker.isValidOrder(invalidOrder);

        //we expect this to be false
        //code in isValidOrder() will get executed because spy is used

        //THEN - verify expected result
        Assert.assertFalse(result);

        //A spy will remember all interactions.
        //verify interactions when isValidOrder() was called
        Mockito.verify(orderChecker).isValidOrder(invalidOrder);
        Mockito.verifyNoMoreInteractions(orderChecker);
    }


    @Test
    public void testCheckValidOrder() {
        //WHEN - have the mock object do something (that you want to test)

        boolean result = orderChecker.isValidOrder(validOrder);
        Assert.assertTrue(result);

        orderManager.processOrder(validOrder.id);

        //THEN - verify expected result

        //A spy will remember all interactions.
        //verify interactions when isValidOrder() was called
        Mockito.verify(orderChecker).isValidOrder(validOrder);
        Mockito.verify(orderChecker).isProfitableSale(validOrder);
        Mockito.verify(orderChecker).isFromBannedCustomer(validOrder);
        Mockito.verifyNoMoreInteractions(orderChecker);
        //A mock will remember all interactions.
        //You can selectively verify that specific methods were actually called.
        Mockito.verify(processOrders).shipOrder(validOrder.id);
        Mockito.verifyNoMoreInteractions(processOrders);
        //you can also verify that no other interactions on the mock object
        //happened other than the ones expected above
    }


    @Test
    public void testOrderFromBannedCustomer() {
        Mockito.when(orderChecker.isFromBannedCustomer(blackListedOrder)).thenReturn(true);

        boolean result = orderChecker.isValidOrder(blackListedOrder);

        Assert.assertFalse(result);

        Mockito.verify(orderChecker).isValidOrder(blackListedOrder);
        Mockito.verify(orderChecker).isProfitableSale(blackListedOrder);
        Mockito.verify(orderChecker).isFromBannedCustomer(blackListedOrder);
        Mockito.verifyNoMoreInteractions(orderChecker);

    }



}
