import java.util.List;

public class PancakeHouseMenuIterator implements Iterator {
    List<MenuItem> items;
    int position = 0;

    public PancakeHouseMenuIterator(List<MenuItem> items) {
        this.items = items;
    }

    public MenuItem next() {
		MenuItem item = items.get(position);
		position = position + 1;
		return item;
    }

    public boolean hasNext() {
		if (position >= items.size()) {
			return false;
		} else {
			return true;
		}
    }
}
