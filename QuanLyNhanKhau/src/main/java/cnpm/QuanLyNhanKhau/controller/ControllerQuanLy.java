package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.Holder;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;


public class ControllerQuanLy implements Initializable{

	@FXML
	private TableView<ModelUser> tableviewUser;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnTenNguoiDung;
	@FXML
	private TableColumn<ModelUser, Date> tablecolumnTaiKhoan;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnMatKhau;
	
	@FXML
	private TableView<ModelUser> tableviewUserNV;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnTenNguoiDungNV;
	@FXML
	private TableColumn<ModelUser, Date> tablecolumnTaiKhoanNV;
	@FXML
	private TableColumn<ModelUser, String> tablecolumnMatKhauNV;
	
	private ModelUser user;
	private ModelUser userNV;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tablecolumnTenNguoiDung.setCellValueFactory(new PropertyValueFactory<>("tenNguoiDung"));
		tablecolumnTaiKhoan.setCellValueFactory(new PropertyValueFactory<>("taiKhoan"));
		tablecolumnMatKhau.setCellValueFactory(new PropertyValueFactory<>("matKhau"));
		
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
		
		List<ModelUser> listUser = Connector.getAllUserByRole("DANG KY");
		listUser.forEach(user -> {
			tableviewUser.getItems().add(user);
		});
	}
	
	public void refreshTableViewUserNV() {
		tableviewUserNV.getItems().clear();
		
		List<ModelUser> listUser = Connector.getAllUserByRole("NHAN VIEN");
		listUser.forEach(user -> {
			tableviewUserNV.getItems().add(user);
		});
	}
	
	@FXML
	public void confirmUser() {
		user.setRole("NHAN VIEN");
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
			ControllerFormDetailUser.setControllerQuanLy(this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
