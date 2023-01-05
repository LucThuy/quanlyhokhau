package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class ControllerFormAddHoKhau implements Initializable {

	@FXML
	private GridPane gridpane;
	
	@FXML
	private TextField textfieldHoTenChuHo;
//	@FXML
//	private TextField textfieldDiaChi;
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
	private Button buttonThemMoi;

	@FXML
	private Label labelThongBao;
	
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
	
	private ModelNhanKhau chuHo;
	private static ControllerHoKhau controllerHoKhau;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
				buttonThemMoi.fire();
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
		tableviewNhanKhau.setRowFactory( val -> {
			TableRow<ModelNhanKhau> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					chuHo = row.getItem();
					textfieldHoTenChuHo.setText(chuHo.getHoTen());
				}
				if(e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					chuHo = row.getItem();
					textfieldHoTenChuHo.setText(chuHo.getHoTen());
					gridpane.getRowConstraints().get(1).setPrefHeight(0);
					tableviewNhanKhau.setVisible(false);
					textfieldSoNha.requestFocus();
//					textfieldDiaChi.requestFocus();
				}
			});
			return row;
		});		
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
	public void addHoKhau() {
		if(isMissingField() || chuHo == null) {
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}
		if(Connector.addHoKhau(chuHo.getIdNhanKhau(), textfieldSoNha.getText() + " - " + textfieldDuongPho.getText() +
				" - " + textfieldPhuong.getText() + " - " + textfieldQuan.getText(),
				textfieldSoNha.getText(), textfieldDuongPho.getText(), textfieldPhuong.getText(), textfieldQuan.getText())) {
			controllerHoKhau.refreshHoKhau();
			App.closeStageForm();
		}
	}

	@FXML
	public void cancel() {
		App.closeStageForm();
	}

	private boolean isMissingField() {
		boolean check = false;
		if (textfieldHoTenChuHo.getText().isEmpty() || chuHo == null) {
			textfieldHoTenChuHo.getStyleClass().add("inputfield-error");
			check = true;
		}
//		if (textfieldDiaChi.getText().isEmpty()) {
//			textfieldDiaChi.getStyleClass().add("inputfield-error");
//			check = true;
//		}
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
		ControllerFormAddHoKhau.controllerHoKhau = controllerHoKhau;
	}
}
