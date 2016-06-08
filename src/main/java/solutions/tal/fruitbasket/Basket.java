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

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Thuan Luong
 */
public class Basket {

    private final Map<Fruit, Integer> fruits = new HashMap<>();

    public static Basket of(Fruit fruit, int quantity) {
        return new Basket(entryOf(fruit, quantity));
    }

    public static Basket of(Fruit fruit1, int quantity1, Fruit fruit2, int quantity2) {
        return new Basket(entryOf(fruit1, quantity1), entryOf(fruit2, quantity2));
    }

    public static Basket of(Fruit fruit1, int quantity1, Fruit fruit2, int quantity2, Fruit fruit3, int quantity3) {
        return new Basket(entryOf(fruit1, quantity1), entryOf(fruit2, quantity2), entryOf(fruit3, quantity3));
    }

    public static Basket of(Fruit fruit1, int quantity1, Fruit fruit2, int quantity2, Fruit fruit3, int quantity3, Fruit fruit4, int quantity4) {
        return new Basket(entryOf(fruit1, quantity1), entryOf(fruit2, quantity2), entryOf(fruit3, quantity3), entryOf(fruit4, quantity4));
    }

    public static Basket of(Fruit fruit1, int quantity1, Fruit fruit2, int quantity2, Fruit fruit3, int quantity3, Fruit fruit4, int quantity4, Fruit fruit5, int quantity5) {
        return new Basket(entryOf(fruit1, quantity1), entryOf(fruit2, quantity2), entryOf(fruit3, quantity3), entryOf(fruit4, quantity4), entryOf(fruit5, quantity5));
    }

    private static BasketEntry entryOf(Fruit fruit, int quantity) {
        return new BasketEntry(fruit, quantity);
    }

    private Basket(BasketEntry... fruits) {
        Stream.of(fruits).forEach(f -> addFruit(f.getFruit(), f.getQuantity()));
    }

    public Double getTotalPrice() {
        return fruits.entrySet().stream().collect(Collectors.summingDouble(e -> e.getKey().getPrice() * e.getValue()));
    }

    public String getFormattedTotalPrice() {
        return String.format("%.2f", getTotalPrice());
    }

    public Basket addFruit(Fruit fruit) {
        return addFruit(fruit, 1);
    }

    public Basket addFruit(Fruit fruit, int quantity) {
        Objects.requireNonNull(fruit, "The parameter 'fruit' cannot be null");
        Preconditions.checkState(quantity > 0, "The parameter 'quantity' must be greater than 1");

        fruits.merge(fruit, quantity, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer original, Integer additional) {
                return original + additional;
            }
        });

        return this;
    }

    private static class BasketEntry {

        private final Fruit fruit;

        private final int quantity;

        public BasketEntry(Fruit fruit, int quantity) {
            this.fruit = fruit;
            this.quantity = quantity;
        }

        public Fruit getFruit() {
            return fruit;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
