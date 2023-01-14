package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.model.ModelNhaVanHoa;
import cnpm.QuanLyNhanKhau.model.ModelUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ControllerNhaVanHoa implements Initializable{

	@FXML
	private TableView<ModelNhaVanHoa> tableviewThongKe;
	@FXML
	private TableColumn<ModelNhaVanHoa, Date> tablecolumnNgayKiemTra;
	@FXML
	private TableColumn<ModelNhaVanHoa, String> tablecolumnBan;
	@FXML
	private TableColumn<ModelNhaVanHoa, String> tablecolumnGhe;
	@FXML
	private TableColumn<ModelNhaVanHoa, String> tablecolumnLoa;
	@FXML
	private TableColumn<ModelNhaVanHoa, String> tablecolumnDai;
	@FXML
	private TableColumn<ModelNhaVanHoa, String> tablecolumnManHinh;
	@FXML
	private TableColumn<ModelNhaVanHoa, String> tablecolumnDen;

//	@FXML
//	private TableView<ModelUser> tableviewUserNV;
//	@FXML
//	private TableColumn<ModelUser, String> tablecolumnTenNguoiDungNV;
//	@FXML
//	private TableColumn<ModelUser, Date> tablecolumnTaiKhoanNV;
//	@FXML
//	private TableColumn<ModelUser, String> tablecolumnMatKhauNV;
	
//	private ModelUser user;
//	private ModelUser userNV;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tablecolumnNgayKiemTra.setCellValueFactory(new PropertyValueFactory<>("ngayKiemTra"));
		tablecolumnBan.setCellValueFactory(new PropertyValueFactory<>("soLuongBan"));
		tablecolumnGhe.setCellValueFactory(new PropertyValueFactory<>("soLuongGhe"));
		tablecolumnLoa.setCellValueFactory(new PropertyValueFactory<>("soLuongLoa"));
		tablecolumnDai.setCellValueFactory(new PropertyValueFactory<>("soLuongDai"));
		tablecolumnManHinh.setCellValueFactory(new PropertyValueFactory<>("soLuongManHinh"));
		tablecolumnDen.setCellValueFactory(new PropertyValueFactory<>("soLuongDen"));
		
//		tableviewThongKe.setRowFactory( val -> {
//			TableRow<ModelUser> row = new TableRow<>();
//			row.setOnMouseClicked(e -> {
//					user = row.getItem();
//			});
//			return row;
//		});
		
//		tablecolumnTenNguoiDungNV.setCellValueFactory(new PropertyValueFactory<>("tenNguoiDung"));
//		tablecolumnTaiKhoanNV.setCellValueFactory(new PropertyValueFactory<>("taiKhoan"));
//		tablecolumnMatKhauNV.setCellValueFactory(new PropertyValueFactory<>("matKhau"));
//		
//		tableviewUserNV.setRowFactory( val -> {
//			TableRow<ModelUser> row = new TableRow<>();
//			row.setOnMouseClicked(e -> {
//					userNV = row.getItem();
//			});
//			return row;
//		});
		
		refreshTableViewThongKe();
//		refreshTableViewUserNV();
	}
	
	private void refreshTableViewThongKe() {
		tableviewThongKe.getItems().clear();
		
		List<ModelNhaVanHoa> listKiemTra = Connector.getAllUserByRole("DANG KY");
		listKiemTra.forEach(user -> {
			tableviewThongKe.getItems().add(user);
		});
	}
	
//	private void refreshTableViewUserNV() {
//		tableviewUserNV.getItems().clear();
//		
//		List<ModelUser> listUser = Connector.getAllUserByRole("NHAN VIEN");
//		listUser.forEach(user -> {
//			tableviewUserNV.getItems().add(user);
//		});
//	}
	
//	@FXML
//	public void confirmUser() {
//		user.setRole("NHAN VIEN");
//		Connector.editUser(user);
//		
//		refreshTableViewUser();
//	}

//	@FXML
//	public void declineUser() {
//		Connector.deleteUser(user);
//		
//		refreshTableViewUser();
//	}
	
	@FXML
	public void addQuanLyNhaVanHoa() {
		try {
			App.addStageForm("view/ViewFormAddQuanLyNhaVanHoa");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
