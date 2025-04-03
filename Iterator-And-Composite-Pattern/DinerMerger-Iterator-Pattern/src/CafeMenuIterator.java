import java.util.Collection;

public class CafeMenuIterator implements Iterator {
    private java.util.Iterator<MenuItem> iterator;

    public CafeMenuIterator(Collection<MenuItem> items) {
        this.iterator = items.iterator();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public MenuItem next() {
        return iterator.next();
    }
}