package cnpm.QuanLyNhanKhau.model;

import java.sql.Date;

public class ModelNhanKhau {
	private int idNhanKhau;
	private String hoTen;
	private String biDanh;
	private Date ngaySinh;
	private String gioiTinh;
	private String cccd;
	private Date ngayCap;
	private String noiCap;
	private String nguyenQuan;
	private String danToc;
	private String noiThuongTru;
	private Date ngayDangKyThuongTru;
	private String trinhDoHocVan;
	private String trinhDoNgoaiNgu;
	private String ngheNghiep;
	private String noiLamViec;
	private String tonGiao;
	private String quocTich;
	private String trinhDoChuyenMon;
	private String ghiChu;
	private String trangThai;
	

	private ModelHoKhau modelHoKhau;
	private ModelHoKhauNhanKhau modelHoKhauNhanKhau;
	
	public ModelNhanKhau(int idNhanKhau, String hoTen, String biDanh, Date ngaySinh, String gioiTinh, String cccd,
						Date ngayCap, String noiCap, String nguyenQuan, String danToc,String noiThuongTru,
						Date ngayDangKyThuongTru, String trinhDoHocVan, String trinhDoNgoaiNgu,String ngheNghiep,
						String noiLamViec, String tonGiao, String quocTich, String trinhDoChuyenMon, String ghiChu,
						String trangThai) {
		super();
		this.idNhanKhau          = idNhanKhau;
		this.hoTen               = hoTen;
		this.biDanh              = biDanh;
		this.ngaySinh            = ngaySinh;
		this.gioiTinh            = gioiTinh;
		this.cccd                = cccd;
		this.ngayCap             = ngayCap;
		this.noiCap              = noiCap;
		this.nguyenQuan          = nguyenQuan;
		this.danToc              = danToc;
		this.noiThuongTru        = noiThuongTru;
		this.ngayDangKyThuongTru = ngayDangKyThuongTru;
		this.trinhDoHocVan       = trinhDoHocVan;
		this.trinhDoNgoaiNgu     = trinhDoNgoaiNgu;
		this.ngheNghiep          = ngheNghiep;
		this.noiLamViec          = noiLamViec;
		this.tonGiao             = tonGiao;
		this.quocTich            = quocTich;
		this.trinhDoChuyenMon    = trinhDoChuyenMon;
		this.ghiChu              = ghiChu;
		this.trangThai           = trangThai;
	}
	
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getBiDanh() {
		return biDanh;
	}
	public void setBiDanh(String biDanh) {
		this.biDanh = biDanh;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh.toLowerCase();
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getCccd() {
		return cccd;
	}
	public void setCccd(String cccd) {
		this.cccd = cccd;
	}	
	public Date getNgayCap() {
		return ngayCap;
	}
	public void setNgayCap(Date ngayCap) {
		this.ngayCap = ngayCap;
	}
	public String getNoiCap() {
		return noiCap;
	}
	public void setNoiCap(String noiCap) {
		this.noiCap = noiCap;
	}
	public String getNguyenQuan() {
		return nguyenQuan;
	}
	public void setNguyenQuan(String nguyenQuan) {
		this.nguyenQuan = nguyenQuan;
	}
	public String getDanToc() {
		return danToc;
	}
	public void setDanToc(String danToc) {
		this.danToc = danToc;
	}
	public String getNoiThuongTru() {
		return noiThuongTru;
	}
	public void setNoiThuongTru(String noiThuongTru) {
		this.noiThuongTru = noiThuongTru;
	}
	public Date getNgayDangKyThuongTru() {
		return ngayDangKyThuongTru;
	}
	public void setNgayDangKyThuongTru(Date ngayDangKyThuongTru) {
		this.ngayDangKyThuongTru = ngayDangKyThuongTru;
	}
	public String getTrinhDoHocVan() {
		return trinhDoHocVan;
	}
	public void setTrinhDoHocVan(String trinhDoHocVan) {
		this.trinhDoHocVan = trinhDoHocVan;
	}
	public String getTrinhDoNgoaiNgu() {
		return trinhDoNgoaiNgu;
	}
	public void setTrinhDoNgoaiNgu(String trinhDoNgoaiNgu) {
		this.trinhDoNgoaiNgu = trinhDoNgoaiNgu;
	}
	public String getNgheNghiep() {
		return ngheNghiep;
	}
	public void setNgheNghiep(String ngheNghiep) {
		this.ngheNghiep = ngheNghiep;
	}
	public String getNoiLamViec() {
		return noiLamViec;
	}
	public void setNoiLamViec(String noiLamViec) {
		this.noiLamViec = noiLamViec;
	}
	public String getTonGiao() {
		return tonGiao;
	}
	public void setTonGiao(String tonGiao) {
		this.tonGiao = tonGiao;
	}
	public String getQuocTich() {
		return quocTich;
	}
	public void setQuocTich(String quocTich) {
		this.quocTich = quocTich;
	}
	public String getTrinhDoChuyenMon() {
		return trinhDoChuyenMon;
	}
	public void setTrinhDoChuyenMon(String trinhDoChuyenMon) {
		this.trinhDoChuyenMon = trinhDoChuyenMon;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public ModelHoKhauNhanKhau getModelHoKhauNhanKhau() {
		return modelHoKhauNhanKhau;
	}
	
	public void setModelHoKhauNhanKhau(ModelHoKhauNhanKhau modelHoKhauNhanKhau) {
		this.modelHoKhauNhanKhau = modelHoKhauNhanKhau;
	}
	
	public String getQuanHe() {
		return modelHoKhauNhanKhau.getQuanHe();
	}
	
	public String getIdHoKhau() {
		if(modelHoKhauNhanKhau != null) {
			return String.valueOf(modelHoKhauNhanKhau.getIdHoKhau());
		}
		return "Chưa có hộ khẩu";
	}
}
