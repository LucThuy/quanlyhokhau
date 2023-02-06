package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.Holder;
import cnpm.QuanLyNhanKhau.model.ModelUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;


public class ControllerQuanLyUser implements Initializable{

	@FXML
	private TableView<ModelUser> tableviewUser;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnTenNguoiDung;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnTaiKhoan;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnMatKhau;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnChucVu;
	
	@FXML
	private TableView<ModelUser> tableviewUserNV;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnTenNguoiDungNV;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnTaiKhoanNV;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnMatKhauNV;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnChucVuNV;
	
	private ModelUser user;
//	private ModelUser userNV;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tablecolumnTenNguoiDung.setCellValueFactory(new PropertyValueFactory<>("tenNguoiDung"));
		tablecolumnTaiKhoan.setCellValueFactory(new PropertyValueFactory<>("taiKhoan"));
		tablecolumnMatKhau.setCellValueFactory(new PropertyValueFactory<>("matKhau"));
		tablecolumnChucVu.setCellValueFactory(new PropertyValueFactory<>("role"));
		
		tableviewUser.setRowFactory( val -> {
			TableRow<ModelUser> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				user = row.getItem();
			});
			return row;
		});
		
		tablecolumnTenNguoiDungNV.setCellValueFactory(new PropertyValueFactory<>("tenNguoiDung"));
		tablecolumnTaiKhoanNV.setCellValueFactory(new PropertyValueFactory<>("taiKhoan"));
		tablecolumnMatKhauNV.setCellValueFactory(new PropertyValueFactory<>("matKhau"));
		tablecolumnChucVuNV.setCellValueFactory(new PropertyValueFactory<>("role"));

		
		tableviewUserNV.setRowFactory( val -> {
			TableRow<ModelUser> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if(e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					showDetailUser();
				}
			});
			return row;
		});
		
		
		
		refreshTableViewUser();
		refreshTableViewUserNV();
	}
	
	private void refreshTableViewUser() {
		tableviewUser.getItems().clear();
		
		List<ModelUser> listUser = Connector.getAllUserByCapQuyen("Chưa cấp quyền");
		listUser.forEach(user -> {
			tableviewUser.getItems().add(user);
		});
	}
	
	public void refreshTableViewUserNV() {
		tableviewUserNV.getItems().clear();
		
		List<ModelUser> listUser = Connector.getAllUserExceptToTruong();
		listUser.forEach(user -> {
			tableviewUserNV.getItems().add(user);
		});
	}
	
	@FXML
	public void confirmUser() {
		user.setCapQuyen("Đã cấp quyền");
		Connector.editUser(user);
		
		refreshTableViewUser();
		refreshTableViewUserNV();
	}

	@FXML
	public void declineUser() {
		Connector.deleteUser(user);
		
		refreshTableViewUser();
	}
	
	@FXML
	public void showDetailUser() {
		user = tableviewUserNV.getSelectionModel().getSelectedItem();
		List<Integer> listId = new ArrayList<>();
		listId.add(user.getIdUser());
		Holder.getInstance().setId(listId);	
		try {
			App.addStageForm("view/ViewFormDetailUSer");
			ControllerFormDetailUser.setControllerQuanLyUser(this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
