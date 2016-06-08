/*
 * Copyright 2016 Thuan Anh Luong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package solutions.tal.fruitbasket;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author Thuan Luong
 */
public class BasketTests {

    @Test
    public void single_priced_fruit() {
        Apple apple = new Apple(2.0);
        Orange orange = new Orange(1.5);
        Banana banana = new Banana(2.5);
        Lemon lemon = new Lemon(1.2);
        Peach peach = new Peach(2.2);

        String formattedPrice = Basket.of(apple, 2, orange, 1, banana, 3, lemon, 4, peach, 1).getFormattedTotalPrice();

        assertThat(formattedPrice, is("20.00"));
    }

    @Test
    public void multiple_priced_fruit() {
        Apple cheapApple = new Apple(1.2);
        Apple expensiveApple = new Apple(2.1);

        String price = Basket.of(cheapApple, 2, expensiveApple, 3).getFormattedTotalPrice();

        assertThat(price, is("8.70"));
    }

    @Test(expected = NullPointerException.class)
    public void null_fruit() {
        Basket.of(null, 1);
    }

    @Test
    public void invalid_quantity() {
        try {
            Basket.of(new Apple(1.0), 0);
            fail("Should have thrown IllegalStateException");
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalStateException.class)));
        }

        try {
            Basket.of(new Apple(1.2), -1);
            fail("Should have thrown IllegalArgumentException");
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalStateException.class)));
        }
    }

    @Test
    public void add_more_of_same_fruit() {

        Apple apple = new Apple(2.0);

        Basket basket = Basket.of(apple, 2);
        basket.addFruit(apple).addFruit(apple, 5);

        String price = basket.getFormattedTotalPrice();

        assertThat(price, is("16.00"));

    }

    @Test
    public void three_fruit_types() {
        String price = Basket.of(new Apple(2.0), 1, new Banana(1.0), 2, new Peach(2.0), 1).getFormattedTotalPrice();
        assertThat(price, is("6.00"));
    }

    @Test
    public void four_fruit_types() {
        String price = Basket.of(new Apple(2.0), 1, new Banana(1.0), 2, new Peach(2.0), 1, new Lemon(2.2), 3).getFormattedTotalPrice();
        assertThat(price, is("12.60"));
    }

    @Test
    public void apples_are_not_oranges() {
        Apple apple = new Apple(2.2);
        Orange orange = new Orange(2.1);

        assertThat(apple, is(not(equalTo(orange))));
        assertThat(apple.getName(), is("Apple"));
        assertThat(orange.getName(), is("Orange"));
    }

    @Test
    public void apples_are_apples_but_only_at_the_same_price() {
        Apple greenApple = new Apple(2.2);
        Apple redApple = new Apple(2.2);
        Apple expensiveApple = new Apple(3.2);

        assertThat(greenApple, is(equalTo(greenApple)));
        assertThat(greenApple, is(equalTo(redApple)));
        assertThat(greenApple, is(not(equalTo(expensiveApple))));
    }
}
