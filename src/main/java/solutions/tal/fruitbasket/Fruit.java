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

import java.util.Objects;

/**
 * @author Thuan Luong
 */
public abstract class Fruit {

    private final String name;

    private final Double price;

    public Fruit(String name, Double price) {
        Objects.requireNonNull(name, "The parameter 'name' cannot be null");
        Objects.requireNonNull(price, "The parameter 'price' cannot be null");
        this.name = name;
        this.price = price;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Fruit fruit = (Fruit) o;

        return name.equals(fruit.name) && price.equals(fruit.price);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
