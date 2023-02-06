package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.Holder;
import cnpm.QuanLyNhanKhau.model.ModelHoKhau;
import cnpm.QuanLyNhanKhau.model.ModelHoKhauNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

public class ControllerFormDetailHoKhauNhanVien implements Initializable {

	@FXML
	private GridPane gridpane;

	@FXML
	private TextField textfieldHoTenChuHo;
	@FXML
	private TextField textfieldSoNha;
	@FXML
	private TextField textfieldDuongPho;
	@FXML
	private TextField textfieldPhuong;
	@FXML
	private TextField textfieldQuan;
	@FXML
	private Button buttonTimKiem;

	@FXML
	private Label labelThongBao;

	@FXML
	private Button buttonLuuThayDoi;

	@FXML
	private TableView<ModelNhanKhau> tableviewNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnHoTen;
	@FXML
	private TableColumn<ModelNhanKhau, Date> tablecolumnNgaySinh;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnGioiTinh;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnCCCD;

	@FXML
	private TableView<ModelNhanKhau> tableviewHoKhauNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnHoTenHKNKNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, Date> tablecolumnNgaySinhHKNKNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnGioiTinhHKNKNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnCCCDHKNKNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnQuanHe;
	

	private ModelHoKhau data;
	private ModelNhanKhau chuHo;
	private ModelNhanKhau thanhVien;

	private static ControllerHoKhau controllerHoKhau;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData(Holder.getInstance().getId());
		
		textfieldHoTenChuHo.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonTimKiem.fire();
			}
		});
		
		textfieldSoNha.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldDuongPho.requestFocus();
			}
		});
		
		textfieldDuongPho.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldPhuong.requestFocus();
			}
		});
		
		textfieldPhuong.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldQuan.requestFocus();
			}
		});
		
		textfieldQuan.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonLuuThayDoi.fire();
			}
		});
		
		textfieldHoTenChuHo.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldHoTenChuHo.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoNha.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldSoNha.getStyleClass().removeAll("inputfield-error");
			}
		});

		textfieldDuongPho.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldDuongPho.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldPhuong.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldPhuong.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldQuan.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldQuan.getStyleClass().removeAll("inputfield-error");
			}
		});

		tablecolumnHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
		tablecolumnNgaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
		tablecolumnGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
		tablecolumnCCCD.setCellValueFactory(new PropertyValueFactory<>("cccd"));
		tableviewNhanKhau.setRowFactory(val -> {
			TableRow<ModelNhanKhau> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if (e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					chuHo = row.getItem();
					textfieldHoTenChuHo.setText(chuHo.getHoTen());
					gridpane.getRowConstraints().get(1).setPrefHeight(0);
					tableviewNhanKhau.setVisible(false);
					textfieldSoNha.requestFocus();
				}
			});
			return row;
		});

		tablecolumnHoTenHKNKNhanKhau.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
		tablecolumnNgaySinhHKNKNhanKhau.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
		tablecolumnGioiTinhHKNKNhanKhau.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
		tablecolumnCCCDHKNKNhanKhau.setCellValueFactory(new PropertyValueFactory<>("cccd"));
		tablecolumnQuanHe.setCellValueFactory(new PropertyValueFactory<>("quanHe"));

		tableviewHoKhauNhanKhau.setRowFactory(val -> {
			TableRow<ModelNhanKhau> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if (e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					thanhVien = row.getItem();
					List<Integer> listId = new ArrayList<Integer>();
					listId.add(thanhVien.getModelHoKhauNhanKhau().getIdHoKhauNhanKhau());
					Holder.getInstance().setId(listId);
					try {
						App.setRootSceneForm("view/ViewFormDetailHoKhauNhanKhau");
						ControllerFormDetailHoKhauNhanKhau.setControllerHoKhau(controllerHoKhau);
						ControllerFormDetailHoKhauNhanKhau.setControllerFormDetailHoKhauNhanVien(this);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			return row;
		});
		
		refreshHoKhauNhanKhau();

	}

	@FXML
	public void searchNhanKhau() {
		List<ModelNhanKhau> listNhanKhau = Connector.searchNhanKhauByHoTen(textfieldHoTenChuHo.getText());

		tableviewNhanKhau.getItems().clear();
		listNhanKhau.forEach(nhanKhau -> {
			tableviewNhanKhau.getItems().add(nhanKhau);
		});

		tableviewNhanKhau.setVisible(true);
		gridpane.getRowConstraints().get(1).setPrefHeight(150);
	}

	@FXML
	public void addHoKhauNhanKhau() {
		List<Integer> listId = new ArrayList<Integer>();
		listId.add(data.getIdHoKhau());
		Holder.getInstance().setId(listId);
		try {
			App.setRootSceneForm("view/ViewFormAddHoKhauNhanKhau");
			ControllerFormAddHoKhauNhanKhau.setControllerHoKhau(controllerHoKhau);
			ControllerFormAddHoKhauNhanKhau.setControllerFormDetailHoKhauNhanVien(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void editHoKhau() {
		if (isMissingField()) {
			labelThongBao.getStyleClass().removeAll("notice-green");
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}
		
		data.setIdNhanKhau(chuHo.getIdNhanKhau());
		data.setModelNhanKhau(chuHo);
		data.setDiaChi(textfieldSoNha.getText() + " - " + textfieldDuongPho.getText() + " - " + textfieldPhuong.getText() +
				" - " + textfieldQuan.getText());
		data.setSoNha(textfieldSoNha.getText());
		data.setDuongPho(textfieldDuongPho.getText());
		data.setPhuong(textfieldPhuong.getText());
		data.setQuan(textfieldQuan.getText());

		if (Connector.editHoKhau(data)) {
			labelThongBao.getStyleClass().add("notice-green");
			labelThongBao.setText("Đã lưu thay đổi");

			controllerHoKhau.refreshHoKhau();
			controllerHoKhau.refreshHoKhauNhanKhau();
		}
	}

	@FXML
	public void deleteHoKhau() {
		List<ModelHoKhauNhanKhau> listHoKhauNhanKhau = Connector.searchHoKhauNhanKhauByIdHoKhau(data.getIdHoKhau());

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("css/main.css").toExternalForm());
		alert.setHeaderText("Xóa mục được chọn?");
		if (!listHoKhauNhanKhau.isEmpty()) {
			String contentText = "Các mục liên quan sau cũng sẽ bị xóa:\n";
			for (ModelHoKhauNhanKhau hoKhauNhanKhau : listHoKhauNhanKhau) {
				contentText += hoKhauNhanKhau.getHoTenNhanKhau() + " - " + hoKhauNhanKhau.getQuanHe() + "\n";
			}
			alert.setContentText(contentText);
		}
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			for(ModelHoKhauNhanKhau hoKhauNhanKhau: listHoKhauNhanKhau) {
				Connector.deleteHoKhauNhanKhau(hoKhauNhanKhau);
			}
			if (Connector.deleteHoKhau(data)) {
				controllerHoKhau.refreshHoKhau();
				App.closeStageForm();
			}
		}
	}

	@FXML
	public void cancel() {
		App.closeStageForm();
	}

	public void setLabelThongBao(String message) {
		labelThongBao.setText(message);
	}

	public void refreshHoKhauNhanKhau() {
		refreshTableViewHoKhauNhanKhau();
	}

	private void refreshTableViewHoKhauNhanKhau() {
		tableviewHoKhauNhanKhau.getItems().clear();

		List<ModelNhanKhau> listNhanKhau = Connector.searchNhanKhauByIdHoKhau(data.getIdHoKhau());
		listNhanKhau.forEach(nhanKhau -> {
			tableviewHoKhauNhanKhau.getItems().add(nhanKhau);
		});
	}

	private void loadData(List<Integer> listId) {
		data = Connector.getHoKhau(listId.get(0));

		textfieldHoTenChuHo.setText(data.getHoTenNhanKhau());
		chuHo = Connector.getNhanKhau(data.getIdNhanKhau());
		textfieldSoNha.setText(data.getSoNha());
		textfieldDuongPho.setText(data.getDuongPho());
		textfieldPhuong.setText(data.getPhuong());
		textfieldQuan.setText(data.getQuan());
	}

	private boolean isMissingField() {
		boolean check = false;
		if (textfieldSoNha.getText().isEmpty()) {
			textfieldSoNha.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldDuongPho.getText().isEmpty()) {
			textfieldDuongPho.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldPhuong.getText().isEmpty()) {
			textfieldPhuong.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldQuan.getText().isEmpty()) {
			textfieldQuan.getStyleClass().add("inputfield-error");
			check = true;
		}
		return check;
	}

	public static ControllerHoKhau getControllerHoKhau() {
		return controllerHoKhau;
	}

	public static void setControllerHoKhau(ControllerHoKhau controllerHoKhau) {
		ControllerFormDetailHoKhauNhanVien.controllerHoKhau = controllerHoKhau;
	}
}
