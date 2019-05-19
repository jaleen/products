package products;

import org.junit.Test;
import products.model.ColorSwatches;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

public class RGBTest {


    @Test
    public void whenRedColor_theReturnRGBForRED() {

        ColorSwatches redColorSwatch = ColorSwatches.builder().basicColor("Red").build();
        assertThat(redColorSwatch.getRGBColor(), is("FF0000"));
    }

    @Test
    public void whenUnknownColor_theReturnNull() {

        ColorSwatches redColorSwatch = ColorSwatches.builder().basicColor("Multi").build();
        assertThat(redColorSwatch.getRGBColor(), is(""));

    }
}
