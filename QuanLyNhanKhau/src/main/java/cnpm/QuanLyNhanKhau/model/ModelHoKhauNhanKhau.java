package cnpm.QuanLyNhanKhau.model;

public class ModelHoKhauNhanKhau {
	private int idHoKhauNhanKhau;
	private int idHoKhau;
	private int idNhanKhau;
	private String quanHe;
	
	private ModelNhanKhau modelNhanKhau;
	private ModelHoKhau modelHoKhau;
	
	public ModelHoKhauNhanKhau(int idHoKhauNhanKhau, int idHoKhau, int idNhanKhau, String quanHe) {
		super();
		this.idHoKhauNhanKhau = idHoKhauNhanKhau;
		this.idHoKhau = idHoKhau;
		this.idNhanKhau = idNhanKhau;
		this.quanHe = quanHe;
	}
	
	public int getIdHoKhauNhanKhau() {
		return idHoKhauNhanKhau;
	}
	public void setIdHoKhauNhanKhau(int idHoKhauNhanKhau) {
		this.idHoKhauNhanKhau = idHoKhauNhanKhau;
	}
	public int getIdHoKhau() {
		return idHoKhau;
	}
	public void setIdHoKhau(int idHoKhau) {
		this.idHoKhau = idHoKhau;
	}
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}
	public String getQuanHe() {
		return quanHe;
	}
	public void setQuanHe(String quanHe) {
		this.quanHe = quanHe;
	}

	public ModelNhanKhau getModelNhanKhau() {
		return modelNhanKhau;
	}

	public void setModelNhanKhau(ModelNhanKhau modelNhanKhau) {
		this.modelNhanKhau = modelNhanKhau;
	}
	
	public ModelHoKhau getModelHoKhau() {
		return modelHoKhau;
	}

	public void setModelHoKhau(ModelHoKhau modelHoKhau) {
		this.modelHoKhau = modelHoKhau;
	}
	
	public String getHoTenNhanKhau() {
		return modelNhanKhau.getHoTen();
	}
	
	public String getDiaChiHoKhau() {
		return modelHoKhau.getDiaChi();
	}
	
	public String getHoTenNhanKhauHoKhau() {
		return modelHoKhau.getHoTenNhanKhau();
	}
}
