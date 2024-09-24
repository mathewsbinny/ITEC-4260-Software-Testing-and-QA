import org.junit.Assert;
import org.junit.Test;

public class StoreTest {

    private final String TEST_STORE_URL = "https://gist.githubusercontent.com/tacksoo/349702bd06852814fba06a4df48e32d8/raw/5fb59f716e9069ac186b1994376c85823a65e335/myinventory.csv";


    @Test
    public void testStoreInventory() {
        //empty store
        Store store = new Store();
        Assert.assertEquals(0, store.getInventory().getSize());
        //testing with test store
        Store testStore = new Store();
        testStore.loadInventoryFromWeb(TEST_STORE_URL);
        Assert.assertEquals(3, testStore.getInventory().getSize());
    }
}


