package ru.job4j.solid.experimental;

public class Main {

    public static void main(String[] args) throws Exception {
        IIngridients potato = new Potato();
        IIngridients carrot = new Carrot(33);
        IIngridients porkFat = new PorkFat();
        Cook soup = new Borsch(potato, carrot, porkFat);
        soup.cook();

        IIngridients wCarrot = new WhiteCarrot(1);
        /** Теперь проверим какую морковку мы добавляем в суп и получим ошибку, потому что
         * очередная организация правозащитников белой морквы запретила это делать
         */
        checkCarrot(wCarrot);
        /** А если проверки нет, мы можем вывести предостережение пользователю
         * Таким образом. Невозможность по каким то причинам использовать наследника морковки,
         * как саму морковку (родительский класс) является нарушением принципа подстановки старушки Лисков
         */
        if (wCarrot != null && wCarrot.getClass() == new WhiteCarrot(1).getClass()) {
            System.out.println("А вы уверены, что хотите белую морковь в суп?");
        }
        Cook soupForBoyare = new Borsch(potato, wCarrot, porkFat);
        soupForBoyare.cook();

    }

    /** Мы помним, что представителей класса:
     * @see WhiteCarrot
     * очень мало в мире и мало того, что в самом наследнике Carrot уже наворотили дел
     * с нарушением принципа подстановки Лисков, так продолжим делать подобное здесь.
     * Так как мы изменили конструктор в нарушение принципа, теперь мы хотим при добавлении в суп Carrot
     * как-то проверять, что это не наш краснокнижный объект - WhiteCarrot. Запилим эту проверку в методе
     */
    private static boolean checkCarrot(IIngridients carrot) {
        boolean rsl = true;
        if (carrot instanceof WhiteCarrot) {
            try {
                throw new Exception("Нельзя белую морковь в суп!!!!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return rsl;
    }
}
