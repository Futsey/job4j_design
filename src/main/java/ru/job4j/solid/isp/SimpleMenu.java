package ru.job4j.solid.isp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс реализует работу интерфейса
 * @see Menu
 */
public class SimpleMenu implements Menu {

    /**
     * Лист rootElements хранит в себе перечень корневых пунктов (экземпляров) Меню
     */
    private final List<MenuItem> rootElements = new ArrayList<>();

    /**
     * Метод добавляет новый экземпляр Меню, выбирая корневым будет новый экземпляр или наследником ROOT-элемента
     */
    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        boolean checkChild = findItem(childName).isEmpty();
        if (findItem(childName).isPresent()) {
            return false;
        }
        if (checkChild && Objects.equals(parentName, Menu.ROOT)) {
            MenuItem item = new SimpleMenuItem(childName, actionDelegate);
            rootElements.add(item);
            rsl = true;
        } else if (checkChild) {
            Optional<ItemInfo> parent = findItem(parentName);
            if (parent.isPresent()) {
                parent.get().menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
                rsl = true;
            }
        }
        return rsl;
    }

    /**
     * Метод позволяет выбрать пункт (экземпляр) Меню по его имени
     * В целях обеспечения защиты от получения null-значения используем обертку Optional
     * @param itemName
     * @return Optional.empty() либо пункт(экземпляр) Меню
     */
    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(s -> new MenuItemInfo(s.menuItem,
                s.number));
    }

    /**
     * Метод позволяет пробежать по всем пунктам (экземплярам) Меню.
     * Благодаря DFSIterator мы можем пройтись по данным рекурсивно
     */
    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {
            DFSIterator dfsIterator = new DFSIterator();
            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = dfsIterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    /**
     * Метод позволяет найти пункт (экземпляр) Меню по имени.
     * @param name
     * @return Optional.empty() либо пункт (экземпляр) Меню
     */
    private Optional<ItemInfo> findItem(String name) {
        Iterator<ItemInfo> iterator = new DFSIterator();
        Optional<ItemInfo> rsl = Optional.empty();
        while (iterator.hasNext()) {
            ItemInfo itemInfo = iterator.next();
            if (name.equals(itemInfo.menuItem.getName())) {
                rsl = Optional.of(itemInfo);
                break;
            }
        }
        return rsl;
    }

    /**
     * Приватный статический вложенный класс SimpleMenuItem реализует дефолтный вложенный класс MenuItemInfo.
     * @see ru.job4j.solid.isp.Menu.MenuItemInfo
     */
    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    /**
     * DFSIterator. Это итератор, основанный на поиске в глубину. Но немного модифицированный,
     * поскольку нам удобно читать пункты меню сверху-вниз и слева-направо.
     */
    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

    @Override
    public String toString() {
        return "SimpleMenu{" +
                "rootElements=" + rootElements +
                '}';
    }
}
