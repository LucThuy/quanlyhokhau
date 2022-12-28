package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerFormDetailNhanKhau implements Initializable {

	@FXML
	private TextField textfieldHoTen;
	@FXML
	private DatePicker datepickerNgaySinh;
	@FXML
	private ToggleGroup togglegroupGioiTinh;
	@FXML
	private TextField textfieldCCCD;

	@FXML
	private Label labelThongBao;

	@FXML
	private Button buttonLuuThayDoi;

	private ModelNhanKhau data;
	
	private static ControllerNhanKhau controllerNhanKhau;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData(Holder.getInstance().getId());

		textfieldHoTen.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				datepickerNgaySinh.requestFocus();
			}
		});

		datepickerNgaySinh.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				RadioButton radiobuttonGioiTinh = (RadioButton) togglegroupGioiTinh.getToggles().get(0);
				radiobuttonGioiTinh.requestFocus();
			}
		});

		togglegroupGioiTinh.getToggles().stream().forEach(toggle -> {
			RadioButton radiobutton = (RadioButton) toggle;
			radiobutton.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
				if (e.getCode() == KeyCode.ENTER) {
					textfieldCCCD.requestFocus();
				}
			});
		});

		textfieldCCCD.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonLuuThayDoi.fire();
			}
		});

		textfieldHoTen.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldHoTen.getStyleClass().removeAll("inputfield-error");
			}
		});

		datepickerNgaySinh.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				datepickerNgaySinh.getStyleClass().removeAll("inputfield-error");
			}
		});

		togglegroupGioiTinh.getToggles().stream().forEach(toggle -> {
			RadioButton radiobutton = (RadioButton) toggle;
			radiobutton.focusedProperty().addListener((obs, oldVal, newVal) -> {
				if (newVal) {
					labelThongBao.setText("");
					togglegroupGioiTinh.getToggles().stream().forEach(t -> {
						RadioButton r = (RadioButton) t;
						r.getStyleClass().removeAll("inputfield-error");
					});
				}
			});
		});

		textfieldCCCD.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldCCCD.getStyleClass().removeAll("inputfield-error");
			}
		});
	}

	@FXML
	public void editNhanKhau() {
		if (isMissingField()) {
			labelThongBao.getStyleClass().removeAll("notice-green");
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}
		RadioButton selectedRadioButton = (RadioButton) togglegroupGioiTinh.getSelectedToggle();

		data.setHoTen(textfieldHoTen.getText());
		data.setNgaySinh(Date.valueOf(datepickerNgaySinh.getValue()));
		data.setGioiTinh(selectedRadioButton.getText());
		data.setCccd(textfieldCCCD.getText());

		if (Connector.editNhanKhau(data)) {
			labelThongBao.getStyleClass().add("notice-green");
			labelThongBao.setText("Đã lưu thay đổi");
			controllerNhanKhau.refreshNhanKhau();
		}
	}

	@FXML
	public void deleteNhanKhau() {
		List<ModelHoKhau> listHoKhau = Connector.searchHoKhauByIdNhanKhau(data.getIdNhanKhau());
		List<ModelHoKhauNhanKhau> listHoKhauNhanKhau = Connector.searchHoKhauNhanKhauByIdNhanKhau(data.getIdNhanKhau());
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("css/main.css").toExternalForm());
		alert.setHeaderText("Xóa mục được chọn?");
		if(!listHoKhau.isEmpty() || !listHoKhauNhanKhau.isEmpty()) {
			String contentText = "Các mục liên quan sau cũng sẽ bị xóa:\n";
			for(ModelHoKhau hoKhau: listHoKhau) {
				contentText += hoKhau.getHoTenNhanKhau() + " - " + hoKhau.getDiaChi() + "\n";
			}
			for(ModelHoKhauNhanKhau hoKhauNhanKhau: listHoKhauNhanKhau) {
				contentText += hoKhauNhanKhau.getHoTenNhanKhauHoKhau() + " - " + hoKhauNhanKhau.getDiaChiHoKhau() + " - " + hoKhauNhanKhau.getQuanHe() + "\n";
			}
			alert.setContentText(contentText);
		}
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			for(ModelHoKhauNhanKhau hoKhauNhanKhau: listHoKhauNhanKhau) {
				Connector.deleteHoKhauNhanKhau(hoKhauNhanKhau);
			}
			for(ModelHoKhau hoKhau: listHoKhau) {
				Connector.deleteHoKhau(hoKhau);
			}
			if(Connector.deleteNhanKhau(data)) {
				controllerNhanKhau.refreshNhanKhau();
				App.closeStageForm();
			}
		}       
	}

	@FXML
	public void cancel() {
		App.closeStageForm();
	}

	private void loadData(List<Integer> listId) {
		data = Connector.getNhanKhau(listId.get(0));

		textfieldHoTen.setText(data.getHoTen());
		datepickerNgaySinh.setValue(new Date(data.getNgaySinh().getTime()).toLocalDate());
		togglegroupGioiTinh.getToggles().forEach(toggle -> {
			RadioButton radiobutton = (RadioButton) toggle;
			if (radiobutton.getText().equals(data.getGioiTinh().toUpperCase())) {
				radiobutton.setSelected(true);
			}
		});
		textfieldCCCD.setText(data.getCccd());
	}

	private boolean isMissingField() {
		boolean check = false;
		if (textfieldHoTen.getText().isEmpty()) {
			textfieldHoTen.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (datepickerNgaySinh.getValue() == null) {
			datepickerNgaySinh.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (togglegroupGioiTinh.getSelectedToggle() == null) {
			togglegroupGioiTinh.getToggles().stream().forEach(toggle -> {
				RadioButton radiobutton = (RadioButton) toggle;
				radiobutton.getStyleClass().add("inputfield-error");
			});
			check = true;
		}
		if (textfieldCCCD.getText().isEmpty()) {
			textfieldCCCD.getStyleClass().add("inputfield-error");
			check = true;
		}
		return check;
	}

	public static ControllerNhanKhau getControllerNhanKhau() {
		return controllerNhanKhau;
	}

	public static void setControllerNhanKhau(ControllerNhanKhau controllerNhanKhau) {
		ControllerFormDetailNhanKhau.controllerNhanKhau = controllerNhanKhau;
	}

}
