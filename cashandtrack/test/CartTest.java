package cashandtrack.test;
import cashandtrack.Menu;
import cashandtrack.StoreSingleton;
import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Test the cart order.
 * @author Chananya Photan
 */
public class CartTest {

    /** create an object of StoreSingleton */
    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();
    /** initial list of order */
    private List<Menu> cart = storeSingleton.getAllCustomer().get(0).getOrder();
    /** initial the menu */
    private Menu pasta;
    /** initial the menu */
    private Menu pizza;
    /** initial the menu */
    private Menu salad;

    /** set the menu */
    @Before
    public void setupFixture() {
        pasta = new Menu("Pasta", 120);
        pizza = new Menu("Pizza", 140);
        salad = new Menu("Green Salad",100);
    }

    /** remove all order in cart after tested */
    @After
    public void clearCart() {
        cart.clear();
    }

    /** test the empty cart */
    @Test
    public void testEmptyCart() {
        assertEquals(0, cart.size());
        cart.add(pasta);
        assertEquals(1, cart.size());
        cart.remove(pasta);
        assertEquals(0, cart.size());
    }

    /** test the size of cart*/
    @Test
    public void testSizeCart() {
        assertEquals(0, cart.size());
        cart.add(pasta);
        cart.add(pizza);
        assertEquals(2, cart.size());
        cart.remove(salad);
        assertEquals(2, cart.size());
    }

    /** test that remove order in the empty cart. */
    @Test (expected =  IndexOutOfBoundsException.class)
    public void testRemoveOrderEmptyCart() {
        assertEquals(0, cart.size());
        cart.remove(1);
    }

    /** test that remove the order twice.*/
    @Test
    public void testRemoverOrder() throws Exception {
        assertEquals(0, cart.size());
        cart.add(pasta);
        cart.add(pizza);
        assertEquals(2, cart.size());
        cart.remove(pasta);
        cart.remove(pasta);
        assertEquals(1, cart.size());
    }
}