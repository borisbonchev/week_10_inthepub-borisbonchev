package pub;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import static pub.Pub.PINT;

/**
 * @author urs
 */
public class BarkeeperTest {

    private Barkeeper barkeeper;
    private Pub pub;
    private final double stock = 100.0;

    @BeforeEach
    void setUp() {
        pub = new Pub( stock );
        barkeeper = new Barkeeper( pub );
    }

    @Test
    void barkeeperTapsBeer() throws EmptyStockException {
        //TODO Test that the correct size beer is poured
        barkeeper.tapBeer(BeerSize.PINT.getSize());
        assertThat(pub.getStock()).isEqualTo(stock - BeerSize.PINT.getSize());
    }

    @Test
    void barkeeperServesDrinker() throws EmptyStockException, DrinkerTooYoungException, DrinkerFullException {
        //TODO Test that both the stock is decreased and the drinkers capacity is decreased (drink responsibly ;-))
        Drinker drinker = new Drinker(6.0, 27);

        barkeeper.serve(drinker, BeerSize.SMALL.getSize());
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(pub.getStock()).isEqualTo(stock - BeerSize.SMALL.getSize());
            s.assertThat(drinker.getVolumeLeft()).isEqualTo(6.0 - BeerSize.SMALL.getSize());
        });
    }
    
    @Test
    public void testTabBeerEmptyStock() {
        //TODO Test that you cannot pour more beer than is in stock
        assertThatThrownBy(() -> barkeeper.tapBeer(200)).isInstanceOf(EmptyStockException.class);
    }
    
    @Test
    public void testServeDrinkerTooYoung() {
        //TODO Test that underage drinkers cannot drink
        Drinker drinker = new Drinker(3.0, 16);

        assertThatThrownBy(() -> barkeeper.serve(drinker, BeerSize.SMALL.getSize()))
                .isInstanceOf(DrinkerTooYoungException.class);
    }
}
