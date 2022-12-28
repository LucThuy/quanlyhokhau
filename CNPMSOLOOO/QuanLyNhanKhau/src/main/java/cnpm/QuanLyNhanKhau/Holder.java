package cnpm.QuanLyNhanKhau;

import java.util.List;

public final class Holder {

	private List<Integer> id;
	private final static Holder INSTANCE = new Holder();
	
	private Holder() {
		
	}

	public List<Integer> getId() {
		return id;
	}

	public void setId(List<Integer> id) {
		this.id = id;
	}

	public static Holder getInstance() {
		return INSTANCE;
	}
	
	
}
