package ru.job4j.solid.experimental;

import java.util.Objects;

public class PorkFat implements IFlesh {

    String placeOfProduction;
    int porkAge;

    public PorkFat() {
    }

    public PorkFat(String placeOfProduction, int porkAge) {
        this.placeOfProduction = placeOfProduction;
        this.porkAge = porkAge;
    }

    @Override
    public IIngridients addIngridient() {
        return new PorkFat();
    }

    @Override
    public IIngridients getIngridient() {
        return null;
    }

    public String getPlaceOfProduction() {
        return placeOfProduction;
    }

    public void setPlaceOfProduction(String placeOfProduction) {
        this.placeOfProduction = placeOfProduction;
    }

    public int getPorkAge() {
        return porkAge;
    }

    public void setPorkAge(int porkAge) {
        this.porkAge = porkAge;
    }

    /** Для наглядности нарушим принцип SRP и создадим внутри модели данных лишний метод определения состояния нашего продукта.
     * Подобный метод логичней реализовать в каком либо хранилище, исполоьзующем экземпляр класса.
     * private boolean frozenState() {
     *         return false;
     *     }
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PorkFat porkFat = (PorkFat) o;
        return porkAge == porkFat.porkAge && Objects.equals(placeOfProduction, porkFat.placeOfProduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeOfProduction, porkAge);
    }

    @Override
    public String toString() {
        return "PorkFat{"
                + "placeOfProduction='" + placeOfProduction + '\''
                + ", porkAge=" + porkAge
                + '}';
    }
}
