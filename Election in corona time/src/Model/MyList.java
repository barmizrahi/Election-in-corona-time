package Model;

import java.util.ArrayList;
import java.util.List;

public class MyList<T extends Citizan> {
	private List<T> list;

	public MyList() {
		list = new ArrayList<>();
	}

	public void add(T t) throws Exception {
		if (duplicate(t))
			list.add(t);
	}

	public void set(int i, T t) throws Exception {
		if (duplicate(t))
			list.set(i, t);

	}

	public T get(int i) {
		return list.get(i);
	}

	public boolean duplicate(T t) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			if (list.contains(t)) {
				throw new Exception("Object Is Already On The List");
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public int size() {
		return list.size();
	}

	public boolean instanceOf(T t) throws Exception {
		if (t instanceof Citizan)
			return true;
		else
			throw new Exception("Object Is not a citizen");

	}

}
