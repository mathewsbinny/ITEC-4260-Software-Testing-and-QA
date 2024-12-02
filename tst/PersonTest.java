import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class PersonTest {

    private CacheManager cache;
    private DiskManager disk;

    @Before
    public void setUp() throws Exception {
        cache = Mockito.mock(CacheManager.class);
        disk = Mockito.mock(DiskManager.class);
    }


    @Test
    public void testPersonDoesNotExist() {
        Person captainDrake = new Person(cache, disk);
        captainDrake.setPerson(8675309,"Captain", "Drake");
        Mockito.when(cache.getPerson(8675309)).thenReturn(null);
        Mockito.when(disk.getPerson(8675309)).thenReturn(null);
        Assert.assertEquals("", captainDrake.getFullName());

        Mockito.verify(cache).getPerson(8675309);
        Mockito.verify(disk).getPerson(8675309);
        Mockito.verifyNoMoreInteractions(cache, disk);
        InOrder inOrder = Mockito.inOrder(cache, disk);
        inOrder.verify(cache).getPerson(8675309);
        inOrder.verify(disk).getPerson(8675309);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testPersonExistsInCache() {
        Person taylorSwift = new Person(cache, disk);
        taylorSwift.setPerson(12345678, "Taylor", "Swift");
        Mockito.when(cache.getPerson(12345678)).thenReturn(taylorSwift);
        Mockito.when(disk.getPerson(12345678)).thenReturn(taylorSwift);
        Assert.assertEquals("Taylor Swift", taylorSwift.getFullName());

        Mockito.verify(cache).getPerson(12345678);
        Mockito.verifyNoMoreInteractions(cache);
        Mockito.verifyNoMoreInteractions(disk);
    }

    @Test
    public void testPersonExistsInDisk() {
        Person postMalone = new Person(cache, disk);
        postMalone.setPerson(87654321, "Post", "Malone");
        Mockito.when(cache.getPerson(87654321)).thenReturn(null);
        Mockito.when(disk.getPerson(87654321)).thenReturn(postMalone);
        Assert.assertEquals("Post Malone", postMalone.getFullName());
        Mockito.verify(cache).getPerson(87654321);
        Mockito.verify(disk).getPerson(87654321);
        Mockito.verifyNoMoreInteractions(cache, disk);
        InOrder inOrder = Mockito.inOrder(cache, disk);
        inOrder.verify(cache).getPerson(87654321);
        inOrder.verify(disk).getPerson(87654321);
        inOrder.verifyNoMoreInteractions();
    }
}
